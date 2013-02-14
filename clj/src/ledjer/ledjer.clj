(ns ledjer.ledjer
  (:require [clojure.string :as str]
            [clojure.java.io :refer [file]])
  (:import [java.util Date]))

(def current-number (atom 0))
(def date-format (java.text.SimpleDateFormat. "MMM d, yyyy"))

(defn format-date [date] (.format date-format date))
(defn parse-date [date-str] (.parse date-format date-str))

(defn next-number! []
  (swap! current-number inc)
  @current-number)

(defn ledjer [] [])

(defmulti amount-for :kind )
(defmethod amount-for :deposit [deposit] (:amount deposit))
(defmethod amount-for :payment [deposit] (- 0 (:amount deposit)))

(defn balance [ledjer]
  (apply + (map amount-for ledjer)))

(defn deposit [ledjer amount date]
  (conj ledjer {:kind :deposit :number (next-number!) :amount amount :date date}))

(defn pay [ledjer payee amount date]
  (conj ledjer {:kind :payment :number (next-number!) :payee payee :amount amount :date date}))

(defn as-dollars [pennies]
  (format "$%.2f" (/ pennies 100.0)))

(defmulti as-statement :kind )
(defmethod as-statement :deposit [deposit]
  (format "%s. %s - Deposit: %s"
    (:number deposit)
    (format-date (:date deposit))
    (as-dollars (:amount deposit))))
(defmethod as-statement :payment [payment]
  (format "%s. %s - Payment to %s: (%s)"
    (:number payment)
    (format-date (:date payment))
    (:payee payment)
    (as-dollars (:amount payment))))

(defn- compare-transactions [a b]
  (let [result (.compareTo (:date a) (:date b))]
    (if (= 0 result)
      (.compareTo (:number a) (:number b))
      result)))

(defn statement [ledjer]
  (str/join "\n"
    (conj (mapv as-statement (sort compare-transactions ledjer))
      (str "Total: " (as-dollars (balance ledjer))))))

(defn save! [ledjer]
  (spit "ledjer.dump" (pr-str ledjer)))

(defn load-ledjer []
  (read-string (slurp "ledjer.dump")))
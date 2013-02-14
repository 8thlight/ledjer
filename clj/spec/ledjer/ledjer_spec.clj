(ns ledjer.ledjer-spec
  (:require [clojure.string :as str]
            [ledjer.ledjer :refer :all ]
            [speclj.core :refer :all ])
  (:import [java.util Date]))

(describe "ledjer"

  (before (reset! current-number 0))

  (it "starts with an empty balance"
    (should= 0 (balance (ledjer))))

  (it "can deposit to increase balance"
    (should= 123 (balance (-> (ledjer)
                            (deposit 100 (Date.))
                            (deposit 23 (Date.))))))

  (it "generates statement with deposits"
    (should= "1. Jan 7, 2013 - Deposit: $1.00\n2. Jan 7, 2013 - Deposit: $0.23\nTotal: $1.23"
      (statement (-> (ledjer)
                   (deposit 100 (parse-date "Jan 7, 2013"))
                   (deposit 23 (parse-date "Jan 7, 2013"))))))

  (it "payments decrease balance"
    (should= 100 (balance (-> (ledjer)
                            (deposit 123 (Date.))
                            (pay "Joe" 23 (Date.))))))

  (it "generates statement with deposit and payment"
    (should= "1. Jan 7, 2013 - Deposit: $1.23\n2. Jan 7, 2013 - Payment to Joe: ($0.23)\nTotal: $1.00"
      (statement (-> (ledjer)
                   (deposit 123 (parse-date "Jan 7, 2013"))
                   (pay "Joe" 23 (parse-date "Jan 7, 2013"))))))

  (it "generates a transaction number for each transaction"
    (let [ledjer
          (-> (ledjer)
            (deposit 123 (Date.))
            (pay "Joe" 23 (Date.)))]
      (should= [1 2] (map :number ledjer))))

  (it "transactions can take dates"
    (let [ledjer
          (-> (ledjer)
            (deposit 123 (parse-date "Jan 5, 2013"))
            (pay "Joe" 23 (parse-date "Jan 6, 2013")))]
      (should= ["Jan 5, 2013" "Jan 6, 2013"] (map #(format-date (:date %)) ledjer))))

  (it "sorts statement by date, then number"
    (let [ledjer
          (-> (ledjer)
            (deposit 100 (parse-date "Jan 7, 2013"))
            (deposit 200 (parse-date "Jan 5, 2013"))
            (pay "Bill" 55 (parse-date "Jan 6, 2013")))]
      (should= (str/join "\n"
                 ["2. Jan 5, 2013 - Deposit: $2.00"
                  "3. Jan 6, 2013 - Payment to Bill: ($0.55)"
                  "1. Jan 7, 2013 - Deposit: $1.00"
                  "Total: $2.45"])
        (statement ledjer))))

  (it "saves a ledjer"
    (save! (-> (ledjer) (deposit 123 (Date.)) (pay "Joe" 23 (Date.))))
    (should-not= nil (slurp "ledjer.dump")))

  (it "loads a ledjer"
    (let [ledjer (-> (ledjer) (deposit 123 (Date.)) (pay "Joe" 23 (Date.)))]
      (save! ledjer)
      (should= ledjer (load-ledjer))))
  )



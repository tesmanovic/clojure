(ns project.core
  (:gen-class)
  (:require [clj-http.client :as client])
  (:require [clojure.string])
  (:require [clojure.java.io])
  (:require [clojure.data.xml :as xml]))

(defn read-rss-config-file []
  (with-open [reader (clojure.java.io/reader "config.txt")]
    (reduce conj [] (line-seq reader))))

(defn transform-to-xml [xml]
  ;to-do
  )
(defn extract-channel [response]
  (first (:content (transform-to-xml (:body response)))))

(defn extract-items [channel-xml]
  (filter
    (fn [item] (= (:tag item) :item))
    (:content channel-xml)))

(defn extract-description [item-content]
  ;to-do
  )

(defn choosen-item [feed num]
  (->  feed
       (extract-channel)
       (extract-items)
       (nth num))
  )

(defn description [feed num]
  (clojure.string/replace
    (first (:content (extract-description (:content (choosen-item feed num)))))
    #"<[^>]*>"
    ""))

(defn parse-command-number [start input]
  (nth (re-matches (re-pattern (str ":" start "([0-9]+)")) input) 1 "nil"))

(def help-text "Usage :[command]
 commands:
  :h - help
  :lr - list feeds
  :ls - list stories
  :s[num] - number of listed storie
  :r[num] - set current feed
  :q - quit")

(defn -main
  "Main function"
  [& args]
  (println help-text)
  (println "Enter command: ")

  (loop [feed ""
         input (read-line)]
  )

  (println "End"))
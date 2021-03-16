(ns project.core
  (:gen-class)
  (:require [clj-http.client :as client])
  (:require [clojure.string])
  (:require [clojure.java.io])
  (:require [clojure.data.xml :as xml]))

(defn read-rss-config-file []
  (with-open [reader (clojure.java.io/reader "config.txt")]
    (reduce conj [] (line-seq reader))))
(defn get-feed [url]
  (client/get url
              {:accept :xml}))
(defn transform-to-xml [xml]
  (let [input-xml (java.io.StringReader. xml)]
    (xml/parse input-xml)))

(defn extract-channel [response]
  (first (:content (transform-to-xml (:body response)))))

(defn extract-items [channel-xml]
  (filter
    (fn [item] (= (:tag item) :item))
    (:content channel-xml)))

(defn extract-title [item-content]
  (first (filter (fn [item] (= (:tag item) :title)) item-content)))

(defn extract-titles [items-xml]
  (map (fn [item] (extract-title (:content item))) items-xml))

(defn extract-description [item-content]
  (first (filter (fn [item] (= (:tag item) :description)) item-content)))

(defn numbering [col]
  (map (fn [text num] (str num ". " text)  ) col (range (count col))))

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
    (when-not (= ":q" input)
      (println "Enter command: ")
      (recur
        (cond
          (not= (parse-command-number "r" input) "nil") (get-feed (nth (read-rss-config-file) (Integer/parseInt (parse-command-number "r" input))))
          (= feed "") (do (println "Need feed") feed)
          (= ":h" input) (do (println help-text) feed)
          (not= (parse-command-number "s" input) "nil") (do (println (description feed (Integer/parseInt (parse-command-number "s" input)))) feed)
          :else (do (println "Uknown command") (println help-text) feed))
        (read-line)))
  )

  (println "End"))
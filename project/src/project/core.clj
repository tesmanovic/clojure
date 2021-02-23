(ns project.core
  (:gen-class)
  (:require [clj-http.client :as client])
  (:require [clojure.string])
  (:require [clojure.java.io])
  (:require [clojure.data.xml :as xml]))

(defn read-rss-config-file []
  (with-open [reader (clojure.java.io/reader "config.txt")]
    (reduce conj [] (line-seq reader))))

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
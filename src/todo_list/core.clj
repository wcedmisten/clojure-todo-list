(ns todo-list.core
  (:gen-class)
  (:require [org.httpkit.server]
            [clojure.data.json :as json]
            [clojure.string])
  (:use [compojure.route :only [not-found]]
        [compojure.core :only [defroutes GET POST DELETE ANY context]]
        org.httpkit.server))

(def items (atom #{}))

(defn get-items [] ;; ordinary clojure function, accepts a request map, returns a response map
  {:status 200
   :body (json/write-str {:data  (seq (deref items))})
   :headers {"Content-Type" "text/json"}})

(defn add-item [item]
  (swap! items conj item)
  {:status 200
   :body "Success"
   :headers {"Content-Type" "text/json"}})

(defroutes all-routes
  (GET "/item" [] (get-items))
  (POST "/item/:text" [text] (add-item text))
  ;; (files "/static/") ;; static file url prefix /static, in `public` folder
  (not-found "<p>Page not found.</p>")) ;; all other, return 404




(defn -main []
  (println "Starting server 2!")
  (run-server all-routes {:port 8080}))

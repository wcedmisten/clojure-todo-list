(ns todo-list.core
  (:gen-class)
  (:require [org.httpkit.server]
            [clojure.data.json :as json]
            [clojure.string])
  (:use [compojure.route :only [not-found files]]
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

(defn remove-item [item]
  (swap! items disj item)
  {:status 200
   :body "Success"
   :headers {"Content-Type" "text/json"}})

(defroutes all-routes
  (GET "/api/v1/item" [] (get-items))
  (POST "/api/v1/item/:text" [text] (add-item text))
  (DELETE "/api/v1/item/:text" [text] (remove-item text))
  (files "/" {:root "static"}) ;; static file url prefix /static, in `static` folder
  (not-found "<p>Page not found.</p>")) ;; all other, return 404


(defn -main []
  (println "Starting server!")
  (run-server all-routes {:port 8080}))

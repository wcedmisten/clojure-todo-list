(defproject todo-list "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "https://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "https://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit "2.5.3"]
                 [compojure "1.6.2"]]
  :main ^:skip-aot todo-list.core
  :target-path "target/%s"
  :aot [todo-list.core])

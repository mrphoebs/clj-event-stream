(ns eventsource.core
  (:gen-class)
  (:use org.httpkit.server)
  (:use clojure.java.io))

(defn route [req]
  (case (:uri req)
    "/" {:status 200
        :headers {"Content-Type" "text/html"}
        :body (slurp (resource "streamer.html"))}
    "/stream" (with-channel req channel
              (send! channel {:status 200
              :headers {"Content-Type" "text/event-stream" "Cache-Control" "no-cache"}} false)
              (while true (send! channel (str "data: " (read-line) "\n\n") false)))))

(defn app [req]
  (println (req :uri))
  (route req))

(defn -main
  "Starts event source server.... "
  [& args]
  (println "starting...")
  (run-server app {:port 8080}))




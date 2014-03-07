(ns eventsource.core
  (:gen-class)
  (:use org.httpkit.server))

(defn route [req]
  (case (:uri req)
    "/" {:status 200
        :headers {"Content-Type" "text/html"}
        :body "<h1>Duuuude</h1>"}
    "/hello" (with-channel req channel
              (send! channel {:status 200
              :headers {"Content-Type" "text/event-stream"}
              :body "<h1>Hello</h1>"} false)
              (while true (send! channel (read-line) false)))))

(defn app [req]
  (println (req :uri))
  (route req))

(defn -main
  "Starts event source server.... "
  [& args]
  (println "starting...")
  (run-server app {:port 8080}))




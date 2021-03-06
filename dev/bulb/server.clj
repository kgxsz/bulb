(ns bulb.server
  (:require [ring.middleware.resource :as middleware]))


(defn handler [request]
  (let [static-asset? (re-find #"^\/(js|css|images|fonts)\/.*" (:uri request))
        request (if static-asset?
                  request
                  (assoc request :uri "/index.html"))
        handle (middleware/wrap-resource
                 (constantly {:status 404 :body "static asset not found"})
                 "public")]
    (handle request)))

(ns bulb.utils
  (:require [cemerick.url :as url]))


(defn make-url
  "Makes the API url by looking at the current host, and adding the path."
  [path]
  (let [{:keys [protocol host]} (url/url (.. js/window -location -href))]
    (str protocol "://" "api." host "/" (name path))))

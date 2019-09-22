(ns bulb.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]))


(defn app []
  [:div
   "Ready"])


(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [app]
                  (.getElementById js/document "root")))

(mount-root)

#_(defn ^:export initialise []
  (enable-console-print!)
  (mount-root))

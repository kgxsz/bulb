(ns bulb.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [bulb.views.app :refer [app]]))


(defn mount []
  (re-frame/clear-subscription-cache!)
  (reagent/render [app]
                  (.getElementById js/document "core")))


(enable-console-print!)
(mount)

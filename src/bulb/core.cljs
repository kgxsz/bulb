(ns ^:figwheel-hooks bulb.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [bulb.effects :as effects]
            [bulb.events :as events]
            [bulb.subscriptions :as subscriptions]
            [bulb.views.app :as app]))



(defn ^:after-load mount []
  (re-frame/clear-subscription-cache!)
  (reagent/render [app/app]
                  (.getElementById js/document "app-container")))


(defn ^:export initialise []
  (enable-console-print!)
  (re-frame/dispatch-sync [:initialise])
  (mount))

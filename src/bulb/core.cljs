(ns ^:figwheel-hooks bulb.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [bulb.effects :as effects]
            [bulb.events :as events]
            [bulb.subscriptions :as subscriptions]
            [bulb.views.core :as core]))


(defn ^:after-load mount []
  (re-frame/clear-subscription-cache!)
  (reagent/render [core/core] (.getElementById js/document "container")))


(defn ^:export initialise []
  (enable-console-print!)
  (re-frame/dispatch-sync [:initialise])
  (mount))

;; initialisation page
;; home page
;; grids page
;; authorisation page
;; unknown page

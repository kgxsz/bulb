(ns bulb.views.loading-page
  (:require [re-frame.core :as re-frame]
            [bulb.views.logo :as logo]
            [bulb.utils :as u]))


(defn view [{:keys [logo]}]
  [:div
   {:class (u/bem [:page])}
   [:div
    {:class (u/bem [:page__contents])}
    [:div
     {:class (u/bem [:cell :padding-top-x-huge])}
     [logo]]
    [:div
     {:class (u/bem [:text :font-size-large :margin-top-xxx-large :padding-top-x-small])}
     "loading"]]])


(defn loading-page []
  [view
   {:logo logo/logo}])

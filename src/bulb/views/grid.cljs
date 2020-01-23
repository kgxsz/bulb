(ns bulb.views.grid
  (:require [re-frame.core :as re-frame]
            [bulb.views.logo :as logo]
            [bulb.views.button :as button]
            [bulb.utils :as u]))


(defn view [{:keys [logo primary-button]}
            {:keys [update-route]}]
  [:div
   {:class (u/bem [:grid])}
   [:div
    {:class (u/bem [:grid__header])}
    [:div
     {:class (u/bem [:text :ellipsis :font-size-huge :font-weight-bold])}
     "Title - subtitle"]]
   [:div
    {:class (u/bem [:grid__body])}]
   [:div
    {:class (u/bem [:grid__footer])}]])


(defn grid []
  [view
   {:logo logo/logo
    :primary-button button/primary-button}
   {:update-route #(re-frame/dispatch [:update-route :home])}])

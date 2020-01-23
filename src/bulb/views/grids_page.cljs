(ns bulb.views.grids-page
  (:require [re-frame.core :as re-frame]
            [bulb.views.logo :as logo]
            [bulb.views.button :as button]
            [bulb.utils :as u]))


(defn view [{:keys [logo primary-button]}
            {:keys [update-route]}]
  [:div
   {:class (u/bem [:page])}
   [:div
    {:class (u/bem [:page__body])}
    [:div
     {:class (u/bem [:cell :padding-top-x-huge])}
     [logo]]
    [:div
     {:class (u/bem [:text :font-size-large :margin-top-xxx-large :padding-top-x-small])}
     "this page is under construction"]
    [:div
     {:class (u/bem [:cell :margin-auto :margin-top-xxx-large])}
     [primary-button
      {:icon :home
       :label "Leave"}
      {:on-click update-route}]]]
   [:div
    {:class (u/bem [:page__footer])}]])


(defn grids-page []
  [view
   {:logo logo/logo
    :primary-button button/primary-button}
   {:update-route #(re-frame/dispatch [:update-route :home])}])

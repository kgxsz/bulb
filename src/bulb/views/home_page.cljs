(ns bulb.views.home-page
  (:require [re-frame.core :as re-frame]
            [bulb.views.logo :as logo]
            [bulb.views.profile :as profile]
            [bulb.views.grid :as grid]
            [bulb.views.button :as button]
            [bulb.utils :as u]))


(defn view [{:keys [authorised? authorising?]}
            {:keys [logo profile grid primary-button]}
            {:keys [authorise]}]
  [:div
   {:class (u/bem [:page])}
   (if authorised?
     [:div
      {:class (u/bem [:page__body])}
      [:div
       {:class (u/bem [:cell :width-cover :margin-top-huge])}
       [profile]]
      [:div
       {:class (u/bem [:cell :width-cover :margin-top-huge])}
       [grid]]
      [:div
       {:class (u/bem [:cell :width-cover :margin-top-huge])}
       [grid]]
      [:div
       {:class (u/bem [:cell :width-cover :margin-top-huge])}
       [grid]]
      [:div
       {:class (u/bem [:cell :width-cover :margin-top-huge])}
       [grid]]]

     [:div
      {:class (u/bem [:page__body])}
      [:div
       {:class (u/bem [:cell :padding-top-x-huge])}
       [logo]]
      [:div
       {:class (u/bem [:cell :margin-auto :margin-top-xxx-large])}
       [primary-button
        {:icon :github
         :label "Sign in"
         :working? authorising?}
        {:on-click authorise}]]])
   [:div
    {:class (u/bem [:page__footer])}]])


(defn home-page []
  (let [!authorised? (re-frame/subscribe [:authorised?])
        !authorising? (re-frame/subscribe [:authorising?])]
    (fn []
      [view
       {:authorised? @!authorised?
        :authorising? @!authorising?}
       {:logo logo/logo
        :profile  profile/profile
        :grid grid/grid
        :primary-button button/primary-button}
       {:authorise #(re-frame/dispatch [:authorise])}])))

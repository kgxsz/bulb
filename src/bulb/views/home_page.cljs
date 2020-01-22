(ns bulb.views.home-page
  (:require [re-frame.core :as re-frame]
            [bulb.views.logo :as logo]
            [bulb.views.profile :as profile]
            [bulb.views.button :as button]
            [bulb.utils :as u]))


(defn view [{:keys [authorised? authorising?]}
            {:keys [logo profile primary-button]}
            {:keys [authorise]}]
  [:div
   {:class (u/bem [:page])}
   [:div
    {:class (u/bem [:page__contents])}
    [:div
     {:class (u/bem [:cell :padding-top-x-huge])}
     [logo]]
    (if authorised?
      [:div
       {:class (u/bem [:cell :width-cover :margin-top-xxx-large])}
       [profile]]
      [:div
       {:class (u/bem [:cell :margin-auto :margin-top-xxx-large])}
       [primary-button
        {:icon :github
         :label "Sign in"
         :working? authorising?}
        {:on-click authorise}]])]])


(defn home-page []
  (let [!authorised? (re-frame/subscribe [:authorised?])
        !authorising? (re-frame/subscribe [:authorising?])]
    (fn []
      [view
       {:authorised? @!authorised?
        :authorising? @!authorising?}
       {:logo logo/logo
        :profile  profile/profile
        :primary-button button/primary-button}
       {:authorise #(re-frame/dispatch [:authorise])}])))

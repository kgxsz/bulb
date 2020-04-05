(ns bulb.views.profile
  (:require [re-frame.core :as re-frame]
            [bulb.views.button :as button]
            [bulb.utils :as u]))


(defn view [{:keys [profile deauthorising?]}
            {:keys [light-button]}
            {:keys [deauthorise]}]
  (if (some? profile)
    [:div
     {:class (u/bem [:profile])}
     [:img
      {:class (u/bem [:profile__avatar])
       :src (:avatar profile)}]
     [:div
      {:class (u/bem [:profile__name])}
      [:div
       {:class (u/bem [:text :ellipsis :font-size-huge :font-weight-bold])}
       (or (:name profile) (:handle profile))]]
     [:div
      {:class (u/bem [:profile__divider])}]
     [light-button
      {:icon :exit
       :working? deauthorising?}
      {:on-click deauthorise}]]
    [:div
     {:class (u/bem [:profile])}]))


(defn profile []
  (let [!profile (re-frame/subscribe [:profile])
        !deauthorising? (re-frame/subscribe [:deauthorising?])]
    [view
     {:profile @!profile
      :deauthorising? @!deauthorising?}
     {:light-button button/light-button}
     {:deauthorise #(re-frame/dispatch [:deauthorise])}]))

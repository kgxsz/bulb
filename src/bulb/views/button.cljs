(ns bulb.views.button
  (:require [re-frame.core :as re-frame]
            [bulb.utils :as u]))


(defn view [{:keys [type working? disabled? icon label]}
            {:keys [on-click]}]
  [:div
   {:class (u/bem [:button type (when working? :working) (when disabled? :disabled)])
    :on-click (when-not (or working? disabled?) on-click)}
   (when icon
     [:div
      {:class (u/bem [:icon icon :font-size-xxx-large])}])
   (when label
     [:div
      {:class (u/bem [:text :font-size-medium :padding-left-xx-small])}
      label])])


(defn button [properties behaviours]
  [view
   properties
   behaviours])


(defn primary-button [properties behaviours]
  [button
   (assoc properties
          :type :primary)
   behaviours])


(defn light-button [properties behaviours]
  [button
   (assoc properties
          :type :light)
   behaviours])

(ns bulb.views.button
  (:require [re-frame.core :as re-frame]
            [bulb.utils :as u]))


(defn view [{:keys [type working? disabled? icon label]}
            {:keys [on-click]}]
  [:div
   {:class (u/bem [:button type (when working? :working) (when disabled? :disabled)]
                  [:cell :row :width-huge :height-x-large])
    :on-click (when-not (or working? disabled?) on-click)}
   [:div
    {:class (u/bem [:icon icon :font-size-xxx-large])}]
   [:div
    {:class (u/bem [:text :font-size-medium :padding-left-xx-small])}
    label]])


(defn button [properties behaviours]
  [view
   properties
   behaviours])


(defn primary-button [properties behaviours]
  [button
   (assoc properties
          :type :primary)
   behaviours])

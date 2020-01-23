(ns bulb.views.notification
  (:require [re-frame.core :as re-frame]
            [bulb.utils :as u]))


(defn view [{:keys [type colour background? icon title paragraph]}]
  [:div
   {:class (u/bem [:notification type (when background? :background)])}
   [:div
    {:class (u/bem [:cell :row :align-baseline])}
    [:div
     {:class (u/bem [:icon icon :font-size-xxx-large colour])}]
    [:div
     {:class (u/bem [:text :font-size-x-large :font-weight-bold :padding-left-xxx-small colour])}
     title]]
   [:div
    {:class (u/bem [:cell :margin-top-tiny])}
    [:div
     {:class (u/bem [:text :align-center colour])}
     paragraph]]])


(defn notification [{:keys [type] :as properties}]
  [view
   (assoc properties
          :icon (case type
                  :success :checkmark-circle
                  :warning :warning
                  :error :warning)
          :colour (case type
                    :success :colour-green-two
                    :error :colour-red-two
                    :warning :colour-yellow-two)
          :title (clojure.string/capitalize (name type)))])


(defn error-notification [properties]
  [notification
   (assoc properties
          :type :error)])

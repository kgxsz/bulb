(ns bulb.views.logo
  (:require [re-frame.core :as re-frame]
            [bulb.utils :as u]))


(defn view []
  [:div
   {:class (u/bem [:logo])}
   [:svg
    {:xmlns "http://www.w3.org/2000/svg"
     :viewBox "0 0 210 50"}
    [:g
     [:text
      {:class (u/bem [:logo__text])
       :transform "translate(27.5 31.5)"}
      [:tspan
       {:x "0"
        :y "0"}
       "KAIZEN"]]
     [:line
      {:class (u/bem [:logo__line])
       :y2 "42"
       :transform "translate(50 2)"}]
     [:line
      {:class (u/bem [:logo__line])
       :y2 "42"
       :transform "translate(80 4)"}]
     [:line
      {:class (u/bem [:logo__line])
       :y2 "46"
       :transform "translate(102 2)"}]
     [:line
      {:class (u/bem [:logo__line])
       :y2 "40"
       :transform "translate(130 4)"}]
     [:line
      {:class (u/bem [:logo__line])
       :y2 "44"
       :transform "translate(159 2)"}]
     [:line
      {:class (u/bem [:logo__line])
       :y2 "42"
       :transform "translate(19 4)"}]
     [:line
      {:class (u/bem [:logo__line])
       :y2 "44"
       :transform "translate(189 4)"}]]]])


(defn logo []
  [view])

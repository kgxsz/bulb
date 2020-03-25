(ns bulb.styles.components.notification
  (:require [bulb.styles.constants :as c]
            [bulb.styles.utils :as u]
            [garden.def :refer [defstyles]]
            [garden.units :refer [px percent ms vh vw]]))

(defstyles notification
  [:.notification
   {:position :fixed
    :display :flex
    :flex-direction :column
    :justify-content :center
    :align-items :center
    :width (-> c/proportion :100 percent)
    :height (-> c/filling :huge px)
    :padding-left (-> c/spacing :small px)
    :padding-right (-> c/spacing :small px)
    :border-bottom :solid
    :border-width (px (:xx-tiny c/filling))}

   [:&--success
    {:background-color (:green-one c/colour)
     :border-color (:green-two c/colour)}]

   [:&--warning
    {:background-color (:yellow-one c/colour)
     :border-color (:yellow-two c/colour)}]

   [:&--error
    {:background-color (:red-one c/colour)
     :border-color (:red-two c/colour)}]

   [:&--background
    {:display :none}
    (u/tiny-width
     {:display :flex})]])

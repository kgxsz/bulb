(ns bulb.styles.components.notification
  (:require [bulb.styles.constants :as c]
            [garden.def :refer [defstyles]]
            [garden.units :refer [px percent ms vh vw]]))

(defstyles notification
  [:.notification
   {:border-bottom :solid
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
    {:z-index -1}]])

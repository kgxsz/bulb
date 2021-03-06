(ns bulb.styles.components.logo
  (:require [bulb.styles.constants :as c]
            [bulb.styles.utils :as u]
            [garden.def :refer [defstyles]]
            [garden.units :refer [px em percent ms vh vw]]))


(defstyles logo
  [:.logo
   {:width (-> c/filling :xx-huge px)
    :height (-> c/filling :xx-large px)}

   [:&__line
    {:fill :none
     :stroke (:black-two c/colour)
     :stroke-width (:xx-tiny c/filling)}]

   [:&__text
    {:fill (:black-two c/colour)
     :font-size (px (:huge c/font-size))
     :font-family "Arial, \"Helvetica Neue\", Helvetica, sans-serif"
     :font-weight :bold
     :letter-spacing (px (:x-large c/font-size))}]])

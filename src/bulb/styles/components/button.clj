(ns bulb.styles.components.button
  (:require [bulb.styles.constants :as c]
            [bulb.styles.utils :as u]
            [garden.def :refer [defstyles]]
            [garden.units :refer [px percent ms vh vw]]))


(defstyles button
  [:.button
   {:cursor :pointer}

   [:&--working
    {:cursor :default}]

   [:&--disabled
    {:cursor :not-allowed
     :opacity (:10 c/fraction)}]

   [:&--primary
    {:width (-> c/filling :huge px)
     :min-width (-> c/filling :huge px)
     :color (:black-two c/colour)
     :border [[:solid (px (:xx-tiny c/filling)) (:black-two c/colour)]]
     :border-radius (-> c/radius :x-huge px)}
    [:&:hover
     {:background-color (:white-two c/colour)}]]

   [:&--light
    {:width (-> c/filling :x-large px)
     :min-width (-> c/filling :x-large px)
     :color (:black-two c/colour)}]])

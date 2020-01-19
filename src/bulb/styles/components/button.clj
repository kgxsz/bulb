(ns bulb.styles.components.button
  (:require [bulb.styles.constants :as c]
            [bulb.styles.utils :as u]
            [garden.def :refer [defstyles]]
            [garden.units :refer [px percent ms vh vw]]))


(defstyles button
  [:.button
   {:cursor :pointer
    :border [[:solid (px (:xx-tiny c/filling)) (:black-two c/colour)]]}

   [:&--working
    {:cursor :default}]

   [:&--disabled
    {:cursor :not-allowed
     :opacity (:10 c/fraction)}]

   [:&--primary
    {:color (:black-two c/colour)
     :border-color (:black-two c/colour)}
    [:&:hover
     {:background-color (:white-two c/colour)}]]])

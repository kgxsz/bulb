(ns bulb.styles.components.grid
  (:require [bulb.styles.constants :as c]
            [garden.def :refer [defstyles]]
            [garden.units :refer [px percent ms vh vw]]))

(defstyles grid
  [:.grid
   [:&__header]
   [:&__body
    {:height (-> c/filling :x-huge px)
     :margin-top (-> c/spacing :x-large px)
     :background-color (:white-three c/colour)}]
   [:&__footer
    {:height (-> c/filling :xx-tiny px)
     :margin-top (-> c/spacing :x-large px)
     :background-color (-> c/colour :white-three)}]])

;; Grid widths fixed
;; 1004
;; 748
;; 460
;; 300

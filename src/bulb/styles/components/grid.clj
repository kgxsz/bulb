(ns bulb.styles.components.grid
  (:require [bulb.styles.constants :as c]
            [bulb.styles.utils :as u]
            [garden.def :refer [defstyles]]
            [garden.units :refer [px percent ms vh vw]]))

(defstyles grid
  [:.grid

   [:&__header
    [:&__separator
     {:padding [[0 (-> c/spacing :x-small px)]]}]]

   [:&__body
    {:position :relative
     :overflow :hidden
     :height (-> c/filling :x-huge px)
     :margin-top (-> c/spacing :x-large px)}]

   [:&__labels
    {:position :absolute
     :bottom 0}
    [:&--horizontal
     {:right 0
      :display :flex
      :flex-direction :row
      :height 0}]
    [:&--vertical
     {:top 0
      :left 0
      :width (px (+ (c/filling :large) (c/filling :x-tiny)))
      :background-color (-> c/colour :white-one)}]]

   [:&__label
    [:&--horizontal
     {:height (px (* 2 (+ (-> c/filling :x-small) (-> c/spacing :xx-tiny))))}]
    [:&--vertical
     {:width (px (+ (-> c/filling :x-small) (-> c/spacing :xx-tiny)))
      :transform "rotate(-90deg)"
      :transform-origin [[:left :top 0]]}]]

   [:&__cells
    {:position :absolute
     :top 0
     :right 0
     :display :grid
     :grid-template-rows [(repeat 7 (-> c/filling :x-small px))]
     :grid-auto-columns (-> c/filling :x-small px)
     :grid-auto-flow :column
     :grid-gap (-> c/spacing :xx-tiny px)}

    [:&__cell
     {:border-radius (-> c/radius :tiny px)
      :cursor :pointer}

     [:&--colour
      (u/make-modifiers c/colour :background-color)]

     [:&--disabled
      {:cursor :default}]]]

   [:&__footer
    {:height (-> c/filling :xx-tiny px)
     :margin-top (-> c/spacing :x-large px)
     :background-color (-> c/colour :white-three)}]])

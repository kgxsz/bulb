(ns bulb.styles.components.profile
  (:require [bulb.styles.constants :as c]
            [bulb.styles.utils :as u]
            [garden.def :refer [defstyles]]
            [garden.units :refer [px percent ms vh vw]]))


(defstyles profile
  [:.profile {:display :flex
              :flex-direction :row
              :justify-content :center
              :align-items :center
              :height(-> c/filling :x-large px)}

   [:&__avatar {:height (-> c/filling :medium px)
                :width (-> c/filling :medium px)
                :min-height (-> c/filling :medium px)
                :min-width (-> c/filling :medium px)
                :border-radius (-> c/proportion :50 percent)
                :background-color (:white-three c/colour)}]

   [:&__name {:margin-left (-> c/spacing :xx-small px)
              :max-width (-> c/proportion :40 percent)}]

   [:&__divider {:width (-> c/proportion :100 percent)
                 :height (-> c/filling :xx-tiny px)
                 :margin-top (-> c/spacing :xx-tiny px)
                 :margin-left (-> c/spacing :xx-small px)
                 :background-color (-> c/colour :white-three)}]])

(ns bulb.styles.components.page
  (:require [bulb.styles.constants :as c]
            [bulb.styles.utils :as u]
            [garden.def :refer [defstyles]]
            [garden.units :refer [px percent ms vh vw]]))


(defstyles page
  [:.page
   {:display :none
    :min-height (vh (:100 c/proportion))
    :overflow :auto
    :background-color (:white-one c/colour)}

   (u/tiny-width
    {:display :none})

   (u/small-width
    {:display :block})

   (u/medium-width
    {:display :block})

   (u/large-width
    {:display :block})

   (u/huge-width
    {:display :block})

   [:&__body
    {:margin :auto
     :padding (-> c/spacing :medium px)
     :display :flex
     :flex-direction :column
     :align-items :center}

    (u/small-width
     {:width (-> c/breakpoint :small :start px)})

    (u/medium-width
     {:width (-> c/breakpoint :medium :start px)})

    (u/large-width
     {:width (-> c/breakpoint :large :start px)})

    (u/huge-width
     {:width (-> c/breakpoint :huge :start px)})]

   [:&__footer
    {:height (-> c/filling :xx-large px)}]])

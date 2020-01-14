(ns bulb.styles.components.page
  (:require [bulb.styles.constants :as c]
            [bulb.styles.utils :as u]
            [garden.def :refer [defstyles]]
            [garden.units :refer [px percent ms vh vw]]))


(defstyles page
  [:.page
   {:display :none
    :min-height (vh (:100 c/proportion))}

   (u/tiny-width
    {:display :none})

   (u/small-width
    {:display :none})

   (u/medium-width
    {:display :block})

   (u/large-width
    {:display :block})

   (u/huge-width
    {:display :block})])

(ns bulb.styles.core
  (:require [garden.def :refer [defstyles]]
            [bulb.styles.components.app :refer [app]]))


(defstyles core
  [:body {:font-size "10px"
          :color :red}]

  app)

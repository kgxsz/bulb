(ns bulb.routing
  (:require [re-frame.core :as re-frame]
            [domkm.silk :as silk]))


(defonce !history (atom nil))

(def routes (silk/routes [[:home [[]]]
                          [:grids [["grids"]]]
                          [:authorisation [["authorisation"]]]]))

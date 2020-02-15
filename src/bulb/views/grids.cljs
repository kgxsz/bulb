(ns bulb.views.grids
  (:require [re-frame.core :as re-frame]
            [bulb.views.grid :as grid]
            [bulb.utils :as u]))


(defn view [{:keys [n]}
            {:keys [grid]}]
  [:div
   (for [i (range n)]
     [:div
      {:key i
       :class (u/bem [:cell :width-cover :margin-top-huge])}
      [grid i]])])


(defn grids []
  (let [!grids (re-frame/subscribe [:grids])]
    (fn []
      [view
       {:n (count @!grids)}
       {:grid grid/grid}])))

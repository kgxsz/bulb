(ns bulb.views.grid
  (:require [re-frame.core :as re-frame]
            [bulb.views.logo :as logo]
            [bulb.views.button :as button]
            [bulb.utils :as u]))


(defn cells
  []
  (for [i (range 434)]
    {:date (str i "th April, 2020")
     :colour (rand-nth [:colour-grey-four :colour-white-three])
     :disabled? (rand-nth [false true])}))


(defn view [{:keys []}
            {:keys []}]
  (let [title "Title"
        subtitle "some long winded subtitle about this"]
    [:div
     {:class (u/bem [:grid])}
     [:div
      {:class (u/bem [:grid__header])}
      [:span
       {:class (u/bem [:text :font-size-huge :font-weight-bold :colour-black-two])}
       title]
      [:span
       {:class (u/bem [:grid__header__separator])}
       [:span
        {:class (u/bem [:text :font-size-huge :colour-grey-two])}
        "—"]]
      [:span
       {:class (u/bem [:text :font-size-huge :colour-grey-two])}
       subtitle]]
     [:div
      {:class (u/bem [:grid__body])}
      [:div
       {:class (u/bem [:grid__cells])}
       (doall
        (for [{:keys [date colour disabled?]} (cells)]
          [:div
           {:key date
            :title date
            :class (u/bem [:grid__cells__cell colour (when disabled? :disabled)])
            :on-click (when-not disabled? #(println "clicked" date))}]))]]
     [:div
      {:class (u/bem [:grid__footer])}]]))


(defn grid []
  [view {} {}])

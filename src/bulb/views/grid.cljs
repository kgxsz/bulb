(ns bulb.views.grid
  (:require [re-frame.core :as re-frame]
            [bulb.views.logo :as logo]
            [bulb.views.button :as button]
            [bulb.utils :as u]
            [cljs-time.core :as t]
            [cljs-time.format :as t.format]
            [cljs-time.periodic :as t.periodic]))


(def date-label-formatter (t.format/formatter "EEEE do 'of' MMMM, Y"))


(def dates
  (let [today (t/today)]
    (t.periodic/periodic-seq
     (t/minus- today (t/days (+ 426 (t/day-of-week today))))
     (t/plus- today (t/days 1))
     (t/days 1))))


(defn cells
  [dates]
  (for [date dates]
    {:date (t.format/unparse date-label-formatter date)
     :colour (if (odd? (t/month date)) :colour-grey-four :colour-white-three)
     :disabled? (t/before? date (t/minus- (last dates) (t/days 6)))}))


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
        (for [{:keys [date colour disabled?]} (cells dates)]
          [:div
           {:key date
            :title date
            :class (u/bem [:grid__cells__cell colour (when disabled? :disabled)])
            :on-click (when-not disabled? #(println "clicked" date))}]))]]
     [:div
      {:class (u/bem [:grid__footer])}]]))


(defn grid []
  [view {} {}])

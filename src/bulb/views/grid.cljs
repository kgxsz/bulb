(ns bulb.views.grid
  (:require [re-frame.core :as re-frame]
            [bulb.views.logo :as logo]
            [bulb.views.button :as button]
            [bulb.utils :as u]
            [cljs-time.core :as t]
            [cljs-time.local :as t.local]
            [cljs-time.coerce :as t.coerce]
            [cljs-time.format :as t.format]
            [cljs-time.periodic :as t.periodic]))


(def date-label-formatter (t.format/formatter "EEEE do 'of' MMMM, Y"))
(def month-label-formatter (t.format/formatter "MMM"))
(def day-label-formatter (t.format/formatter "E"))
(def basic-formatter (t.format/formatters :basic-date))


(def dates
  (let [today (t/today)]
    (t.periodic/periodic-seq
     (t/minus- today (t/days (+ 426 (t/day-of-week today))))
     (t/plus- today (t/days 1))
     (t/days 1))))


(defn colour [i]
  (let [colours [:colour-green-two
                 :colour-orange-two
                 :colour-yellow-two
                 :colour-purple-two
                 :colour-blue-two
                 :colour-red-two]]
    (get colours (mod i (count colours)))))


(defn view [{:keys [title subtitle cells]}
            {:keys [toggle-checked-date]}]
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
      (for [{:keys [date colour disabled?]} cells]
        [:div
         {:key date
          :title (t.format/unparse date-label-formatter date)
          :class (u/bem [:grid__cells__cell colour (when disabled? :disabled)])
          :on-click (when-not disabled? #(toggle-checked-date date))}]))]
    [:div
     {:class (u/bem [:grid__labels :vertical])}
     (doall
      (for [label ["Mon" "Wed" "Fri" "Sun"]]
        [:div
         {:key label
          :class (u/bem [:grid__label :horizontal])}
         [:div
          {:class (u/bem [:text :font-size-xx-small :colour-grey-one])}
          label]]))]]
   [:div
    {:class (u/bem [:grid__footer])}]])


(defn grid [i]
  (let [!grid (re-frame/subscribe [:grid i])]
    (fn []
      (let [{:keys [title subtitle checked-dates]} @!grid]
        [view
         {:title title
          :subtitle subtitle
          :cells (for [date dates]
                   {:date date
                    :colour (cond
                              (contains? checked-dates (t.format/unparse basic-formatter date)) (colour i)
                              (odd? (t/month date)) :colour-grey-four
                              (even? (t/month date)) :colour-white-three)
                    :disabled? (t/before? date (t/minus- (last dates) (t/days 6)))})}
         {:toggle-checked-date #(re-frame/dispatch [:toggle-checked-date i (t.format/unparse basic-formatter %)])}]))))

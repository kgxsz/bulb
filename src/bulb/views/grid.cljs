(ns bulb.views.grid
  (:require [re-frame.core :as re-frame]
            [bulb.views.logo :as logo]
            [bulb.views.button :as button]
            [bulb.utils :as u]))


(defn view [{:keys [logo primary-button]}
            {:keys [update-route]}]
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
        (for [i (range 500)]
          [:div
           {:key i
            :title (str i)
            :class (u/bem [:grid__cells__cell :colour-grey-four])}]))
       #_(doall
        (for [{:keys [date label shaded? protected?]} (make-items (t/today))]
          (let [add-checked-date (fn [] (re-frame/dispatch [:add-checked-date id date]))
                remove-checked-date (fn [] (re-frame/dispatch [:remove-checked-date id date]))
                checked? (contains? (set @!checked-dates) date)]
            [:div
             {:key date
              :title label
              :class (u/bem [:calendar__items__item
                             (cond
                               checked? colour
                               shaded? :colour-grey-medium
                               :else :colour-grey-light)])
              :on-click (when-not protected?
                          (if checked?
                            remove-checked-date
                            add-checked-date))}])))]

      ]
     [:div
      {:class (u/bem [:grid__footer])}]]))


(defn grid []
  [view
   {:logo logo/logo
    :primary-button button/primary-button}
   {:update-route #(re-frame/dispatch [:update-route :home])}])

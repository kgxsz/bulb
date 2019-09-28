(ns bulb.views.app
  (:require [re-frame.core :as re-frame]))


(defn view [{:keys [authorised?]}
            {:keys [toggle route]}]
  [:div
   {:class "app"}
   (if authorised? "authed" "not authed")
   [:button
    {:on-click toggle}
    "toggle!"]
   [:button
    {:on-click route}
    "route!!"]])


(defn app []
  (let [!authorised? (re-frame/subscribe [:authorised?])]
    (fn []
      [view
       {:authorised? @!authorised?}
       {:toggle #(re-frame/dispatch [:toggle])
        :route #(re-frame/dispatch [:go-to-home-page])}])))

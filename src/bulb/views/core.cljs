(ns bulb.views.core
  (:require [re-frame.core :as re-frame]))


(defn view [{:keys [routing-initialised? route authorised?]}
            {:keys [authorise]}]
  (if routing-initialised?
    [:div
     {:class "core"}
     (case route
       :home [:div
              "Hi there!"
              [:div
               (if authorised? "authed" "not authed")]
              [:button
               {:on-click authorise}
               "authorise"]]
       :user [:div
              "You're a user!"]
       :authorisation [:div
                       "authorising"]
       :unknown [:div
                 "Something is wrong"])]
    [:div
     {:class "core"}
     "Not ready"]))


(defn core []
  (let [!routing-initialised? (re-frame/subscribe [:routing-initialised?])
        !route (re-frame/subscribe [:route])
        !authorised? (re-frame/subscribe [:authorised?])]
    (fn []
      [view
       {:routing-initialised? @!routing-initialised?
        :route @!route
        :authorised? @!authorised?}
       {:authorise #(re-frame/dispatch [:authorise])}])))

(ns bulb.views.core
  (:require [re-frame.core :as re-frame]))


(defn view [{:keys [routing-initialised? route authorised?]}
            {:keys [initialise-authorisation]}]
  (if routing-initialised?
    [:div
     {:class "core"}
     (case route
       :home [:div
              "Hi there!"
              (if authorised?
                [:div
                 "authed"]
                [:button
                 {:on-click initialise-authorisation}
                 "authorise"])]
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
       {:initialise-authorisation #(re-frame/dispatch [:initialise-authorisation])}])))

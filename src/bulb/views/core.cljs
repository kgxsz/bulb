(ns bulb.views.core
  (:require [re-frame.core :as re-frame]
            [bulb.views.notification :as notification]
            [bulb.utils :as u]))


(defn view [{:keys [route authorised?]}
            {:keys [error-notification ]}
            {:keys [authorise deauthorise get-profile]}]
  [:div
   {:class (u/bem [:core])}
   [error-notification]
   [:div
    {:class (u/bem [:page]
                   [:cell :overflow-auto :colour-white-one])}
    (case route
      :home [:div
              [:button
               {:on-click get-profile}
               [:div
                {:class (u/bem [:text])}
                "see my profile"]]
              (if authorised?
                [:button
                 {:on-click deauthorise}
                 "deauthorise"]
                [:button
                 {:on-click authorise}
                 "authorise"])]
      :user [:div
             {:class (u/bem [:text])}
             "You're a user!"]
      :authorisation [:div
                      {:class (u/bem [:text])}
                      "authorising"]
      :unknown [:div
                {:class (u/bem [:text])}
                "Something is wrong"]
      [:div
       {:class (u/bem [:cell :margin-top-x-huge])}
       [:div
        {:class (u/bem [:text :align-center :font-size-large])}
        "Loading"]])]])


(defn core []
  (let [!route (re-frame/subscribe [:route])
        !authorised? (re-frame/subscribe [:authorised?])]
    (fn []
      [view
       {:route @!route
        :authorised? @!authorised?}
       {:error-notification notification/error-notification}
       {:authorise #(re-frame/dispatch [:authorise])
        :deauthorise #(re-frame/dispatch [:deauthorise])
        :get-profile #(re-frame/dispatch [:get-profile])}])))


;; Loading until a profile has been procured
;; Error messages for any non-200 responses
;; If profile is nil, show auth button

(ns bulb.views.core
  (:require [re-frame.core :as re-frame]
            [bulb.views.notification :as notification]
            [bulb.views.button :as button]
            [bulb.utils :as u]))


(defn view [{:keys [initialising? authorised? route current-user-profile]}
            {:keys [error-notification primary-button]}
            {:keys [authorise deauthorise]}]
  [:div
   {:class (u/bem [:core])}
   [error-notification]
   [:div
    {:class (u/bem [:page]
                   [:cell :overflow-auto :colour-white-one])}
    (if initialising?
      [:div
       {:class (u/bem [:cell :margin-top-x-huge])}
       [:div
        {:class (u/bem [:text :align-center :font-size-large])}
        "loading"]]
      (case route
        :home [:div
               {:class (u/bem [:cell :column :margin-top-x-huge])}
               (if authorised?
                 (if current-user-profile
                   [primary-button
                    {:label "Deauthorise"}
                    {:on-click deauthorise}]
                   [:div
                    {:class (u/bem [:text :align-center :font-size-large])}
                    ;; This is loading for now, but eventually this would be
                    ;; some kind of ghost item for the profile
                    "loading"])
                 [primary-button
                  {:label "Authorise"}
                  {:on-click authorise}])
               [:div
                (str current-user-profile)]]
        :user [:div
               {:class (u/bem [:cell :margin-top-x-huge])}
               [:div
                {:class (u/bem [:text :align-center :font-size-large])}
                "hello user"]]
        :authorisation [:div
                        {:class (u/bem [:cell :margin-top-x-huge])}
                        [:div
                         {:class (u/bem [:text :align-center :font-size-large])}
                         "authorising"]]
        :unknown [:div
                  {:class (u/bem [:cell :margin-top-x-huge])}
                  [:div
                   {:class (u/bem [:text :align-center :font-size-large])}
                   "something is wrong"]]))]])


(defn core []
  (let [!initialising? (re-frame/subscribe [:initialising?])
        !authorised? (re-frame/subscribe [:authorised?])
        !route (re-frame/subscribe [:route])
        !current-user-profile (re-frame/subscribe [:current-user-profile])]
    (fn []
      [view
       {:initialising? @!initialising?
        :authorised? @!authorised?
        :route @!route
        :current-user-profile @!current-user-profile}
       {:error-notification notification/error-notification
        :primary-button button/primary-button}
       {:authorise #(re-frame/dispatch [:authorise])
        :deauthorise #(re-frame/dispatch [:deauthorise])}])))

;; Issue an initialisation command on initialisation - DONE
;; Merge the current-user-id into the state - DONE
;; Use an interceptor to capture the current-user-id for every query/command - DONE
;; Manage the flicker before getting the initialisation response - DONE
;; Show the auth button if not authed - DONE
;; Query for the profile if authed - DONE
;; Fix the transit tagged issue - DONE
;; Don't finish loading home page until the full profile is fetched - DONE
;; Fix the dual command on auth issue - TODO
;; Error messages for any unexpected outcomes - TODO

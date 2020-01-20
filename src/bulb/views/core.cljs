(ns bulb.views.core
  (:require [re-frame.core :as re-frame]
            [bulb.views.notification :as notification]
            [bulb.views.logo :as logo]
            [bulb.views.button :as button]
            [bulb.utils :as u]))


(defn view [{:keys [initialising? authorised? authorising? deauthorising? error? route profile]}
            {:keys [error-notification logo primary-button]}
            {:keys [authorise deauthorise]}]
  [:div
   {:class (u/bem [:core])}
   [error-notification
    {:background? true
     :paragraph "This application requires a larger browser window."}]
   (when error?
     [error-notification
      {:paragraph "Looks like something's gone wrong."}])
   [:div
    {:class (u/bem [:page]
                   [:cell :overflow-auto :colour-white-one])}
    (if true ;initialising?
      [:div
       {:class (u/bem [:cell :column :padding-top-x-huge])}
       [logo]
       [:div
        {:class (u/bem [:cell :margin-top-xxx-large])}
        [:div
         {:class (u/bem [:text :align-center :font-size-large])}
         "loading"]]]
      (case route
        :home [:div
               {:class (u/bem [:cell :column :margin-top-xx-huge])}
               (if authorised?
                 (if (some? profile)
                   [primary-button
                    {:icon :exit
                     :label "Sign out"
                     :working? deauthorising?}
                    {:on-click deauthorise}]
                   [:div
                    {:class (u/bem [:text :align-center :font-size-large])}
                    "loading"])
                 [primary-button
                  {:icon :github
                   :label "Sign in"
                   :working? authorising?}
                  {:on-click authorise}])
               [:div
                (str profile)]]
        :grids [:div
                {:class (u/bem [:cell :margin-top-xx-huge])}
                [:div
                 {:class (u/bem [:text :align-center :font-size-large])}
                 "GRIDS!"]]
        :authorisation [:div
                        {:class (u/bem [:cell :margin-top-xx-huge])}
                        [:div
                         {:class (u/bem [:text :align-center :font-size-large])}
                         "loading"]]
        :unknown [:div
                  {:class (u/bem [:cell :margin-top-xx-huge])}
                  [:div
                   {:class (u/bem [:text :align-center :font-size-large])}
                   "something is wrong"]]))]])


(defn core []
  (let [!initialising? (re-frame/subscribe [:initialising?])
        !authorised? (re-frame/subscribe [:authorised?])
        !authorising? (re-frame/subscribe [:authorising?])
        !deauthorising? (re-frame/subscribe [:deauthorising?])
        !error? (re-frame/subscribe [:error?])
        !route (re-frame/subscribe [:route])
        !profile (re-frame/subscribe [:profile])]
    (fn []
      [view
       {:initialising? @!initialising?
        :authorised? @!authorised?
        :authorising? @!authorising?
        :deauthorising? @!deauthorising?
        :error? @!error?
        :route @!route
        :profile @!profile}
       {:error-notification notification/error-notification
        :logo logo/logo
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
;; Fix the dual command on auth issue - DONE
;; Error messages for any unexpected outcomes - DONE
;; Deal with grids when not authorised - TODO
;; Button and page reactions on signal - DONE
;; Add logo components and styling - TODO
;; Clear down unwanted HTML items in index logo - TODO
;; Look at favicon - TODO

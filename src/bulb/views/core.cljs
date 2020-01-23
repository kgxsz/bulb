(ns bulb.views.core
  (:require [re-frame.core :as re-frame]
            [bulb.views.notification :as notification]
            [bulb.views.loading-page :as loading-page]
            [bulb.views.home-page :as home-page]
            [bulb.views.grids-page :as grids-page]
            [bulb.views.unknown-page :as unknown-page]
            [bulb.utils :as u]))


(defn view [{:keys [initialising? error? route]}
            {:keys [error-notification loading-page home-page grids-page unknown-page]}]
  [:div
   {:class (u/bem [:core])}
   [error-notification
    {:background? true
     :paragraph "This application requires a larger browser window."}]
   (when error?
     [error-notification
      {:paragraph "Looks like something's gone wrong."}])
   (if initialising?
     [loading-page]
     (case route
       :home [home-page]
       :grids [grids-page]
       :authorisation [loading-page]
       :unknown [unknown-page]))])


(defn core []
  (let [!initialising? (re-frame/subscribe [:initialising?])
        !error? (re-frame/subscribe [:error?])
        !route (re-frame/subscribe [:route])]
    (fn []
      [view
       {:initialising? @!initialising?
        :error? @!error?
        :route @!route}
       {:error-notification notification/error-notification
        :loading-page loading-page/loading-page
        :home-page home-page/home-page
        :grids-page grids-page/grids-page
        :unknown-page unknown-page/unknown-page}])))

(ns bulb.effects
  (:require [bulb.routing :as routing]
            [re-frame.core :as re-frame]
            [domkm.silk :as silk]
            [pushy.core :as pushy]
            [medley.core :as medley]
            [clojure.string :as string]))


(re-frame/reg-fx
 :initialise-routing
 (fn []
   (reset! routing/!history
           (pushy/pushy
            #(re-frame/dispatch
              [:route {:route (::silk/name %)
                       :route-params (->> (select-keys % [:username])
                                          (medley/map-vals string/lower-case))
                       :query-params (->> % ::silk/url :query (medley/map-keys keyword))}])
            #(or (silk/arrive routing/routes %)
                 {::silk/name :unknown, ::silk/url {:query {}}})))
   (pushy/start! @routing/!history)))


(re-frame/reg-fx
 :update-route
 (fn [{:keys [host route route-params query-params]}]
   (pushy/set-token! @routing/!history (silk/depart routing/routes route (or route-params {})))))


(re-frame/reg-fx
 :redirect
 (fn [{:keys [url]}]
   (set! js/window.location url)))

; For examples: https://github.com/kgxsz/sloth/blob/master/src/main/app/navigation.cljs

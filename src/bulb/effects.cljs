(ns bulb.effects
  (:require [bulb.routing :as routing]
            [re-frame.core :as re-frame]
            [domkm.silk :as silk]
            [pushy.core :as pushy]
            [medley.core :as medley]
            [clojure.string :as string]))


(re-frame/reg-fx
 :start-history
 (fn []
   (reset! routing/!history
           (pushy/pushy
            #(re-frame/dispatch
              [:route {:route (::silk/name %)
                       :route-params {:symbol (some-> % :symbol string/lower-case keyword)}
                       :query-params (->> % ::silk/url :query (medley/map-keys keyword))}])
            (partial silk/arrive routing/routes)))
   (pushy/start! @routing/!history)))


(re-frame/reg-fx
 :update-history
 (fn [[route route-params query-params]]
   (pushy/set-token! @routing/!history (silk/depart routing/routes route (or route-params {})))))

(ns bulb.events
  (:require [re-frame.core :as re-frame]
            [bulb.interceptors :as interceptors]))


(re-frame/reg-event-fx
 :initialise
 [interceptors/schema interceptors/log]
 (fn [{:keys [db]} event]
   {:db {:authorised? false
         :route :unknown}
    :start-history nil}))


(re-frame/reg-event-fx
 :toggle
 [interceptors/schema interceptors/log]
 (fn [{:keys [db]} [_]]
   {:db (update db :authorised? not)}))


(re-frame/reg-event-fx
 :route
 [interceptors/schema interceptors/log]
 (fn [{:keys [db]} [_ {:keys [route route-params query-params]}]]
   {:db (-> db
            (assoc :route route)
            (assoc :route-params route-params)
            (assoc :query-params query-params))}))


(re-frame/reg-event-fx
 :go-to-home-page
 [interceptors/schema interceptors/log]
 (fn [{:keys [db]} [_]]
   {:update-history [:home]}))

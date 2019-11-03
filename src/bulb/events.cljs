(ns bulb.events
  (:require [re-frame.core :as re-frame]
            [bulb.interceptors :as interceptors]))


(re-frame/reg-event-fx
 :initialise
 [interceptors/schema interceptors/log]
 (fn [{:keys [db]} event]
   {:db {:routing-initialised? false
         :authorised? false
         :route :unknown
         :route-params {}
         :query-params {}}
    :initialise-routing nil}))


(re-frame/reg-event-fx
 :route
 [interceptors/schema interceptors/log]
 (fn [{:keys [db]} [_ {:keys [route route-params query-params]}]]
   (let [db (-> db
                (assoc :routing-initialised? true)
                (assoc :route route)
                (assoc :route-params route-params)
                (assoc :query-params query-params))]
     (case route
       :home {:db db}
       :authorisation {:db db
                       :command {:verify-authorisation (select-keys query-params [:code])}}
       {:db db}))))


(re-frame/reg-event-fx
 :initialise-authorisation
 [interceptors/schema interceptors/log]
 (fn [{:keys [db]} [_]]
   {:query {:authorisation-details {}}}))


(re-frame/reg-event-fx
 :query-success
 [interceptors/schema]
 (fn [{:keys [db]} [_ query response]]
   (case (-> query keys first)
     :authorisation-details {:redirect {:url (get response "url")}}
     {})))


(re-frame/reg-event-fx
 :query-failure
 [interceptors/schema]
 (fn [{:keys [db]} [_ query response]]
   (js/console.warn "QUERY FAILURE!")
   {:db db}))


(re-frame/reg-event-fx
 :command-success
 [interceptors/schema]
 (fn [{:keys [db]} [_ command response]]
   (js/console.warn "COMMAND SUCCESS!")
   (case (-> command keys first)
     :verify-authorisation {:db (assoc db :authorised? true)
                            :update-route {:route :home}}
     {})))


(re-frame/reg-event-fx
 :command-failure
 [interceptors/schema]
 (fn [{:keys [db]} [_ command response]]
   (js/console.warn "COMMAND FAILURE!")
   {:db db}))

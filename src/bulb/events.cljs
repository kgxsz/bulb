(ns bulb.events
  (:require [re-frame.core :as re-frame]
            [bulb.interceptors :as interceptors]
            [goog.string :as gstring]
            [goog.string.format]))


(re-frame/reg-event-fx
 :initialise
 [interceptors/schema interceptors/log]
 (fn [{:keys [db]} event]
   {:db {:authorised? false}
    :initialise-routing true}))


(re-frame/reg-event-fx
 :route
 [interceptors/schema interceptors/log]
 (fn [{:keys [db]} [_ {:keys [route route-params query-params]}]]
   (let [db (-> db
                (assoc :route route)
                (assoc :route-params route-params)
                (assoc :query-params query-params))]
     (case route
       :home {:db db}
       :authorisation {:db db
                       :command {:authorise (select-keys query-params [:code])}}
       {:db db}))))


(re-frame/reg-event-fx
 :query-success
 [interceptors/schema]
 (fn [{:keys [db]} [_ query response]]
   (case (-> query keys first)
     :authorisation-details (let [client-id (:client-id response)
                                  host "https://github.com"
                                  path "login/oauth/authorize"
                                  query-params (gstring/format "client_id=%s" client-id)]
                              {:redirect {:url (gstring/format "%s/%s?%s" host path query-params)}})
     {})))


(re-frame/reg-event-fx
 :query-failure
 [interceptors/schema]
 (fn [{:keys [db]} [_ query response]]
   (js/console.warn "QUERY FAILURE!" response)
   {:db db}))


(re-frame/reg-event-fx
 :command-success
 [interceptors/schema]
 (fn [{:keys [db]} [_ command response]]
   (js/console.warn "COMMAND SUCCESS!")
   (case (-> command keys first)
     :authorise {:db (assoc db :authorised? true)
                 :update-route {:route :home}}
     {})))


(re-frame/reg-event-fx
 :command-failure
 [interceptors/schema]
 (fn [{:keys [db]} [_ command response]]
   (js/console.warn "COMMAND FAILURE!" response)
   {:db db}))


(re-frame/reg-event-fx
 :authorise
 [interceptors/schema interceptors/log]
 (fn [{:keys [db]} [_]]
   {:query {:authorisation-details {}}}))


(re-frame/reg-event-fx
 :deauthorise
 [interceptors/schema interceptors/log]
 (fn [{:keys [db]} [_]]
   {:command {:deauthorise {}}
    :db (assoc db :authorised? false)}))


(re-frame/reg-event-fx
 :get-profile
 [interceptors/schema interceptors/log]
 (fn [{:keys [db]} [_]]
   {:query {:profile {}}}))


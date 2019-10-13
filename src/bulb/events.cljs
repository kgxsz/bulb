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
   {:db (-> db
            (assoc :routing-initialised? true)
            (assoc :route route)
            (assoc :route-params route-params)
            (assoc :query-params query-params))}))


(re-frame/reg-event-fx
 :authorise
 [interceptors/schema interceptors/log]
 (fn [{:keys [db]} [_]]
   {:redirect {:url "https://github.com/login/oauth/authorize?client_id=8d06f025e5fbd7809f2b"}}))


(re-frame/reg-event-fx
 :query-success
 [interceptors/schema]
 (fn [{:keys [db]} [_ query response]]
   (js/console.warn "QUERY SUCCESS!")
   {:db db}))


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
   {:db db}))


(re-frame/reg-event-fx
 :command-failure
 [interceptors/schema]
 (fn [{:keys [db]} [_ command response]]
   (js/console.warn "COMMAND FAILURE!")
   {:db db}))


;; Get a constructed URL backend
;; Redirect to that URL
;; Get redirected
;; Send the code and state to the backend via a command
;; Get a session back
;; Query for the user goods

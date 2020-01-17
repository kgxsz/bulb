(ns bulb.subscriptions
  (:require [re-frame.core :as re-frame]))


(re-frame/reg-sub
 :initialising?
 (fn [db [_]]
   (let [routing-initialised? (contains? db :route)
         current-user-id-received? (contains? db :current-user-id)]
     (not (and routing-initialised? current-user-id-received?)))))


(re-frame/reg-sub
 :authorised?
 (fn [db [_]]
   (when (contains? db :current-user-id)
     (some? (:current-user-id db)))))


(re-frame/reg-sub
 :route
 (fn [db [_]]
   (:route db)))


(re-frame/reg-sub
 :current-user-profile
 (fn [db [_]]
   (get-in db [:profiles (:current-user-id db)])))

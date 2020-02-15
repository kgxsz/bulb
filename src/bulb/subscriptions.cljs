(ns bulb.subscriptions
  (:require [re-frame.core :as re-frame]))


(re-frame/reg-sub
 :initialising?
 (fn [db [_]]
   (let [routing-not-initialised? (not (contains? db :route))
         current-user-id-not-received? (not (contains? db :current-user-id))]
     (or routing-not-initialised?
         current-user-id-not-received?))))


(re-frame/reg-sub
 :authorised?
 (fn [db [_]]
   (when (contains? db :current-user-id)
     (some? (:current-user-id db)))))


(re-frame/reg-sub
 :authorising?
 (fn [db [_]]
   (:authorising? db)))


(re-frame/reg-sub
 :deauthorising?
 (fn [db [_]]
   (:deauthorising? db)))


(re-frame/reg-sub
 :error?
 (fn [db [_]]
   (:error? db)))


(re-frame/reg-sub
 :route
 (fn [db [_]]
   (:route db)))


(re-frame/reg-sub
 :profile
 (fn [db [_]]
   (get-in db [:profile (:current-user-id db)])))


(re-frame/reg-sub
 :title
 (fn [db [_ i]]
   (get-in db [:grids (:current-user-id db) i :title])))


(re-frame/reg-sub
 :subtitle
 (fn [db [_ i]]
   (get-in db [:grids (:current-user-id db) i :subtitle])))


(re-frame/reg-sub
 :checked-dates
 (fn [db [_ i]]
   (get-in db [:grids (:current-user-id db) i :checked-dates])))


(re-frame/reg-sub
 :grid
 (fn [db [_ i]]
   (get-in db [:grids (:current-user-id db) i])))

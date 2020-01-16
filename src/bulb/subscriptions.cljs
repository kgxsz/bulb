(ns bulb.subscriptions
  (:require [re-frame.core :as re-frame]))


(re-frame/reg-sub
 :initialising?
 (fn [db [_]]
   (not
    (and (contains? db :route)
         (contains? db :current-user-id)))))


(re-frame/reg-sub
 :route
 (fn [db [_]]
   (:route db)))


(re-frame/reg-sub
 :authorised?
 (fn [db [_]]
   (when (contains? db :current-user-id)
     (some? (:current-user-id db)))))

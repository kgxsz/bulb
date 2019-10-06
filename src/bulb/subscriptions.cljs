(ns bulb.subscriptions
  (:require [re-frame.core :as re-frame]))


(re-frame/reg-sub
 :routing-initialised?
 (fn [db [_]]
   (:routing-initialised? db)))


(re-frame/reg-sub
 :route
 (fn [db [_]]
   (:route db)))


(re-frame/reg-sub
 :authorised?
 (fn [db [_]]
   (:authorised? db)))

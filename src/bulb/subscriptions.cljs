(ns bulb.subscriptions
  (:require [re-frame.core :as re-frame]))


(re-frame/reg-sub
 :authorised?
 (fn [db [_]]
   (:authorised? db)))

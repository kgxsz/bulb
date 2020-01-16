(ns bulb.events
  (:require [re-frame.core :as re-frame]
            [bulb.interceptors :as interceptors]
            [goog.string :as gstring]
            [goog.string.format]))


(re-frame/reg-event-fx
 :initialise
 [interceptors/log interceptors/schema]
 (fn [{:keys [db]} event]
   {:db {}
    :initialise-routing {}
    :command {:initialise {}}}))


(re-frame/reg-event-fx
 :route
 [interceptors/log interceptors/schema]
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
 [interceptors/log interceptors/schema interceptors/current-user-id]
 (fn [{:keys [db]} [_ query response]]
   (case (-> query keys first)
     :authorisation-details (let [client-id (:client-id response)
                                  host "https://github.com"
                                  path "login/oauth/authorize"
                                  query-params (gstring/format "client_id=%s" client-id)]
                              {:redirect {:url (gstring/format "%s/%s?%s" host path query-params)}})
     :profile (let [{:keys [profile]} response]
                (js/console.warn profile)
                {:db (assoc-in db [:profiles (:user-id profile)] profile)})
     {})))


(re-frame/reg-event-fx
 :query-failure
 [interceptors/schema]
 (fn [{:keys [db]} [_ query response]]
   (js/console.warn "QUERY FAILURE!" response)))


(re-frame/reg-event-fx
 :command-success
 [interceptors/log interceptors/schema interceptors/current-user-id]
 (fn [{:keys [db]} [_ command response]]
   (case (-> command keys first)
     :authorise {:update-route {:route :home}}
     :initialise (when-let [current-user-id (:current-user-id db)]
                   {:query {:profile {:user-id current-user-id}}})
     {})))


(re-frame/reg-event-fx
 :command-failure
 [interceptors/schema]
 (fn [{:keys [db]} [_ command response]]
   (js/console.warn "COMMAND FAILURE!" response)))


(re-frame/reg-event-fx
 :authorise
 [interceptors/log interceptors/schema]
 (fn [{:keys [db]} [_]]
   {:query {:authorisation-details {}}}))


(re-frame/reg-event-fx
 :deauthorise
 [interceptors/log interceptors/schema]
 (fn [{:keys [db]} [_]]
   {:command {:deauthorise {}}}))

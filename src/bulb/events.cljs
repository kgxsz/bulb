(ns bulb.events
  (:require [re-frame.core :as re-frame]
            [bulb.interceptors :as interceptors]
            [goog.string :as gstring]
            [goog.string.format]))


(re-frame/reg-event-fx
 :initialise
 [interceptors/schema]
 (fn [{:keys [db]} event]
   {:db {}
    :initialise-routing {}}))


(re-frame/reg-event-fx
 :route
 [interceptors/schema]
 (fn [{:keys [db]} [_ {:keys [route route-params query-params]}]]
   (let [db (-> db
                (assoc :route route)
                (assoc :route-params route-params)
                (assoc :query-params query-params))]
     (case route
       :home {:db db
              :query {:profile {}
                      :grids {}}}
       :grids {:db db
               :query {:profile {}
                       :grids {}}}
       :authorisation {:db (assoc db :authorising? true)
                       :command {:authorise (select-keys query-params [:code])}}
       :unknown {:db db
                 :query {:profile {}}}
       {:db db}))))


(re-frame/reg-event-fx
 :query-success
 [interceptors/schema interceptors/current-user-id]
 (fn [{:keys [db]} [_ query response]]
   (case (-> query keys first)
     :authorisation-details (let [client-id (:client-id response)
                                  host "https://github.com"
                                  path "login/oauth/authorize"
                                  query-params (gstring/format "client_id=%s" client-id)]
                              {:redirect {:url (gstring/format "%s/%s?%s" host path query-params)}})
     :profile {:db (merge db response)}
     :grids {:db (merge db response)}
     {})))


(re-frame/reg-event-fx
 :query-failure
 [interceptors/schema]
 (fn [{:keys [db]} [_ query response]]
   {:db (assoc db :error? true)}))


(re-frame/reg-event-fx
 :command-success
 [interceptors/schema interceptors/current-user-id]
 (fn [{:keys [db]} [_ command response]]
   (case (-> command keys first)
     :authorise {:db (assoc db :authorising? false)
                 :update-route {:route :home}}
     :deauthorise {:db (assoc db :deauthorising? false)
                   :update-route {:route :home}}
     {})))


(re-frame/reg-event-fx
 :command-failure
 [interceptors/schema]
 (fn [{:keys [db]} [_ command response]]
   {:db (assoc db :error? true)}))


(re-frame/reg-event-fx
 :authorise
 [interceptors/schema]
 (fn [{:keys [db]} [_]]
   {:db (assoc db :authorising? true)
    :query {:authorisation-details {}}}))


(re-frame/reg-event-fx
 :deauthorise
 [interceptors/schema]
 (fn [{:keys [db]} [_]]
   {:db (assoc db :deauthorising? true)
    :command {:deauthorise {}}}))


(re-frame/reg-event-fx
 :update-route
 [interceptors/schema]
 (fn [{:keys [db]} [_ route]]
   {:update-route {:route route}}))


(re-frame/reg-event-fx
 :toggle-checked-date
 [interceptors/schema]
 (fn [{:keys [db]} [_ i checked-date]]
   (let [path [:grids (:current-user-id db) i :checked-dates]
         checked-dates (get-in db path)]
     (if (contains? checked-dates checked-date)
       {:db (update-in db path disj checked-date)}
       {:db (update-in db path conj checked-date)}))))

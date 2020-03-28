(ns bulb.schema
  (:require [cljs.spec.alpha :as s]
            [clojure.string :as string]
            [medley.core :as medley]))

;; Helpers

(def email #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,63}$")
(def date #"^20[0-9][0-9][0-1][0-9][0-3][0-9]$")


;; Generic

(s/def ::user-id int?)
(s/def ::epoch int?)


;; Operational

(s/def ::authorising? boolean?)
(s/def ::deauthorising? boolean?)
(s/def ::current-user-id (s/nilable ::user-id))
(s/def ::route #{:home :grids :authorisation :unknown})
(s/def ::route-params map?)
(s/def ::query-params map?)
(s/def ::error? boolean?)


;; Profile
(s/def ::name string?)
(s/def ::location string?)
(s/def ::created-at ::epoch)
(s/def ::avatar string?)
(s/def ::email (s/and string? (partial re-matches email)))
(s/def ::last-authorised ::epoch)
(s/def ::profile (s/map-of ::user-id
                           (s/keys :req-un [::user-id
                                            ::name
                                            ::location
                                            ::created-at
                                            ::avatar
                                            ::email
                                            ::last-authorised])))

;; Grids

(s/def ::title string?)
(s/def ::subtitle string?)
(s/def ::checked-date (s/and string? (partial re-matches date)))
(s/def ::checked-dates (s/every ::checked-date :kind set? :into #{}))
(s/def ::grid (s/keys :req-un [::title
                               ::subtitle
                               ::checked-dates]))
(s/def ::grids (s/map-of ::user-id (s/coll-of ::grid :kind vector? :into [])))


;; Db

(s/def ::db (s/keys :req-un []
                    :opt-un [::authorising?
                             ::deauthorising?
                             ::current-user-id
                             ::route
                             ::route-params
                             ::query-params
                             ::error?
                             ::profile
                             ::grids]))

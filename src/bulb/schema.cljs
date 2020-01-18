(ns bulb.schema
  (:require [cljs.spec.alpha :as s]
            [clojure.string :as string]
            [medley.core :as medley]))


(s/def ::current-user-id (s/nilable int?))

(s/def ::route #{:home :user :authorisation :unknown})

(s/def ::route-params map?)

(s/def ::query-params map?)

(s/def ::error? boolean?)


(s/def ::db (s/keys :req-un []
                    :opt-un [::current-user-id
                             ::route
                             ::route-params
                             ::query-params
                             ::error?]))

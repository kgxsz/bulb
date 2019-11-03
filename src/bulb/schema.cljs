(ns bulb.schema
  (:require [cljs.spec.alpha :as s]
            [clojure.string :as string]
            [medley.core :as medley]))


(s/def ::routing-initialised? boolean?)

(s/def ::authorised? boolean?)

(s/def ::route #{:home :user :authorisation :unknown})

(s/def ::route-params map?)

(s/def ::query-params map?)


(s/def ::db (s/keys :req-un [::routing-initialised?
                             ::authorised?
                             ::route
                             ::route-params
                             ::query-params]
                    :opt-un []))

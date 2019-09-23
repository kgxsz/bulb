(ns bulb.views.app)


(defn view
  []
  [:div
   {:class "app"}
   "App Ready!"])


(defn app
  []
  [view])

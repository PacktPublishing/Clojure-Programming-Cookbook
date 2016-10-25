
(require '[clojurewerkz.balagan.core :as b])

(def friend
  {:name "Nick"
   :birth {:year 1985 :place "Japan"}
   :nickname "nickthegreat"})


(b/select friend
          [:birth :year])


(b/with-paths
  friend
  [:birth]  (fn [value path]
              (println "I am born in " (value :place))))

(b/with-paths
  friend
  [:name] (fn [value _] (println "My name is " value)
  [:birth]  (fn [value path]
              (println "I am born in " (value :place)))))

(b/update friend
           [] (b/add-field :nice-friend true) )

(b/update friend
           (b/mk-path [:age]) #(- 2016 (-> % :birth :year)))

(b/update friend
          [] (b/remove-field :nickname))

(b/update friend
          [:birth] (b/remove-field :place))

(ns aws-examples.ec2-example
  (:require [amazonica.aws.ec2 :as ec2]
            [amazonica.core :as core]))
;;=> nil

(core/defcredential "Your Access Key" "Your Secret Access Key" "your region")
;;=> {:access-key "Your Access Key", :secret-key "Your Secret Access Key", :endpoint "your region"}

(ec2/describe-regions)
;;=> {:regions [{:region-name "ap-south-1", :endpoint "ec2.ap-south-1.amazonaws.com"}
;;=>                       .....
;;=>            {:region-name "ap-northeast-2", :endpoint "ec2.ap-northeast-2.amazonaws.com"}
;;=>            {:region-name "ap-northeast-1", :endpoint

(ec2/describe-instances)
;;=> {:reservations [{:reservation-id "r-8efe3c2b", :requester-id "226008221399",
;;=>                  :owner-id "182672843130", :group-names [], :groups [], ....

(defn get-instances-info[]
  (let [inst (ec2/describe-instances)]
    (->>
     (mapcat :instances (inst :reservations))
     (map
      #(vector
        [:node-name (->> (filter (fn [x] (= (:key  x)) "Name" ) (:tags %)) first :value)]
        [:status (get-in % [:state :name])]
        [:instance-id (:instance-id %)]
        [:private-dns-name (:private-dns-name %)]
        [:global-ip (-> % :network-interfaces first :private-ip-addresses first :association :public-ip)]
        [:private-ip (-> % :network-interfaces first :private-ip-addresses first  :private-ip-address)]))
     (map #(into {} %))
     (sort-by :node-name))))
;;=> #'aws-examples.ec2-example/get-instances-info

(get-instances-info)
;;=> ({:node-name "ECS Instance - amazon-ecs-cli-setup-my-cluster",
;;=>   :status "running",
;;=>   :instance-id "i-a1257a3e",
;;=>   :private-dns-name "ip-10-0-0-212.ap-northeast-1.compute.internal",
;;=>   :global-ip "54.199.234.18",
;;=>   :private-ip "10.0.0.212"}
;;=>  {:node-name "EcsInstanceAs
;;=>   :status "terminated",
;;=>   :instance-id "i-c5bbef5a",
;;=>   :private-dns-name "",
;;=>   :global-ip nil,
;;=>   :private-ip nil})

(ec2/start-instances :instance-ids '("i-c5bbef5a"))
;;=> {:starting-instances
;;=>  [{:previous-state {:code 80, :name "stopped"},
;;=>    :current-state {:code 0, :name "pending"},
;;=>    :instance-id "i-c5bbef5a"}]}

(ec2/stop-instances :instance-ids '("i-c5bbef5a"))
;;=> {:stopping-instances
;;=>  [{:previous-state {:code 16, :name "running"},
;;=>    :current-state {:code 64, :name "stopping"},
;;=>    :instance-id "i-c5bbef5a"}]}

(ns aws-examples.s3-example
  (:require [amazonica.core :as core]
            [amazonica.aws.s3 :as s3]
            [amazonica.aws.s3transfer :as s3-transfer]))
;;=> nil

(core/defcredential "Your Access Key" "Your Secret Access Key" "your region")
;;=> {:access-key "Your Access Key", :secret-key "Your Secret Access Key", :endpoint "your region"}

(s3/create-bucket "makoto-bucket-1")
;;=> {:name "makoto-bucket-1"}

(s3/create-bucket "makoto-bucket-2")
;;=> {:name "makoto-bucket-2"}

(s3/create-bucket "makoto-bucket-3")
;;=> {:name "makoto-bucket-3"}

(s3/list-buckets)
;;=> [{:creation-date #object[org.joda.time.DateTime 0x6a09e119 "2016-08-01T07:01:05.000+09:00"],
;;=>   :owner
;;=>   {:id "3d6e87f691897059c23bcfb88b17da55f0c9aa02cc2a44e461f1594337059d27",
;;=>    :display-name "tokoma1"},
;;=>   :name "makoto-bucket-1"}
;;=>  {:creation-date #object[org.joda.time.DateTime 0x7392252c "2016-08-01T17:35:30.000+09:00"],
;;=>   :owner
;;=>   {:id "3d6e87f691897059c23bcfb88b17da55f0c9aa02cc2a44e461f1594337059d27",
;;=>    :display-name "tokoma1"},
;;=>   :name "makoto-bucket-2"}
;;=>  {:creation-date #object[org.joda.time.DateTime 0x4d59b4cb "2016-08-01T17:38:59.000+09:00"],
;;=>   :owner
;;=>   {:id "3d6e87f691897059c23bcfb88b17da55f0c9aa02cc2a44e461f1594337059d27",
;;=>    :display-name "tokoma1"},
;;=>   :name "makoto-bucket-3"}]

(s3/delete-bucket "makoto-bucket-2")
;;=> nil

(s3/delete-bucket "makoto-bucket-3")
;;=> nil

(s3/list-buckets)
;;=> [{:creation-date #object[org.joda.time.DateTime 0x56387509 "2016-08-01T07:01:05.000+09:00"],
;;=> :owner {:id "3d6e87f691897059c23bcfb88b17da55f0c9aa02cc2a44e461f1594337059d27", :display-name "tokoma1"}, :name "makoto-bucket-1"}]


(s3/list-objects :bucket-name "makoto-bucket-1")
;;=> {:truncated? false, :bucket-name "makoto-bucket-1", :max-keys 1000, :common-prefixes [],
;;=> :object-summaries [{:storage-class "STANDARD", :bucket-name "makoto-bucket-1",
;;=> :etag "1e40658df92d353974eb249c9cc46c8c",
;;=> :last-modified #object[org.joda.time.DateTime 0x1b76029c "2016-08-01T07:01:16.000+09:00"],
;;=> :owner {:id "3d6e87f691897059c23bcfb88b17da55f0c9aa02cc2a44e461f1594337059d27",
;;=> :display-name "tokoma1"}, :key "test/hosts", :size 380}]}


(s3/get-object :bucket-name "makoto-bucket-1" :key "test/hosts")
;;=> {:bucket-name "makoto-bucket-1", :key "test/hosts",
;;=> :input-stream #object[com.amazonaws.services.s3.model.S3ObjectInputStream 0x24f810e9
;;=> ......
;;=> :last-modified #object[org.joda.time.DateTime 0x79ad1ca9 "2016-08-01T07:01:16.000+09:00"],
;;=> :cache-control nil, :http-expires-date nil, :content-length 380, :content-type "application/octet-stream",
;;=> :restore-expiration-time nil, :content-encoding nil, :expiration-time nil, :content-md5 nil,
;;=> :ongoing-restore nil}}

(slurp (:object-content (s3/get-object :bucket-name "makoto-bucket-1" :key "test/hosts")))
;;=> "127.0.0.1\tlocalhost\n127.0.1.1\tphenix\n\n# The following lines are desirable for IPv6 capable hosts\n::1     ip6-localhost ip6-loopback\nfe00::0 ip6-localnet\nff00::0 ip6-mcastprefix\nff02::1 ip6-allnodes\nff02::2 ip6-allrouters\n\n52.8.30.189 my-cluster01-proxy1 \n52.8.169.10 my-cluster01-master1 \n52.8.198.115 my-cluster01-slave01 \n52.9.12.12 my-cluster01-slave02\n\n52.8.197.100 my-node01\n"



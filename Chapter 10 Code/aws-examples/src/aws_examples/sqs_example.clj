(ns aws-examples.sqs-example
  (:require [amazonica.core :as core]
           [amazonica.aws.sqs :as sqs]))

(core/defcredential "AKIAIB27ZQVPLSIFBC7A"
  "J2c3orewif1vX/OaWXPD/ozkBCzIuXbQbemjxFYt" "ap-northeast-1")


(core/defcredential "AKIAJKK6BG6GCH5JVY5A"
  "YaD5733iemdfsI9Jh9O95YZ0EDNcx7NQ8uhmlhlP"
  "ap-northeast-1")

(sqs/create-queue :queue-name "makoto-queue"
              :attributes
                {:VisibilityTimeout 3000
                 :MaximumMessageSize 65536 
                 :MessageRetentionPeriod 1209600 
                 :ReceiveMessageWaitTimeSeconds 15}) 
;;=> {:queue-url "https://sqs.ap-northeast-1.amazonaws.com/xxxxxxxxxxxx/makoto-queue"}

(sqs/get-queue-attributes "makoto-queue")
;;=> {:QueueArn "arn:aws:sqs:ap-northeast-1:xxxxxxxxxxxx:makoto-queue", ...

(sqs/create-queue "DLQ")
;;=> {:queue-url "https://sqs.ap-northeast-1.amazonaws.com/xxxxxxxxxxxx/DLQ"}

(sqs/assign-dead-letter-queue (sqs/find-queue "makoto-queue")
                              (sqs/find-queue "DLQ") 10)
;;=> nil

(sqs/list-queues)
;;=> {:queue-urls
;;=>  ["https://sqs.ap-northeast-1.amazonaws.com/xxxxxxxxxxxx/DLQ"
;;=> "https://sqs.ap-northeast-1.amazonaws.com/xxxxxxxxxxxx/makoto-queue"]}


(sqs/find-queue "makoto-queue")
;;=> "https://sqs.ap-northeast-1.amazonaws.com/xxxxxxxxxxxx/makoto-queue"

(sqs/find-queue "DLQ")
;;=> "https://sqs.ap-northeast-1.amazonaws.com/xxxxxxxxxxxx/DLQ"

(sqs/send-message (sqs/find-queue "makoto-queue") "hello sqs from Clojure")
;;=> {:md5of-message-body "00129c8cc3c7081893765352a2f71f97", :message-id "690ddd68-a2f6-45de-b6f1-164eb3c9370d"}

(sqs/receive-message "makoto-queue")
;;=> {:messages [
;;=>             {:md5of-body "00129c8cc3c7081893765352a2f71f97",
;;=>              :receipt-handle "AQEB.....", :message-id "bd56fea8-4c9f-4946-9521-1d97057f1a06",
;;=>              :body "hello sqs from Clojure"}]}

(sqs/purge-queue :queue-url (sqs/find-queue "makoto-queue"))
;;=> nil

(sqs/delete-queue "makoto-queue")
;;=> nil

(sqs/delete-queue "DLQ")
;;=> nil




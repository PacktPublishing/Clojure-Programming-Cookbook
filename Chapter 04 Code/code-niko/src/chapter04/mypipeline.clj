(require '[clojure.core.async :as async])
(require '[cheshire.core :as json])
(require '[org.httpkit.client :as http])

(def concurrency 10)

(let [in (async/chan)
      out (async/chan)
      request-handler (fn [url out*]
                          (async/go
                            (println "Making request:" url)
                            (let [{:keys [status headers body error] :as resp} @(http/get url)
                                  body (json/parse-string body)]
                              (doseq [repo (body "items")]
                                (async/>! out (repo "clone_url"))))
; Finally close the channel to signal finished processing
                            (async/close! out*)))]
; Process `in` messages concurrently
  (async/pipeline-async concurrency out request-handler in)
; Push URLs to process
  (async/go
    (doseq [url (for [page (range 10)]
                  (str "https://api.github.com/search/repositories?q=language:clojure&page="
                                            (inc page)))]
      (async/>! in url)))

; Print results of processing
  (async/go-loop []
    (println (async/<! out))
    (recur)))

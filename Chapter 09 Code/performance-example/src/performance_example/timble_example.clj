(ns performance-example.timble-example
  (:require
   [clojure.core.reducers :as r]
   [clojure.java.io :as io]
    [taoensso.timbre :as timbre
      :refer (log  trace  debug  info  warn  error  fatal  report
              logf tracef debugf infof warnf errorf fatalf reportf
              spy get-env log-env)]
    [taoensso.timbre.appenders.core :as appenders]
    [taoensso.tufte :as tufte :refer (defnp p profiled profile)]
    ))
;;=> nil

(error "timbre error")
;;=> 16-07-18 14:40:12 phenix ERROR [performance-example.timble-example:15] - timbre error
;;=> nil

(info "timble info")
;;=> 16-07-18 14:40:49 phenix INFO [performance-example.timble-example:18] - timble info
;;=> nil

(def x 10)
;;=> #'performance-example.timble-example/x

(spy (* x 10))
16-07-15 00:06:50 phenix DEBUG [performance-example.timble-example:16] - (* x 10) => 100
;;=> nil

(timbre/set-level! :info)
;;=> {:level :info}

(debug "this is a debug message")
;;=> nil

(timbre/set-level! :debug)
;;=> {:level :debug}

(debug "this is a debug message")
;;=> 16-07-18 13:28:03 phenix DEBUG [performance-example.timble-example:30] - this is a debug message
;;=> nil

(defn ms-time[s]
  (double (/ (- (System/nanoTime) s) 1000000)))
;;=> #'performance-example.timble-example/ms-time

(defmacro with-perf-log [f s]
  `(let [st# (System/nanoTime)]
     ~s
     (~f (format "%s -> %10.3fms" '~s (ms-time st#)))))
;;=> #'performance-example.timble-example/with-perf-log

(with-perf-log info (Thread/sleep 1000))
;;=> 16-07-18 13:38:19 phenix INFO [performance-example.timble-example:?] - (Thread/sleep 1000) ->   1002.858ms
;;=> nil

(with-perf-log debug (Thread/sleep 1000))
;;=> 16-07-18 13:38:41 phenix DEBUG [performance-example.timble-example:?] - (Thread/sleep 1000) ->   1002.345ms
;;=> nil

(timbre/merge-config!
 {:appenders {:spit (appenders/spit-appender {:fname "/tmp/my-file.log"})}})
;;=> {:level :debug, :ns-whitelist [], :ns-blacklist [], :middleware [],
;;=> :timestamp-opts {:pattern "yy-MM-dd HH:mm:ss", :locale :jvm-default,
;;=> :timezone :utc}, :output-fn #function[taoensso.timbre/default-output-fn],
;;=> :appenders {:println {:enabled? true, :async? false, :min-level nil, :rate-limit nil,
;;=> :output-fn :inherit, :fn #function[taoensso.timbre.appenders.core/println-appender/fn--22162]},
;;=> :spit {:enabled? true, :async? false, :min-level nil, :rate-limit nil, :output-fn :inherit,
;;=> :fn #function[taoensso.timbre.appenders.core/spit-appender/self--22170]}}}

(debug "this is a debug message")
;;=> 16-07-18 14:38:37 phenix DEBUG [performance-example.timble-example:47] - this is a debug message
;;=> nil

(tufte/add-basic-println-handler! {})
;;=> #{:basic-println}

(profile {}
 (dotimes [_ 10]
   (p :apply  (apply + (map #(* % %) (range 1 1000001))))
   (p :map  (reduce + (map #(* % %) (range 1 1000001))))
   (p :reducers (r/fold + (r/map #(* % %) (range 1 1000001))))))
;;=>           pId      nCalls       Min        Max       MAD      Mean   Time% Time
;;=>          :map          10    97.0ms   148.02ms   17.27ms  113.45ms      37 1.13s
;;=>         :apply          10   95.26ms   149.86ms   13.86ms   106.6ms      35 1.07s
;;=>   :reducers          10   75.67ms   106.29ms    9.25ms   84.31ms      28 843.07ms
;;=>    Clock Time                                                          100 3.05s
;;=>Accounted Time                                                          100 3.04s
;;=> nil











(error "timbre info")
;;=> 16-07-15 00:05:17 phenix INFO [performance-example.timble-example:12] - timbre info
;;=> nil
(def x 10)
;;=> #'performance-example.timble-example/x
(spy (* x 10) )
16-07-15 00:06:50 phenix DEBUG [performance-example.timble-example:16] - (* x 10) => 100
;;=> nil


(timbre/set-level! :info)
;;=> {:level :info}
(debug "this is a debug message")
;;=> nil
(timbre/set-level! :debug)
;;=> {:level :debug}
(debug "this is a debug message")
;;=> 16-07-18 13:28:03 phenix DEBUG [performance-example.timble-example:30] - this is a debug message
;;=> nil

(timbre/set-config! {:level :debug})

(timbre/set-config! [:shared-appender-config :spit-filename "/tmp/my-file.log"] )
(timbre/set-config! [:appenders :spit :enabled? true] )
(timbre/merge-config!
  {:appenders {:spit (appenders/spit-appender {:fname "/path/my-file.log"})}})
(debug "this is a debug message")
   
(timbre/set-config! [:appenders :standard-out :enabled? true])
(timbre/set-config! [:appenders :spit :enabled? true])
(timbre/set-config! [:shared-appender-config :spit-filename log-file-name])

(defn mtime[s]
  (double (/ (- (System/nanoTime) s) 1000000)))
;;=> #'performance-example.timble-example/mtime

(defmacro with-perf-log [f s]
  `(let [st# (System/nanoTime)]
     ~s
     (~f (format "%s -> %10.3fms" '~s (mtime st#)))))
;;=> #'performance-example.timble-example/with-perf-log

(with-perf-log info (Thread/sleep 1000))
;;=> 16-07-18 13:38:19 phenix INFO [performance-example.timble-example:?] - (Thread/sleep 1000) ->   1002.858ms
;;=> nil
(with-perf-log debug (Thread/sleep 1000))
;;=> 16-07-18 13:38:41 phenix DEBUG [performance-example.timble-example:?] - (Thread/sleep 1000) ->   1002.345ms
;;=> nil

(let [s (System/nanoTime)]
  (Thread/sleep 1000)
  (info (mtime s)))



(tufte/add-basic-println-handler! {})

(defn get-x [] (Thread/sleep 500)             "x val")
(defn get-y [] (Thread/sleep (rand-int 1000)) "y val")

(profile ; Profile any `p` forms called during body execution
  {} ; Profiling options; we'll use the defaults for now
  (dotimes [_ 5]
    (p :get-x (get-x))
    (p :get-y (get-y))))

(profile {}
 (dotimes [_ 10]
   (p :apply  (apply + (map #(* % %) (range 1 1000001))))
   (p :map  (reduce + (map #(* % %) (range 1 1000001))))
   (p :reducers (r/fold + (r/map #(* % %) (range 1 1000001))))))
;;=>           pId      nCalls       Min        Max       MAD      Mean   Time% Time
;;=>          :map          10    97.0ms   148.02ms   17.27ms  113.45ms      37 1.13s
;;=>        :apply          10   95.26ms   149.86ms   13.86ms   106.6ms      35 1.07s
;;=>     :reducers          10   75.67ms   106.29ms    9.25ms   84.31ms      28 843.07ms
;;=>    Clock Time                                                          100 3.05s
;;=>Accounted Time                                                          100 3.04s
;;=> nil





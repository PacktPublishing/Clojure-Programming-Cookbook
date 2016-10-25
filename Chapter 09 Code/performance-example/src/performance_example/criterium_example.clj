(ns performance-example.criterium-example
    (:require [criterium.core :refer
             [bench with-progress-reporting report-result quick-bench benchmark quick-benchmark]]
            [clojure.core.reducers :as r]
            [criterium.stats :as stats]
            ))

(dotimes [_x 3]
  (time (apply + (map #(* % %) (range 1 1000001)))))
;;=> "Elapsed time: 158.034419 msecs"
;;=> "Elapsed time: 98.81966 msecs"
;;=> "Elapsed time: 92.167793 msecs"

(dotimes [_x 3]
  (time (reduce + (map #(* % %) (range 1 1000001)))))
;;=> "Elapsed time: 165.243023 msecs"
;;=> "Elapsed time: 103.53534 msecs"
;;=> "Elapsed time: 157.971972 msecs"

(dotimes [_x 3]
  (time (r/fold + (r/map #(* % %) (range 1 1000001)))))
;;=> "Elapsed time: 144.123838 msecs"
;;=> "Elapsed time: 89.670486 msecs"
;;=> "Elapsed time: 81.810268 msecs"

(bench (apply + (map #(* % %) (range 1 1000001))))
;;=> Evaluation count : 660 in 60 samples of 11 calls.
;;=>              Execution time mean : 92.660075 ms
;;=>     Execution time std-deviation : 1.823143 ms
;;=>    Execution time lower quantile : 91.480376 ms ( 2.5%)
;;=>    Execution time upper quantile : 98.511451 ms (97.5%)
;;=>                    Overhead used : 9.581787 ns
;;=> Found 5 outliers in 60 samples (8.3333 %)
;;=>     low-severe     5 (8.3333 %)
;;=>  Variance from outliers : 7.8662 % Variance is slightly inflated by outliers
;;=> (bench (reduce + (map #(* % %) (range 1 1000001))))
;;=> Evaluation count : 660 in 60 samples of 11 calls.
;;=>              Execution time mean : 91.840883 ms
;;=>     Execution time std-deviation : 1.425659 ms
;;=>    Execution time lower quantile : 90.996427 ms ( 2.5%)
;;=>    Execution time upper quantile : 95.662824 ms (97.5%)
;;=>                    Overhead used : 9.581787 ns
;;=> Found 2 outliers in 60 samples (3.3333 %)
;;=>     low-severe     2 (3.3333 %)
;;=>  Variance from outliers : 1.6389 % Variance is slightly inflated by outliers
;;=> (bench (r/fold + (r/map #(* % %) (range 1 1000001))))
;;=> Evaluation count : 840 in 60 samples of 14 calls.
;;=>              Execution time mean : 76.441841 ms
;;=>     Execution time std-deviation : 1.431449 ms
;;=>    Execution time lower quantile : 75.755372 ms ( 2.5%)
;;=>    Execution time upper quantile : 82.202533 ms (97.5%)
;;=>                    Overhead used : 9.581787 ns
;;=> Found 10 outliers in 60 samples (16.6667 %)
;;=>     low-severe     6 (10.0000 %)
;;=>     low-mild     4 (6.6667 %)
;;=>  Variance from outliers : 7.8176 % Variance is slightly inflated by outliers

(with-progress-reporting
  (bench (Thread/sleep 1000) :verbose))
;;=> Estimating sampling overhead
;;=> Warming up for JIT optimisations 10000000000 ...
;;=>   compilation occurred before 275187 iterations
;;=>   compilation occurred before 35488371 iterations
;;=>   compilation occurred before 69326040 iterations
;;=>   compilation occurred before 70151349 iterations
;;=>   compilation occurred before 105364533 iterations
;;=>   compilation occurred before 140577717 iterations
;;=> Estimating execution count ...
;;=> Sampling ...
;;=> Final GC...
;;=> Checking GC...
;;=> Finding outliers ...
;;=> Bootstrapping ...
;;=> Checking outlier significance
;;=> Warming up for JIT optimisations 10000000000 ...
;;=>   compilation occurred before 1 iterations
;;=> Estimating execution count ...
;;=> Sampling ...
;;=> Final GC...
;;=> Checking GC...
;;=> Finding outliers ...
;;=> Bootstrapping ...
;;=> Checking outlier significance
;;=> amd64 Linux 3.16.0-38-generic 4 cpu(s)
;;=> Java HotSpot(TM) 64-Bit Server VM 25.65-b01
;;=> Runtime arguments: -Dfile.encoding=UTF-8 -XX:+TieredCompilation -XX:TieredStopAtLevel=1 -XX:-Omit;;=> StackTraceInFastThrow -Dclojure.compile.path=/home/makoto/clojure/clojure-packt-book/chapter09/cr;;=> iterium-example/target/classes -Dcriterium-example.version=0.1.0-SNAPSHOT -Dclojure.debug=false
;;=> Evaluation count : 780 in 60 samples of 13 calls.
;;=>       Execution time sample mean : 77.498879 ms
;;=>              Execution time mean : 77.517145 ms
;;=> Execution time sample std-deviation : 1.418071 ms
;;=>     Execution time std-deviation : 1.441261 ms
;;=>    Execution time lower quantile : 76.678226 ms ( 2.5%)
;;=>    Execution time upper quantile : 82.447959 ms (97.5%)
;;=>                    Overhead used : 9.491795 ns
;;=> 
;;=> Found 6 outliers in 60 samples (10.0000 %)
;;=>     low-severe     1 (1.6667 %)
;;=>     low-mild     5 (8.3333 %)
;;=>  Variance from outliers : 7.8102 % Variance is slightly inflated by outliers

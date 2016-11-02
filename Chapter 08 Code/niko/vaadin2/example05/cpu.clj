(ns cpu	
	(:use [clojure.java.shell :only [sh]])
	(:use [hara.io.scheduler]))

(def last-value
	(atom ""))

(defn collect-cpu-fn[t params]
	(let [
		; linux 
		command "grep 'cpu ' /proc/stat | awk '{usage=($2+$4)*100/($2+$4+$5)} END {print usage \"%\"}'"
		; macos
		; command "top -l 1 | head -n 10 | grep CPU | awk '{print $2}'"
		cpu (:out (sh "bash" "-c" command))]
	(dosync (reset! last-value cpu))))

(def cpu-task
{:handler collect-cpu-fn
 :schedule "/5 * * * * * *"
 :params {}})
(def sch1 (scheduler {:print-task cpu-task}))
(start! sch1)

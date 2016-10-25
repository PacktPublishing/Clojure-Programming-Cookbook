(ns custom-repl.server
	(:import [java.net InetAddress ServerSocket Socket SocketException]
        [java.io InputStreamReader OutputStreamWriter]
        [clojure.lang LineNumberingPushbackReader]))

(defn on-thread [f]
  (doto (new Thread f) (.start)))

(defn create-server 
  [accept-socket bind port]
    (let [
    	address (InetAddress/getByName bind)
    	ss (new ServerSocket port)
    	]
      (on-thread #(when-not (. ss (isClosed))
                    (try (accept-socket (. ss (accept)))
                         (catch SocketException e))
                    (recur)))
      ss))

(defn repl
  [ins outs]
    (binding [*ns* (create-ns 'user)
              *warn-on-reflection* false
              *out* (new OutputStreamWriter outs)]
      (let [eof (new Object)
            r (new LineNumberingPushbackReader (new InputStreamReader ins))]
        (loop [e (read r false eof)]
          (when-not (= e eof)
            (prn (eval e))
            (flush)
            (recur (read r false eof)))))))

(defn socket-repl 
  [s] (on-thread #(repl (. s (getInputStream)) (. s (getOutputStream)))))

(def server 
	(create-server socket-repl "0.0.0.0" 13579))

; (. server (close))
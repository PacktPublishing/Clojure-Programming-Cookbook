(ns asyncing.withchime
  (:import [javax.sound.sampled AudioSystem])
  (:require [chime :refer [chime-ch]]
          [clj-time.core :as t]
          [clojure.java.io :as io]
          [clojure.core.async :as a :refer [<! go-loop]]))

(defn play-sound [file]
 (let[ 
      sound (io/as-file file)
      input (AudioSystem/getAudioInputStream sound)
      clip (AudioSystem/getClip)]
  (doto clip
   (.open input)
   (.start))))

(let [chimes (chime-ch [(-> 2 t/seconds t/from-now)])]
  (a/<!! (go-loop []
          (when-let [msg (<! chimes)]
            (play-sound "resources/pendolino.wav")
            (recur)))))

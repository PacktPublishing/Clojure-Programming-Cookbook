  (defproject cucumbering "0.1.0-SNAPSHOT"

  ; :eval-in :leiningen
  ; :jvm-opts ["-Djava.library.path=opencv_lib"]
  :injections [(clojure.lang.RT/loadLibrary org.opencv.core.Core/NATIVE_LIBRARY_NAME)]
  
             ; "-Djava.library.path=%PATH%:/Applications/VirtualBox.app/Contents/MacOS"]
  :profiles {
    :dev {
      :dependencies [
        

        ; example 02
        [org.clojure/data.csv "0.1.3"]

        [environ "1.0.3"]
        [dk.ative/docjure "1.10.0"]

        ; not used ? 
        [cheshire "5.6.1"]
        
        ; example 06
        [cc.qbits/jet "0.7.9"]
        [com.novemberain/monger "3.0.2"]

        ; example 07
        [freactive.core "0.2.0-alpha1"]

        ; example 08 
        [org.apache.spark/spark-core_2.10 "1.5.0"]
        [yieldbot/flambo "0.7.0"]

        ; example 09
        [clojurewerkz/eep "1.0.0-beta1"]


        [opencv/opencv "2.4.13"]
        [opencv/opencv-native "2.4.13"]

      ]    
    }
    
  }
   ; :cucumber-feature-paths ["features01" "features02" "features03"]
   :cucumber-feature-paths ["features00"]

   :plugins [
      [org.clojars.punkisdead/lein-cucumber "1.0.5"]
      [lein-localrepo "0.5.2"]
    ]
  
  :dependencies [
    [org.clojure/clojure "1.9.0-alpha7"]]

  )
; The following file is written by Jonathan Watmough
; This file is free for any use, providing this notice is preserved.
;
; This file works with the sound system on a Mac Book Pro.
; Nothing else is tested.


; imports
(import '(java.lang Thread))
(import '(java.nio ByteBuffer ShortBuffer))
(import '(javax.sound.sampled DataLine AudioSystem LineEvent LineListener AudioFormat))
;(import '(javax.sound.sampled.DataLine Info))

; supported audio filetypes
(def filetypes (. AudioSystem (getAudioFileTypes)))
(print (str "Supported audio: " (seq filetypes)))
(newline)

; supported mixers
(def mixer-info (seq (. AudioSystem (getMixerInfo))))

; get mixer-info, name, description of each mixer
(def mixer-info-list
  (map #(let [m %] { :mixer-info m 
                     :name (. m (getName))
                     :description (. m (getDescription))}) mixer-info))

; create a desired pcm audio format
; -> float sampleRate, int sampleSizeInBits, int channels, boolean signed, boolean bigEndian
(def audio-format (new AudioFormat 44100 16 1 false true))
(def buffer-size (* 22050 2))   ; 44k = 1/2 sec x 2 bytes / sample mono

; set a ref for the open line
(def input (ref nil))

; "Built-in Microphone"  <-- we'll use this

; get the mixer info for the mic
(def mic-mixer-info
  (:mixer-info (first (filter #(= "Built-in Microphone" (:name %)) mixer-info-list))))

; get the built in mic mixer
(def mic (. AudioSystem (getMixer mic-mixer-info)))

; get the supported source and target lines for the mixer
(def sources (seq (. mic (getSourceLineInfo))))   ; nil
(def targets (seq (. mic (getTargetLineInfo))))   ; (interface TargetDataLine supporting 72 audio formats)

; get a target line
(def line-info (first targets))
(def mic-line (. mic (getLine line-info)))

; add a line listener for events on the line
(. mic-line (addLineListener
              (proxy [LineListener] []
                (update [evt]
                  (do (print "Event: " (. evt (getType)))
                      (newline)
                      (. *out* (flush)))))))

; check if we can get this format from the built in mic
(. mic-line (open format buffer-size))

; start the input
(. mic-line (start))	
 
; try looping and counting available samples

(def f (future (do
(dotimes [i 100]
  (print "Available data: " (. mic-line (available)))
  (newline)
  (. *out* (flush))
  (let [buffer  (make-array (. Byte TYPE) 2048)
        bcount  (. mic-line (read buffer 0 2048))
        bbyte   (. ByteBuffer (wrap buffer))
        bshort  (. bbyte (asShortBuffer))
        ]
    (print "Read: " bcount " bytes. Buffer state:" (str bshort))
    (print " ... Converted to short: "  (str (. bshort (get 0))))
    (newline))
  (. Thread (sleep 20))))))  ; 1 milli sleep = 1/1000 of a sec = 44 samples

; stop the input
(. mic-line (stop))

; close mic
(. mic-line (close))

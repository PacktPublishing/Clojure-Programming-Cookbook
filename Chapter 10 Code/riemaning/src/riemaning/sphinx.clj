(import '[java.io File FileInputStream InputStream])
(import '[edu.cmu.sphinx.api Configuration SpeechResult LiveSpeechRecognizer StreamSpeechRecognizer])


(defn new-english-live-recognized []
	(let[ configuration (Configuration.)]
		(doto configuration
			(.setAcousticModelPath 	"resource:/edu/cmu/sphinx/models/en-us/en-us")
			(.setDictionaryPath 	"resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict")
			(.setLanguageModelPath 	"resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin"))
		(LiveSpeechRecognizer. configuration)))

(def recognizer 
	(new-english-live-recognized))

(.startRecognition recognizer true)

(def result  
	(.getResult recognizer))

(defn get-sentence[result]
	(clojure.string/join  
		" "
		(map #(.getSpelling (.getWord (.getPronunciation %))) (.getWords result))))

(get-sentence result)

(.stopRecognition recognizer)
(use 'clojure.test)

(import '[org.opencv.core Core Mat MatOfRect Point Rect Scalar])
(import '[org.opencv.objdetect CascadeClassifier]
        '[org.opencv.highgui Highgui])

(def image 
  (atom nil))
(def faceDetector 
  (atom nil))

(When #"^I load the image \"(.*?)\"$" [_image]
  (reset! image 
    (Highgui/imread _image)))

(When #"^setup the classifier for frontal face recognition$" []
  (reset! faceDetector 
    (CascadeClassifier. "opencv-data/lbpcascade_frontalface.xml")))

(Then #"^I can find (\d+) face in the loaded image$" [arg1]
  (let[faceDetections    (MatOfRect.)]
    (.detectMultiScale @faceDetector @image faceDetections)
    (let [ 
      rect-array (.toArray faceDetections)
      len (alength rect-array)]
      
      ; output for the fun of it
      (doseq [^Rect rect rect-array]
        (Core/rectangle @image 
          (Point. (.-x rect) (.-y rect)) 
          (Point. (+ (.-x rect) (.width rect)) (+ (.-y rect) (.height rect))) 
          (Scalar. 0 255 0)))

      (Highgui/imwrite "target/last-detection.png" @image)

    	(assert (= (Integer/parseInt arg1) len )))))
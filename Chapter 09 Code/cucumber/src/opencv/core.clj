; example 01
(import '[org.opencv.core Mat CvType Scalar])

(def m (Mat. 5 10 CvType/CV_8UC1 (Scalar. 0 0)))
(def mr1 (.row m 1))
(.setTo mr1 (Scalar. 1 0))
(def mc5 (.col m 5))
(.setTo mc5 (Scalar. 5 0))
(println (.dump m))


; example 02
(import '[org.opencv.core Mat Size CvType]
               '[org.opencv.highgui Highgui]
               '[org.opencv.imgproc Imgproc])
(def lena (Highgui/imread "fixtures/lena.png"))
(def blurred (Mat. 512 512 CvType/CV_8UC3))
(Imgproc/GaussianBlur lena blurred (Size. 5 5) 3 3)
(Highgui/imwrite "target/blurred.png" blurred)

; example 03
(import '[org.opencv.core Core Mat MatOfRect Point Rect Scalar CascadeClassifier])
(import '[org.opencv.objdetect CascadeClassifier]
	    '[org.opencv.highgui Highgui])

(def faceDetector 
	(CascadeClassifier. "opencv-data/lbpcascade_frontalface.xml"))
(def image 
	(Highgui/imread "fixtures/lena.png"))
(def faceDetections 
	(MatOfRect.))
(.detectMultiScale faceDetector image faceDetections)
(def rect-array (.toArray faceDetections))
(doseq [^Rect rect rect-array]
	(Core/rectangle image (Point. (.-x rect) (.-y rect)) (Point. (+ (.-x rect) (.width rect)) (+ (.-y rect) (.height rect))) (Scalar. 0 255 0)))
(Highgui/imwrite "target/detection.png" image)
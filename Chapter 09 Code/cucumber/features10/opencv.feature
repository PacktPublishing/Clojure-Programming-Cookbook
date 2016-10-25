Feature: OpenCV Face Recognition
	Playing around with face recognition and cucumber

# Scenario: Looking for faces in an image
# 	When I load the image "fixtures/lena.png"
# 	And setup the classifier for frontal face recognition
# 	Then I can find 1 face in the loaded image

# Scenario: Looking for faces in an image that is not from the opencv example
# 	When I load the image "fixtures/friendsbig.jpg"
# 	And setup the classifier for frontal face recognition
# 	Then I can find 6 face in the loaded image


Scenario: Looking for faces in an image that is not from the opencv example
	When I load the image "fixtures/nico.jpg"
	And setup the classifier for frontal face recognition
	Then I can find 1 face in the loaded image
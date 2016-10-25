# download opencv
# cmake 
# make -j8
# copy bin/opencv.jar in the opencv folder
# jar -cMf opencv-native-2413.jar native 
# where native folder is as below

# NicolassMacBook% tree opencvlib 
# opencvlib
# ├── native
# │   └── macosx
# │       └── x86_64
# │           └── libopencv_java2413.dylib
# ├── opencv-2413.jar
# └── opencv-native-2413.jar

# 3 directories, 3 files

lein localrepo install opencvlib/opencv-2413.jar opencv/opencv 2.4.13
lein localrepo install opencvlib/opencv-native-2413.jar opencv/opencv-native 2.4.13 
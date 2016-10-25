(ns chapter03.multimethods)

(defmulti volume :shape)
;;=> nil

(defmethod volume :cube [shape]
  (* (:length shape)(:length shape)(:length shape))
  )
;;=> #multifn[volume 0x4f6134c8]
(volume {:shape :cube :length 10})
;;=> 1000

(defmethod volume :rectangular-parallelepiped [shape]
  (* (:length shape)(:width shape)(:height shape))
  )
;;=> #multifn[volume 0x3695e4a4]
(volume {:shape :rectangular-parallelepiped :length 2 :width 3 :height 4})
;;=> 24

(defmethod volume :ball [shape]
  (/ (* (. Math PI) (:radius shape) (:radius shape)(:radius shape) 4.0) 3.0)
  )
;;=> #multifn[volume 0x3695e4a4]
(volume {:shape :ball :radius 2})
;;=> 33.510321638291124


(:shape {:shape :cube :length 10}) 
;;=> :cube
(defmethod volume :cube [shape]
  (* (:length shape)(:length shape)(:length shape))
  )
;;=> #multifn[volume 0x4f6134c8]

(volume {:shape :cone :length 10 :height 20})
IllegalArgumentException No method in multimethod 'volume' for dispatch value: :cone  clojure.lang.MultiFn.getFn (MultiFn.java:156)

(defmethod volume :default [shape] nil)
;;=> #multifn[volume 0x4f6134c8]
(volume {:shape :cone :length 10 :height 20})
;;=> nil

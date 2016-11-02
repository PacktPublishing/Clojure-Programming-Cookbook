(ns ex1
   (:import 
           [java.util Date GregorianCalendar]
           [com.vaadin.ui.components.calendar.event CalendarEvent$EventChangeListener BasicEvent]
           [com.vaadin.ui
            VerticalLayout
            Calendar]))

; (def event-change-fn 
;   (proxy [CalendarEvent$EventChangeListener] []
;     (eventChange [eventChangeEvent]
;       (println eventChangeEvent))))

(def event-change-fn 
  (reify CalendarEvent$EventChangeListener
    (eventChange [this eventChangeEvent]
      (println eventChangeEvent))))


(def sample-event 
  (let [ 
  start (GregorianCalendar.)
  end (GregorianCalendar.)]
  (.add end java.util.Calendar/HOUR 2)
  (doto 
    (BasicEvent. 
      "Birthday Part7" 
      "At Ren's place" 
      (.getTime start) 
      (.getTime end)) 
    ; )))
    (.addEventChangeListener event-change-fn))))

(defn calendar [] 
  (let[ cal (Calendar. "Today Calendar")]
    (doto cal
      (.setWidth "600px")
      (.setHeight "500px")
      (.setStartDate (Date.))
      (.setEndDate (Date.))
      (.addEvent sample-event)
      )))

(defn main [app]
    (let [
      layout  (VerticalLayout.)
      calendar (calendar)
      ]
      (doto (VerticalLayout.)
        (.addComponent calendar))))
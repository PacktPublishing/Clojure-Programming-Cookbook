(ns ex1
   (:import
           [com.vaadin.ui
            AbstractComponentContainer
            Button
            Button$ClickEvent
            Button$ClickListener
            Component
            GridLayout
            Label
            Window
            Window$CloseListener
            HorizontalSplitPanel
            Panel
            Tree
            VerticalLayout
            VerticalSplitPanel
            Window]))

(defonce planets [["Mercury" []]
                  ["Venus" []]
                  ["Earth" ["The Moon"]]
                  ["Mars" ["Phobos" "Deimos"]]
                  ["Jupiter" ["Io" "Europe" "Ganymedes" "Callisto"]]
                  ["Saturn" ["Titan" "Tethys" "Dione" "Rhea" "Iapetus"]]
                  ["Uranus" ["Miranda" "Ariel" "Umbriel" "Titania" "Oberon"]]
                  ["Neptune" ["Triton" "Proteus" "Nereid" "Larissa"]]])

(defn add-moons [tree planet moons]
  (reduce (fn [tree moon]
            (doto tree
              (.addItem moon)
              (.setParent moon planet)
              (.setChildrenAllowed moon false)))
          tree
          moons))

(defn add-planet [tree [planet moons]]
  (.addItem tree planet)
  (if (empty? moons)
    (.setChildrenAllowed tree planet false)
    (do
      (add-moons tree planet moons)
      (.expandItemsRecursively tree planet)))
  tree)

(defn planet-tree []
  (let [tree (Tree. "The Planets and Major Moons")]
    (reduce add-planet
            tree
            planets)))

(defn main [app]
    (let [layout  (VerticalLayout.)
          panel (Panel. "Panel for split panel III")
          hsplit (HorizontalSplitPanel.)]
      (.setContent panel hsplit)
      (.setSizeFull layout)
      (.setFirstComponent hsplit (planet-tree))
      (.setSecondComponent hsplit (Label. "Hello this is a wonderful world !"))
      (doto (VerticalLayout.)
        (.addComponent panel))))

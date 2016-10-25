(use 'prc)
(let [input-values (range -6.0 6.0 0.5)]
  (prc/line-chart
   "Trigonometry"
   {
    "sin" (map #(Math/sin %) input-values)
    "cos" (map #(Math/cos %) input-values)
    "tan"(map #(Math/tan %) input-values)
  }
   {:labels input-values}))

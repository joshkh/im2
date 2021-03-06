(ns re-frame-boiler.components.table
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame :refer [subscribe dispatch]]
            [dommy.core :as dommy :refer-macros [sel sel1]]))

(defn handle [expanded? e]
  (let [props (reagent/props e)
        node  (sel1 (reagent/dom-node e) :.im-target)]
    (.configure js/imtables "TableCell.IndicateOffHostLinks" false)
    (if @expanded? (-> (.loadTable js/imtables
                                   node
                                   (clj->js {:start 0 :size 25})
                                   (clj->js {:service {:root "www.flymine.org/query"}
                                             :query   props
                                             :TableCell {:IndicateOffHostLinks false}}))
                       (.then (fn [success] nil)
                              (fn [error]
                                (.error js/console error)))))))

(defn main [_ & [expanded]]
  (reagent/create-class
    (let [expanded? (reagent.core/atom expanded)]
      {:component-did-mount  (partial handle expanded?)
       :component-did-update (partial handle expanded?)
       :reagent-render       (fn [query]
                               [:div
                                {:on-click #(reset! expanded? true)}
                                (if @expanded?
                                  [:div.imtables [:div.im-target]]
                                  [:div (:title query)])])})))

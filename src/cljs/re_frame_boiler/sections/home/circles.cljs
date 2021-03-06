(ns re-frame-boiler.sections.home.circles
  (:require [re-frame.core :as re-frame]
            [re-frame-boiler.components.search :as search]))

(defn searchbox []
[:div.search
  [search/main]
  [:div.info
   [:svg.icon.icon-info [:use {:xlinkHref "#icon-info"}]]
   " Search for genes, proteins, pathways, ontology terms, authors, etc."]])

(defn lists []
  [:div.feature.lists
  [:h3 "Lists"]
   [:div.piccie [:svg.icon.icon-summary [:use {:xlinkHref "#icon-summary"}]] ]
    [:div [:a {:href "nope"} "View"]
    [:a {:href "nope"} "Upload"]]
   ])


 (defn templates []
   [:div.feature.templates
   [:h3 "Templates"]
    [:div.piccie [:svg.icon.icon-search [:use {:xlinkHref "#icon-search"}]] ]
     [:div [:a {:href "nope"} "Browse"]]
    ])

(defn help []
  [:div.feature.help
  [:h3 "Help"]
   [:div.piccie [:svg.icon.icon-summary [:use {:xlinkHref "#icon-eh"}]] ]
    [:div [:a {:href "nope"} "Tour"]
    [:a {:href "nope"} "Docs/Help"]]
   ])


(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [:main.home
       [searchbox]
       [:div.features
        [lists]
        [templates]
        [help]
        ]

       ])))

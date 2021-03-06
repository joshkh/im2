(ns imcljs.assets
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [ajax.core :refer [GET POST]]
            [cljs-http.client :as http]
            [imcljs.utils :as utils :refer [cleanse-url]]
            [cljs.core.async :refer [put! chan <! >! timeout close!]]))

(defn templates
  "Returns the results of a quicksearch"
  [{root :root token :token}]
  (go (:templates (:body (<! (http/get "http://www.flymine.org/query/service/templates"
                                       {:query-params      {:format "json"}
                                        :with-credentials? false}))))))

(defn lists
  "Returns the results of a quicksearch"
  [{root :root token :token}]
  (go (:lists (:body (<! (http/get (str (cleanse-url root) "/lists")
                                   {:query-params      {:format "json"}
                                    :with-credentials? false}))))))

(defn model
  "Returns the results of a quicksearch"
  [{root :root token :token}]
  (go (:classes (:model (:body (<! (http/get (str (cleanse-url root) "/model")
                                    {:query-params      {:format "json"}
                                     :with-credentials? false})))))))

(defn summary-fields
  "Returns the results of a quicksearch"
  [{root :root token :token}]
  (go (:classes (:body (<! (http/get (str (cleanse-url root) "/summaryfields")
                                   {:with-credentials? false}))))))
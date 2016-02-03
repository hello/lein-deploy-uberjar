(ns leiningen.deploy-uberjar
  (:require
    [leiningen.deploy :refer [deploy]]
    [leiningen.pom :as pom]
    [leiningen.uberjar :refer [uberjar]]))

(defn deploy-uberjar
  "Like `lein deploy`, but deploys an uberjar to the repository.
  Adds \"-standalone\" to the artifact name."
  ([project]
    (deploy-uberjar
      project
      (if (pom/snapshot? project) "snapshots" "releases")))
  ([project repository]
    (let [artifact (str (:name project) "-standalone")
          identifier (symbol (:group project) artifact)
          version (:version project)
          filename (uberjar project)]
      (deploy project repository identifier version filename))))

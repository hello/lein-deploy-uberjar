(defproject com.hello/lein-deploy-uberjar "0.1.0-SNAPSHOT"
  :description "Leiningen plugin to deploy an uberjar to Maven repository."
  :url "https://github.com/hello/lein-deploy-uberjar"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true
  :plugins [[s3-wagon-private "1.2.0"]]
  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag" "v" "--no-sign"]
                  ["deploy"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]]
  :deploy-branches ["master"]
  :repositories [["releases" {:url "s3p://hello-maven/release/"
                              :username :env/aws_access_key_id
                              :passphrase :env/aws_secret_key
                              :sign-releases false}]
                 ["snapshots" {:url "s3p://hello-maven/snapshot/"
                               :username :env/aws_access_key_id
                               :passphrase :env/aws_secret_key}]])

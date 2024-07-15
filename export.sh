set -e
mvn -pl . clean install
mvn -pl blog-sdk clean install
mvn -pl blog-admin-plugin clean install -Pexport,\!test
mvn -pl blog-web-plugin clean install -Pexport,\!test
mvn -pl blog-theme clean install -Pexport,\!test
ezy.sh package
ezy.sh export

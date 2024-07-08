mvn -pl . clean install & ^
mvn -pl blogproject-sdk clean install & ^
mvn -pl blogproject-admin-plugin clean install -Pexport,\!test & ^
mvn -pl blogproject-web-plugin clean install -Pexport,\!test & ^
mvn -pl blogproject-theme clean install -Pexport,\!test & ^
ezy.bat package & ^
ezy.bat export

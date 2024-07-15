mvn -pl . clean install & ^
mvn -pl myblog-sdk clean install & ^
mvn -pl myblog-admin-plugin clean install -Pexport,\!test & ^
mvn -pl myblog-web-plugin clean install -Pexport,\!test & ^
mvn -pl myblog-theme clean install -Pexport,\!test & ^
ezy.bat package & ^
ezy.bat export

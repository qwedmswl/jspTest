#D:\A_TeachingMaterial\6.JspSpring\workspace\jsp\deploy\deploy *참고*

cd /d/A_TeachingMaterial/6.jspSpring/deploy/jsp

git pull

mvn clean

mvn package

mv ./target/jsp-0.0.1-SNAPSHOT.war ./target/jsp.war

/d/B_Util/5.ApacheTomcat/apache-tomcat-7.0.73-windows-x64/apache-tomcat-7.0.73/bin/shutdown.sh

#삭제하기
rm /d/B_Util/5.ApacheTomcat/apache-tomcat-7.0.73-windows-x64/apache-tomcat-7.0.73/webapps/jsp.war

#폴더 삭제하기
rm -rf /d/B_Util/5.ApacheTomcat/apache-tomcat-7.0.73-windows-x64/apache-tomcat-7.0.73/webapps/jsp

cp ./target/jsp.war /d/B_Util/5.ApacheTomcat/apache-tomcat-7.0.73-windows-x64/apache-tomcat-7.0.73/webapps

/d/B_Util/5.ApacheTomcat/apache-tomcat-7.0.73-windows-x64/apache-tomcat-7.0.73/bin/startup.sh
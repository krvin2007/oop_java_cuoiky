@echo off
cd /d "%~dp0"
java -cp "target/classes;lib\h2-2.2.224.jar;lib\mysql-connector-j-9.7.0.jar" com.mycompany.quanlygara.view.ConsoleView

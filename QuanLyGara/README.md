# QuanLyGara

## Cách chạy

1. Mở terminal tại thư mục QuanLyGara.
2. Chạy lệnh:
   - javac -encoding UTF-8 -cp "lib\h2-2.2.224.jar;lib\mysql-connector-j-9.7.0.jar" -d target/classes $(Get-ChildItem -Recurse -Filter *.java -Path src/main/java | ForEach-Object { '"' + $_.FullName + '"' })
   - java -cp "target/classes;lib\h2-2.2.224.jar;lib\mysql-connector-j-9.7.0.jar" com.mycompany.quanlygara.view.ConsoleView

Hoặc chạy file:
- run.bat

## Tài khoản mẫu
- admin / admin
- ketoan / 123456
- thukho / 123456
- thomay / 123456

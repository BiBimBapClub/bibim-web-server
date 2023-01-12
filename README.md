# 비빔밥 동아리 웹사이트
## 서버 실행 커맨드
```
java -version # java 11 이상 버전 필요!
cd {project_root}
./gradlew build -x check
java -jar ./build/libs/bibimweb-0.0.1-SNAPSHOT.jar

# 업로드 되있는 jar만 실행
cd bibim-web-server # 프로젝트 폴더로 이동
java -jar ./jar/server.jar
```
localhost:8080에 서버가 올라갑니다!

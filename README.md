# encrypt

This service uses the Advanced Encryption Standard for offer good defenses against various attack techniques.



## encrypt Project
### Dev tools
- Java 11
- Gradle

### Build & Run Project
- build
```bash
./gradlew clean build
```

- Run
```bash
# run app by using Gradle
./gradlew bootRun

# run app by using Jar
java -jar build/libs/encrypt-0.0.1.jar
```



- docker
```bash

// 建立映像檔
docker build --rm -t encrypt .

// 映像檔建立 refernece
docker tag encrypt rockexe0000/encrypt

// 映像檔推送到 docker hub
docker image push rockexe0000/encrypt

// 啟動容器
docker container run -d -p 8080:8080 rockexe0000/encrypt


```



### APIs & doc
- Swagger doc: http://localhost:8080/swagger-ui/index.html#/

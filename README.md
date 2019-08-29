# AgeCorrector
年齢データを修正するバッチモジュールです。

#### MySQL情報を環境変数に設定
```
$ export SPRING_DATASOURCE_URL=jdbc:mysql://<hostname>:<port>/<database>;
$ export SPRING_DATASOURCE_USERNAME=<username>;
$ export SPRING_DATASOURCE_PASSWORD=<password>;
```
#### ビルドと実行
```
$ ./gradlew clean build
$ java -jar build/libs/agecorrector.jar
```

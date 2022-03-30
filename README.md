# Protobuf Kotlin Android Sample

This is a sample project to showcase the new [Kotlin support for protocol buffers](https://developers.googleblog.com/2021/11/announcing-kotlin-support-for-protocol.html).

## Libraries 

* [Retrofit](https://square.github.io/retrofit/) - used for http android client
* [Protobuf gradle plugin - github](https://github.com/google/protobuf-gradle-plugin)
* [Ktor](https://ktor.io/) - backend server to return a simple result

## Module Structure

![](docs/module-structure.png)

## Quick File Links

* [Dog proto file](https://github.com/plusmobileapps/protobuf-kotlin-sample/blob/main/model/src/main/proto/dog.proto)
* [Backend endpoint returning protobuf](https://github.com/plusmobileapps/protobuf-kotlin-sample/blob/main/backend/src/main/java/com/plusmobileapps/backend/Application.kt)
* Client [retrofit protobuf creation](https://github.com/plusmobileapps/protobuf-kotlin-sample/blob/main/app/src/main/java/com/plusmobileapps/protobufkotlin/di/DI.kt) and [DogService](https://github.com/plusmobileapps/protobuf-kotlin-sample/blob/main/app/src/main/java/com/plusmobileapps/protobufkotlin/network/DogService.kt)
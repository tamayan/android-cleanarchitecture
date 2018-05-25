# Android CleanArchitecture Sample

## Directory

```tree
cleanarchitecture/
├── data
│   ├── datastore
│   │   ├── cloud
│   │   │   └── client
│   │   │       ├── okhttp
│   │   │       └── retrofit
│   │   │           └── service
│   │   └── disk
│   ├── entity
│   │   ├── json
│   │   └── realm
│   └── repository
├── di
│   ├── component
│   └── module
├── domain
│   ├── entity
│   ├── exception
│   ├── repository
│   └── usecase
│       └── base
├── presentation
│   ├── entity
│   ├── mvp
│   │   ├── presenter
│   │   └── view
│   └── ui
│       ├── activity
│       └── adapter
└── util
```

## Library

- [Dagger2](https://github.com/google/dagger)
- [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [RxJava](https://github.com/ReactiveX/RxJava)
- [RxKotlin](https://github.com/ReactiveX/RxKotlin)
- [RxBinding](https://github.com/JakeWharton/RxBinding)
- [Retrofit2](https://github.com/square/retrofit)
- [Moshi](https://github.com/square/moshi)
- [Timber](https://github.com/JakeWharton/timber)

### Android Support Libraries

- Constraint Layout
- Appcompat v7
- Recyclerview v7
- Support v4

## API

webサーバーを建てて、サーバーから `news-list.json` を受け取れるようにしてください。

URLやBasic認証の情報を `gradle.properties` に追加してください。

例)Localでデバッグする場合

```gradle.properties
## local
local_base_url=http://localhost:8000/
local_user_name=Basic ID
local_password=Basic パスワード
```

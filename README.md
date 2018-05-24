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

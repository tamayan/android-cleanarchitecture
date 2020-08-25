# Android Clean Architecture Sample

AndroidのClean Architectureサンプルです。

AACやAndroid Jetpackを参考に構成しています。

## ライブラリ

- DI (Dagger2)
- DataBase (Room)
- APIClient (Retrofit)
- JSON Parser (Moshi)
- Debug Log (Timber) 

## APIサーバ

`mock/news.json` を `/news` で取得可能にする必要があります。

local.propertiesを用意し、APIサーバへの接続先を追加してください。

```local.properties
# Local
local_base_url=http://192.168.10.106:3000/
local_user_name=
local_password=
```

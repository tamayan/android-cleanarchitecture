package com.example.cleanarchitecture.data.datastore.disk

import com.example.cleanarchitecture.data.datastore.ReadNewsDataStore
import com.example.cleanarchitecture.data.entity.realm.NewsRealmEntity
import com.example.cleanarchitecture.domain.entity.News
import io.reactivex.Completable
import io.reactivex.Single
import io.realm.Realm

/**
 * Created by tamayan on 2017/12/03.
 */

class DiskNewsDataStore : ReadNewsDataStore {

    override fun insertOrUpdate(newsList: List<News>): Completable {
        return Completable.create { emitter ->
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            try {
                realm.insertOrUpdate(newsList.map { NewsRealmEntity(it.id, it.title) })
                realm.commitTransaction()
                emitter.onComplete()
            } catch (e: Exception) {
                realm.cancelTransaction()
                emitter.onError(e)
            } finally {
                realm.close()
            }
        }
    }

    override fun fetch(id: Int): Single<News> {
        return Single.create {
            val realm = Realm.getDefaultInstance()
            val realmEntity = realm.where(NewsRealmEntityClass).equalTo(ID, id).findFirst()
            val news = News(realmEntity?.id ?: 0, realmEntity?.title ?: "no title")
            realm.close()
            it.onSuccess(news)
        }
    }

    override fun fetch(): Single<List<News>> {
        return Single.create { singleEmitter ->
            val realm = Realm.getDefaultInstance()
            val newsList = realm.where(NewsRealmEntityClass).findAll().map { realmEntity ->
                News(realmEntity.id ?: 0, realmEntity.title ?: "no title")
            }
            realm.close()
            singleEmitter.onSuccess(newsList)
        }
    }

    companion object {

        private val NewsRealmEntityClass = NewsRealmEntity::class.java

        private const val ID = "id"
    }
}
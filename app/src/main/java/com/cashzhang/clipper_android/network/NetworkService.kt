package com.cashzhang.clipper_android.network

import com.cashzhang.clipper_android.model.ClipboardEntity
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface NetworkService {

    @Headers("Content-Type: application/json")
    @POST("v1/clipboard")
    fun sendPasteText(
        @Body param: ClipboardEntity
    ): Completable

    companion object {
        private const val baseUrl = "http://192.168.110.40:8614/"

        private var gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        private val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        fun send(text: String): Completable {
            return INSTANCE.sendPasteText(ClipboardEntity(text))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.single())
        }

        private val INSTANCE: NetworkService = retrofit.create(NetworkService::class.java)
    }
}

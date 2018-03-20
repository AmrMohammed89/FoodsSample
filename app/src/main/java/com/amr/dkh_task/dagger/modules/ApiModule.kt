package com.amr.dkh_task.dagger.modules

import android.content.Context
import android.net.ConnectivityManager
import com.amr.dkh_task.BuildConfig
import com.amr.dkh_task.DKHApp
import com.amr.dkh_task.DefaultScheduler
import com.amr.dkh_task.SchedulerContract
import com.amr.dkh_task.data.net.ApiController
import com.amr.dkh_task.exception.NoNetworkException
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import isConnected
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Singleton
import javax.net.ssl.SSLPeerUnverifiedException

@Module
class ApiModule {


    @Provides
    @Singleton
    fun providesGson(): Gson =
            GsonBuilder()
                    .serializeNulls()
                    .create()

    @Provides
    @Singleton
    fun providesOkHttpClient(app: DKHApp): OkHttpClient {

        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor()
                        .apply {
                            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                            else HttpLoggingInterceptor.Level.NONE
                        })
                .addInterceptor { chain ->
                    if (!connectivityManager.isConnected) {
                        throw NoNetworkException
                    }
                    try {
                        val original = chain.request()

                        // Customize the request
                        val request = original.newBuilder().header("Content-Type", "application/json").build()
                        val response = chain.proceed(request)
                        response.cacheResponse()
                        // Customize or return the response
                        response
                    } catch (e: SocketTimeoutException) {
                        throw NoNetworkException
                    } catch (e: UnknownHostException) {
                        throw NoNetworkException
                    } catch (e: SSLPeerUnverifiedException) {
                        throw NoNetworkException
                    }
                }.build()
    }

    @Provides
    @Singleton
    fun providesAppApi(okHttpClient: OkHttpClient, gson: Gson): ApiController {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .build()

        return retrofit.create(ApiController::class.java)
    }

    @Provides
    @Singleton
    fun provideScheduler(): SchedulerContract = DefaultScheduler
}
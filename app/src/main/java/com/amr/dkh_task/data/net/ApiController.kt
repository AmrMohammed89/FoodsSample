package com.amr.dkh_task.data.net

import com.amr.dkh_task.data.net.model.FoodResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiController {

    @GET("/bins/kvdzh")
    fun getFoodList(): Observable<FoodResponse>
}
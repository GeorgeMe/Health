package com.is.health.api;

import com.is.health.entity.HealthInfoDetails;
import com.is.health.protocol.ClassifyResponse;
import com.is.health.protocol.HealthListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by George on 2016/5/22.
 */
public interface SeeApi {

    @GET("classify")
    Call<ClassifyResponse> classify();

    @GET("list")
    Call<HealthListResponse> list(@Query("id") Number id,@Query("page") Number page,@Query("rows") Number rows);

    @GET("show")
    Call<HealthInfoDetails> show(@Query("id") Number shop_id);

}

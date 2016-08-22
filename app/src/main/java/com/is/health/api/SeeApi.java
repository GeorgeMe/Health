package com.is.health.api;

import com.is.health.entity.BaseResponse;
import com.is.health.entity.HealthInfoDetails;
import com.is.health.protocol.ClassifyResponse;
import com.is.health.protocol.HealthListResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

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

    /**
     * 通过 MultipartBody和@body作为参数来上传
     * @param multipartBody MultipartBody包含多个Part
     * @return 状态信息
     */
    @POST("aabb.do")
    Call<BaseResponse<String>> uploadFileWithRequestBody(@Body MultipartBody multipartBody);

    @POST("aabb.do")
    @Multipart
    Observable<BaseResponse> uploadFileInfo(@PartMap Map<String, RequestBody> externalFileParameters) ;
}

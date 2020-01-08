package com.example.otour.data.source

import androidx.lifecycle.LiveData
import com.example.otour.data.source.response.BookingResponse
import com.example.otour.data.source.response.MainResponse
import com.example.otour.data.source.response.PackageResponse
import com.example.otour.data.source.response.UserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface RemoteService {

    @FormUrlEncoded
    @POST("auth/signin")
    fun login(
        @Header("Authorization") auth: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): LiveData<ApiResponse<MainResponse<UserResponse>>>

    @Multipart
    @POST("auth/signup")
    fun register(
        @Header("Authorization") auth: String,
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("role") role: RequestBody,
        @Part photo: MultipartBody.Part
    ): LiveData<ApiResponse<MainResponse<UserResponse>>>

    @Multipart
    @POST("user/update")
    fun updateUser(
        @Header("Authorization") auth: String,
        @Part("name") name: RequestBody,
        @Part("password") password: RequestBody,
        @Part photo: MultipartBody.Part
    ): LiveData<ApiResponse<MainResponse<UserResponse>>>

    @FormUrlEncoded
    @POST("user/update")
    fun updateUser(
        @Header("Authorization") auth: String,
        @Field("name") name: String,
        @Field("password") password: String,
        @Field("photo") photo: String
    ): LiveData<ApiResponse<MainResponse<UserResponse>>>

    @FormUrlEncoded
    @GET("package/detail/{packageId}")
    fun getDetailPackage(
        @Header("Authorization") auth: String,
        @Path("packageId") packageId: Int
    ): LiveData<ApiResponse<MainResponse<PackageResponse>>>

    @GET("package/promo")
    fun getPromoPackage(@Header("Authorization") auth: String): LiveData<ApiResponse<MainResponse<List<PackageResponse>>>>

    @GET("package/popular")
    fun getPopularPackage(@Header("Authorization") auth: String): LiveData<ApiResponse<MainResponse<List<PackageResponse>>>>

    @GET("package/popular")
    fun getBestPackage(@Header("Authorization") auth: String): LiveData<ApiResponse<MainResponse<List<PackageResponse>>>>

    @FormUrlEncoded
    @POST("package/rate")
    fun ratePackage(
        @Header("Authorization") auth: String,
        @Field("packageId") packageId: Int,
        @Field("rate") rate: Int
    ): LiveData<ApiResponse<MainResponse<PackageResponse>>>

    @FormUrlEncoded
    @POST("package/search")
    fun searchPackage(
        @Header("Authorization") auth: String,
        @Field("keyword") keyword: String?,
        @Field("cityId") cityId: Int?,
        @Field("price") price: Int?
    ): LiveData<ApiResponse<MainResponse<List<PackageResponse>>>>

    @FormUrlEncoded
    @GET("booking/detail/{bookingId}")
    fun getBookingDetail(
        @Header("Authorization") auth: String,
        @Path("bookingId") bookingId: Int
    ): LiveData<ApiResponse<MainResponse<BookingResponse>>>

    @FormUrlEncoded
    @POST("booking")
    fun bookingPackage(
        @Header("Authorization") auth: String,
        @Field("packageId") packageId: Int,
        @Field("amount") amount: Int,
        @Field("payment") payment: String
    ): LiveData<ApiResponse<MainResponse<BookingResponse>>>

    @FormUrlEncoded
    @POST("booking/purchase")
    fun purchaseBooking(
        @Header("Authorization") auth: String,
        @Field("bookingId") bookingId: Int,
        @Field("description") description: String
    ): LiveData<ApiResponse<MainResponse<BookingResponse>>>

    @GET("booking/history")
    fun getBookingHistory(@Header("Authorization") auth: String): LiveData<ApiResponse<MainResponse<List<BookingResponse>>>>
}
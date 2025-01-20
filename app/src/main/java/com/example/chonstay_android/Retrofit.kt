package com.example.chonstay_android

import android.util.Log
import androidx.navigation.NavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApiService {

    @FormUrlEncoded
    @POST("users/login")
    fun login(
        @Field("userEmail") userEmail: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("users/register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("userPassword") userPassword: String,
        @Field("phoneNumber") phoneNumber: String,
    ): Call<RegisterResponse>

    @GET("homes")
    fun getHomePreviewsAll(): Call<HomePreviewsResponse>

    @GET("homes")
    fun getHomePreviews(
        @Query("location") requestQuery: String
    ): Call<HomePreviewsResponse>
}

data class LoginResponse(
    val message: String,
    val user_id: Int?
)

data class RegisterResponse(
    val message: String
)

data class HomePreview(
    val homeName: String,
    val address: String,
    val homeImageUrl: String,
    val averageReview: Double
)

data class HomePreviewsResponse(
    val homePreviews: List<HomePreview>
)

object RetrofitClient {
    private const val BASE_URL = "https://my-app-742515757936.asia-northeast3.run.app/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userApiService: UserApiService = retrofit.create(UserApiService::class.java)
}

fun loginUser(navController: NavController, email: String, password: String) {
    val call = RetrofitClient.userApiService.login(email, password)

    call.enqueue(object : Callback<LoginResponse> {
        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
            if (response.isSuccessful) {
                val loginResponse = response.body()
                if (loginResponse != null) {
                    navController.navigate("ModeSelect")
                    Log.d(
                        "Login",
                        "Success: ${loginResponse.message}, User ID: ${loginResponse.user_id}"
                    )
                }
            } else {
                Log.d("Login", "Error: ${response.message()}")
            }
        }

        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            Log.d("Login", "Failure: ${t.localizedMessage}")
        }
    })
}

fun registerUser(
    navController: NavController,
    name: String,
    email: String,
    password: String,
    phoneNumber: String,
) {
    val call = RetrofitClient.userApiService.register(name, email, password, phoneNumber)

    call.enqueue(object : Callback<RegisterResponse> {
        override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
            if (response.isSuccessful) {
                val registerResponse = response.body()
                if (registerResponse != null) {
                    navController.navigate("ModeSelect")
                    Log.d("Register", "Success: ${registerResponse.message}")
                }
            } else {
                Log.d("Register", "Error: ${response.message()}")
            }
        }

        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
            Log.d("Register", "Failure: ${t.localizedMessage}")
        }
    })
}

fun getHomePreviewsAll() {
    val call = RetrofitClient.userApiService.getHomePreviewsAll()

    call.enqueue(object : Callback<HomePreviewsResponse> {
        override fun onResponse(
            call: Call<HomePreviewsResponse>,
            response: Response<HomePreviewsResponse>
        ) {
            if (response.isSuccessful) {
                val homePreviewsResponse = response.body()
                if (homePreviewsResponse != null) {
                    Value.stayList = homePreviewsResponse.homePreviews
                    Log.d("HomePreviews", "Success: ${homePreviewsResponse.homePreviews}")
                }
            } else {
                Log.d("HomePreviews", "Error: ${response.message()}")
            }
        }

        override fun onFailure(call: Call<HomePreviewsResponse>, t: Throwable) {
            // 네트워크 오류 또는 기타 실패 처리
            Log.d("HomePreviews", "Failure: ${t.localizedMessage}")
        }
    })
}
fun getHomePreviews(
    requestQuery: String,
    onComplete: (Boolean, List<HomePreview>?) -> Unit
) {
    val call = RetrofitClient.userApiService.getHomePreviews(requestQuery)

    call.enqueue(object : Callback<HomePreviewsResponse> {
        override fun onResponse(
            call: Call<HomePreviewsResponse>,
            response: Response<HomePreviewsResponse>
        ) {
            if (response.isSuccessful) {
                val homePreviewsResponse = response.body()
                if (homePreviewsResponse != null) {
                    onComplete(true, homePreviewsResponse.homePreviews) // 성공 시 데이터 반환
                    Log.d("HomePreviews", "Success: ${homePreviewsResponse.homePreviews}")
                } else {
                    onComplete(false, null) // 응답은 성공했지만 Body가 null인 경우
                }
            } else {
                onComplete(false, null) // 요청 실패
                Log.d("HomePreviews", "Error: ${response.message()}")
            }
        }

        override fun onFailure(call: Call<HomePreviewsResponse>, t: Throwable) {
            onComplete(false, null) // 네트워크 오류
            Log.d("HomePreviews", "Failure: ${t.localizedMessage}")
        }
    })
}



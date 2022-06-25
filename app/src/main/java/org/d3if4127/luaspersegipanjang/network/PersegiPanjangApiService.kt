package org.d3if4127.luaspersegipanjang.network

import com.google.gson.GsonBuilder
import org.d3if4127.luaspersegipanjang.model.About
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/amaliarizkiaputri/api_json/main.contohsoal.json/"


private val gson = GsonBuilder()
    .setLenient()
    .create()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()

interface PersegiPanjangApiService {
    @GET("contohsoal.json")
    suspend fun getPersegiPanjang(): Response<About>
}

object PersegiPanjangApi {
    val service: PersegiPanjangApiService by lazy {
        retrofit.create(PersegiPanjangApiService::class.java)
    }

    fun getPersegiPanjangUrl(nama: String): String {
        return "$BASE_URL$nama.png"
    }


}

enum class ApiStatus { LOADING, SUCCESS, FAILED}
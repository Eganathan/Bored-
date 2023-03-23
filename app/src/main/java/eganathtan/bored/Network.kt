package eganathtan.bored

import com.squareup.moshi.Json
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.GET

interface BoredApi {
    companion object {
        val url = "https://www.boredapi.com/api/"


        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val retrofitService: BoredApiService by lazy {
            retrofit.create(BoredApiService::class.java)

        }
    }
}

data class BoredActivity(
    @Field("activity") var activity: String? = null,
    @Field("type") var type: String? = null,
    @Field("participants") var participants: Int? = null,
    @Field("price") var price: Double? = null,
    @Field("link") var link: String? = null,
    @Field("key") var key: String? = null,
    @Field("accessibility") var accessibility: Double? = null
)

interface BoredApiService {
    @GET("activity")
    suspend fun getRandomActivity(): Response<BoredActivity?>

}
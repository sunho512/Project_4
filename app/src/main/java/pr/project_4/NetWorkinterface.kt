package pr.project_4

import pr.project_4.data.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.Call

interface NetWorkInterface {
    @GET("v2/search/image")
    fun searchData(
        @Header("Authorization") apiKey: String?,
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Call<SearchResponse>?
}
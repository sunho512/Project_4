package pr.project_4

import pr.project_4.data.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetWorkInterface {
    @GET("검색을 원하는 엔드포인트") // 실제 엔드포인트 URL을 여기에 추가해야 합니다.
    suspend fun searchData(
        @Query("query") query: String,
        @Query("sort") sort: String = "accuracy",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 50
    ): SearchResponse
}

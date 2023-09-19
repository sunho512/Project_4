package pr.project_4

import pr.project_4.data.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetWorkInterface {
    @GET("검색을 원하는 엔드포인트") // 실제 엔드포인트 URL을 여기에 추가해야 합니다.
    suspend fun searchData(
        @Query("query") query: String,
        @Query("sort") sort: String = "accuracy", // 기본 값으로 accuracy를 사용
        @Query("page") page: Int = 1, // 기본 값으로 1을 사용
        @Query("size") size: Int = 80 // 기본 값으로 80을 사용
    ): SearchResponse // YourResponseClass는 실제 응답 데이터 모델 클래스여야 합니다.
}

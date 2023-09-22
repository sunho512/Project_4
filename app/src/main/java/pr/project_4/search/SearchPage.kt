package pr.project_4.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import pr.project_4.Constants
import pr.project_4.NetWorkClient.SearchNetWork
import pr.project_4.data.Document
import pr.project_4.data.SearchResponse
import pr.project_4.data.Utils
import pr.project_4.databinding.FragmentSearchPageBinding
import pr.project_4.model.SearchItemModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPage : Fragment() {

    private lateinit var binding: FragmentSearchPageBinding
    private lateinit var mContext: Context
    private lateinit var adapter: SearchAdapter
    private lateinit var gridmanager: StaggeredGridLayoutManager

    private var resItems: ArrayList<SearchItemModel> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchPageBinding.inflate(inflater, container, false)

        setupViews()   // 뷰 초기 설정
        setupListeners() // 리스너 설정

        return binding.root
    }

    private fun setupViews() {
        // RecyclerView 설정
        gridmanager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView1.layoutManager = gridmanager

        adapter = SearchAdapter(mContext)
        binding.recyclerView1.adapter = adapter
        binding.recyclerView1.itemAnimator = null
    }

    private fun setupListeners() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.tvSearch.setOnClickListener {
            val query = binding.etSearch.text.toString()
            if (query.isNotEmpty()) {
                Utils.saveLastSearch(requireContext(), query)
                adapter.clearItem()
                fetchImageResults(query)
            } else {
                Toast.makeText(mContext, "검색어를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }

            imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
        }
    }

    private fun fetchImageResults(query: String) {
        SearchNetWork.searchData(Constants.AUTH_HEADER, query, "recency", 1, 80)
            ?.enqueue(object : Callback<SearchResponse?> {
                override fun onResponse(
                    call: Call<SearchResponse?>, response: Response<SearchResponse?>
                ) {
                    response.body()?.meta?.let { meta ->
                        if (meta.totalCount > 0) {
                            response.body()!!.documents.forEach { document ->
                                val title = document.displaySitename
                                val datetime = document.datetime
                                val url = document.thumbnailUrl
                                resItems.add(SearchItemModel(title, datetime, url))
                            }
                        }
                    }
                    adapter.items = resItems
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
                    Log.e("#아 화딱지나네", "응 실패야: ${t.message}")
                }
            })
    }
}
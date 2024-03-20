package com.yogawahyuy.recommendationmovie.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yogawahyuy.recommendationmovie.R
import com.yogawahyuy.recommendationmovie.adapter.HomeRecyclerAdapter
import com.yogawahyuy.recommendationmovie.adapter.SearchRecyclerAdapter
import com.yogawahyuy.recommendationmovie.databinding.FragmentHomeBinding
import com.yogawahyuy.recommendationmovie.databinding.FragmentSearchBinding
import com.yogawahyuy.recommendationmovie.viewmodel.MainViewModel
import com.yogawahyuy.recommendationmovie.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        searchViewModel = ViewModelProvider(requireActivity())[SearchViewModel::class.java]
        viewModel.loadTrendingAll()
        provideRecommendation()

        binding.btnSearch.setOnClickListener {
            val searchValue = binding.etSearch.text.toString()
            provideSearchResult(searchValue)
            binding.tvTitleSearch.text = "Result For $searchValue"
        }
    }
    private fun provideRecommendation(){
        val layMng = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rvSearch.layoutManager = layMng
        viewModel.observerTrendingAll().observe(requireActivity(), Observer{
            val searchAdapter = SearchRecyclerAdapter(
                requireContext(),
               it
            )
            binding.rvSearch.adapter = searchAdapter
            searchAdapter.setOnItemClickListener(object : SearchRecyclerAdapter.OnItemClickListener{
                override fun onItemClick(position: Int) {
                    val intent = Intent(requireContext(), DetailMovieActivity::class.java)
                    intent.putExtra("id",it.get(position).id)
                    startActivity(intent)
                }

            })
        })
    }
    private fun provideSearchResult(searchString: String){
        binding.rvSearch.removeAllViews()
        searchViewModel.loadSearchResult(searchString)
        val layMng = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rvSearch.layoutManager = layMng
        searchViewModel.observerSearchResult().observe(requireActivity(), Observer {
            if (it.isEmpty()){
                binding.tvEmpty.visibility = View.VISIBLE
                binding.rvSearch.visibility = View.GONE
            }else {
                binding.tvEmpty.visibility = View.GONE
                binding.rvSearch.visibility = View.VISIBLE
                val searchAdapter = SearchRecyclerAdapter(
                    requireContext(),
                    it
                )
                binding.rvSearch.adapter = searchAdapter
                searchAdapter.setOnItemClickListener(object :
                    SearchRecyclerAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        val intent = Intent(requireContext(), DetailMovieActivity::class.java)
                        intent.putExtra("id", it.get(position).id)
                        startActivity(intent)
                    }

                })
            }
        })
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
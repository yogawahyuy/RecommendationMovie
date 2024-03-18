package com.yogawahyuy.recommendationmovie.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogawahyuy.recommendationmovie.R
import com.yogawahyuy.recommendationmovie.adapter.HomeRecyclerAdapter
import com.yogawahyuy.recommendationmovie.databinding.FragmentHomeBinding
import com.yogawahyuy.recommendationmovie.util.BaseURL
import com.yogawahyuy.recommendationmovie.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var homeRecyclerAdapter: HomeRecyclerAdapter

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
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainCarousel.registerLifecycle(lifecycle)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModel.loadData()
        provideCarouselView()
        provideNowPlaying()
        provideTrending()
        provideTopRated()
    }

    private fun provideCarouselView(){
        val list = mutableListOf<CarouselItem>()
        val idList = mutableListOf<Int>()
        viewModel.observerUp().observe(requireActivity(), Observer {
            for (i in 0..10){
                list.add(
                    CarouselItem(
                    BaseURL().baseImageOri+it[i].posterPath,
                    it[i].originalTitle
                )
                )
                idList.add(it[i].id)
            }
            binding.mainCarousel.setData(list)
            binding.mainCarousel.carouselListener = object :CarouselListener{
                override fun onClick(position: Int, carouselItem: CarouselItem) {
                    val intent = Intent(requireContext(), DetailMovieActivity::class.java)
                    intent.putExtra("id",idList[position])
                    startActivity(intent)
                }
            }
        })
    }
    private fun provideNowPlaying(){
        val urlList = arrayListOf<String>()
        val idList = arrayListOf<Int>()
        val layMng = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.rvNowplaying.layoutManager = layMng
        viewModel.observerNP().observe(requireActivity(),Observer{
            for (i in it.indices){
                urlList.add(it[i].posterPath)
                idList.add(it[i].id)
            }
            homeRecyclerAdapter = HomeRecyclerAdapter(requireContext(),idList,urlList)
            binding.rvNowplaying.adapter = homeRecyclerAdapter
            homeRecyclerAdapter.setOnItemClickListener(object : HomeRecyclerAdapter.OnItemClickListener{
                override fun onItemClick(position: Int) {
                    val intent = Intent(requireContext(), DetailMovieActivity::class.java)
                    intent.putExtra("id",idList[position])
                    startActivity(intent)
                }

            })
        })

    }
    private fun provideTrending(){
        val urlList = arrayListOf<String>()
        val idList = arrayListOf<Int>()
        val layMng = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.rvTrending.layoutManager = layMng
        viewModel.observerTrendingAll().observe(requireActivity(),Observer{
            for (i in it.indices){
                urlList.add(it[i].posterPath)
                idList.add(it[i].id)
            }
            val homeRecyclerAdapter = HomeRecyclerAdapter(requireContext(),idList,urlList)
            binding.rvTrending.adapter = homeRecyclerAdapter
            homeRecyclerAdapter.setOnItemClickListener(object :HomeRecyclerAdapter.OnItemClickListener{
                override fun onItemClick(position: Int) {
                    val intent = Intent(requireContext(), DetailMovieActivity::class.java)
                    intent.putExtra("id",idList[position])
                    startActivity(intent)
                }

            })
        })

    }
    private fun provideTopRated(){
        val urlList = arrayListOf<String>()
        val idList = arrayListOf<Int>()
        val layMng = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.rvToprated.layoutManager = layMng
        viewModel.observerTopRated().observe(requireActivity(),Observer{
            for (i in it.indices){
                urlList.add(it[i].posterPath)
                idList.add(it[i].id)
            }
            val homeRecyclerAdapter = HomeRecyclerAdapter(requireContext(),idList,urlList)
            binding.rvToprated.adapter = homeRecyclerAdapter
        })

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
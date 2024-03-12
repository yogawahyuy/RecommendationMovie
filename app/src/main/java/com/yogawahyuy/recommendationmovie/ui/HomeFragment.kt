package com.yogawahyuy.recommendationmovie.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yogawahyuy.recommendationmovie.R
import com.yogawahyuy.recommendationmovie.databinding.FragmentHomeBinding
import com.yogawahyuy.recommendationmovie.util.BaseURL
import com.yogawahyuy.recommendationmovie.viewmodel.MainViewModel
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
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentHomeBinding

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
        setupViewModel()
    }

    private fun setupViewModel(){
        val list = mutableListOf<CarouselItem>()
        val viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModel.loadData()
        viewModel.observerNP().observe(requireActivity(), Observer {
            Log.d("hasil ViewModel", "setupViewModel: $it")
            for (i in it.indices){
                list.add(
                    CarouselItem(
                    BaseURL().baseImageOri+it[i].posterPath,
                    it[i].originalTitle
                )
                )
            }
            binding.mainCarousel.setData(list)
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
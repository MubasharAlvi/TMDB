package com.example.projectdemo.presentation.main.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.projectdemo.R
import com.example.projectdemo.data.remote.model.MovieModelClass
import com.example.projectdemo.databinding.FragmentMediaLibraryBinding
import com.example.projectdemo.presentation.main.adapter.RecycleviewAdapter
import com.example.projectdemo.presentation.main.viewmodels.MovieViewModel
import com.example.projectdemo.utils.ResourceApiState
import com.example.projectdemo.utils.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MediaLibraryFragment : Fragment(),RecycleviewAdapter.InterfaceClickListener {
    private var _binding: FragmentMediaLibraryBinding? = null
    private val viewModel by viewModels<MovieViewModel>()
    private lateinit var movieList: ArrayList<MovieModelClass>
    private lateinit var recyclerViewAdapter: RecycleviewAdapter
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.fragment_media_library,
                container,
                false
            )
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieList = ArrayList()

        binding.apply {
            deshboardLayoutId.setSafeOnClickListener {
                val actionDeshboardFragment =
                    MediaLibraryFragmentDirections.actionMediaLibraryFragmentToDeshBoardFragment3()
                findNavController().navigate(actionDeshboardFragment)
            }
            watchImgId.setSafeOnClickListener {
                val actionWatchFragment =
                    MediaLibraryFragmentDirections.actionMediaLibraryFragmentToWatchFragment3()
                findNavController().navigate(actionWatchFragment)
            }
        }
        init()
        lifecycleScope.launch {
            viewModel.apiStateFlow.collect {
                when (it) {
                    is ResourceApiState.Loading -> {
                        binding.progressbarId.visibility = View.VISIBLE
                    }
                    is ResourceApiState.Error -> {
                        binding.progressbarId.visibility = View.GONE
                        Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()

                    }
                    is ResourceApiState.Empty -> {
                        binding.progressbarId.visibility = View.GONE
                        Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                    }
                    is ResourceApiState.Success -> {
                        binding.progressbarId.visibility = View.GONE
                        for (data in it.response.results) {
                            movieList.add(data)
                        }
                        recycleviewFun(movieList)
                    }
                }
            }
        }

    }



    private fun init() {
        setColor()
        viewModel.movieFun("popular")
    }

    //        RecyclerView
    private fun recycleviewFun(movieList: ArrayList<MovieModelClass>) {
        binding.mediaRecyclerviewId.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        recyclerViewAdapter =
            RecycleviewAdapter(requireContext(), movieList,
                R.layout.item_layout_media,this)
        binding.mediaRecyclerviewId.adapter = recyclerViewAdapter
    }

    //    binding null
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setColor() {
        binding.apply {
            deshboardTxtId.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.light_white
                )
            )
            watchTxtId.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_white))
            mediaLibTxtId.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.light_white
                )
            )
            mediaLibTxtId.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow))
        }
    }

    override fun moviePlayer(movieModelListPosition: MovieModelClass, position: Int) {
        val actionInterfaceMoviewPlayer =
            MediaLibraryFragmentDirections.actionMediaLibraryFragmentToMoviePlayerFragment(movieModelListPosition)
        findNavController().navigate(actionInterfaceMoviewPlayer)
    }

}






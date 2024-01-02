package com.example.projectdemo.presentation.main.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectdemo.R
import com.example.projectdemo.data.remote.model.MovieModelClass
import com.example.projectdemo.databinding.FragmentDashboardBinding
import com.example.projectdemo.presentation.main.adapter.RecycleviewAdapter
import com.example.projectdemo.presentation.main.viewmodels.MovieViewModel
import com.example.projectdemo.utils.NetWorkCheck
import com.example.projectdemo.utils.ResourceApiState
import com.example.projectdemo.utils.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DeshBoardFragment : Fragment(), RecycleviewAdapter.InterfaceClickListener {
    private var _binding: FragmentDashboardBinding? = null
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
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_dashboard, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieList = ArrayList()
        binding.apply {
            searchviewIconId.setSafeOnClickListener {
                wieghtNotvisible()
            }
        }

        binding.apply {
            watchLayoutId.setSafeOnClickListener {
                val actionWatchFragment =
                    DeshBoardFragmentDirections.actionDeshBoardFragment3ToWatchFragment3()
                findNavController().navigate(actionWatchFragment)
            }
            mediaLibraryLayoutId.setSafeOnClickListener {
                val actionmeidaFragment =
                    DeshBoardFragmentDirections.actionDeshBoardFragment3ToMediaLibraryFragment()
                findNavController().navigate(actionmeidaFragment)
            }
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val imm: InputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(binding.searchView.getWindowToken(), 0)
//                    query?.let { viewModel.searchFun(it) }
                    query?.let { viewModel.searchFun(it) }
                    movieList.clear()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
        init()
//        if(NetWorkCheck(requireContext())==true){
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

    private fun wieghtvisible() {
        binding.searchView.visibility = View.GONE
        binding.headerId.visibility = View.VISIBLE
        binding.searchviewIconId.visibility = View.VISIBLE
    }

    private fun wieghtNotvisible() {
        binding.searchView.visibility = View.VISIBLE
        binding.headerId.visibility = View.GONE
        binding.searchviewIconId.visibility = View.GONE
    }

    private fun init() {
        setColor()
        binding.headerId.text = "Dashboard"
        viewModel.movieFun("popular")
    }

    private fun setColor() {
        binding.apply {
            deshboardTxtId.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow))
            watchTxtId.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_white))
            mediaLibTxtId.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.light_white
                )
            )
            listTxtId.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_white))
        }
    }

    //        RecyclerView
    private fun recycleviewFun(movieList: ArrayList<MovieModelClass>) {
        binding.dashboardRecycleId.layoutManager = LinearLayoutManager(context)
        recyclerViewAdapter = RecycleviewAdapter(
            requireContext(), movieList,
            R.layout.item_list, this
        )
        binding.dashboardRecycleId.adapter = recyclerViewAdapter
        recyclerViewAdapter!!.notifyDataSetChanged()
    }

    //    binding null
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null


    }

    override fun moviePlayer(movieModelListPosition: MovieModelClass, position: Int) {
        val actionInterfaceMoviewPlayer =
            DeshBoardFragmentDirections.actionDeshBoardFragment3ToMoviePlayerFragment(
                movieModelListPosition
            )
        findNavController().navigate(actionInterfaceMoviewPlayer)
    }
}
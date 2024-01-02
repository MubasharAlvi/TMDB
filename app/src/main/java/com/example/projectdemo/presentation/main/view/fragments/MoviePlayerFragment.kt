package com.example.projectdemo.presentation.main.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.projectdemo.R
import com.example.projectdemo.databinding.FragmentMediaPlayerBinding
import com.example.projectdemo.presentation.main.viewmodels.MovieViewModel
import com.example.projectdemo.utils.Constant.URL_IMAGE
import com.example.projectdemo.utils.Constant.URL_YouTUBE
import com.example.projectdemo.utils.ResourceApiState
import com.example.projectdemo.utils.setSafeOnClickListener
import com.google.android.youtube.player.YouTubeStandalonePlayer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MoviePlayerFragment : Fragment() {
    private var _binding: FragmentMediaPlayerBinding? = null
    private val binding get() = _binding!!
    private val moviePlayArgs by navArgs<MoviePlayerFragmentArgs>()
    private val viewModel by viewModels<MovieViewModel>()
    private var VIDEO_ID: Int? = null
    private lateinit var videoURL: String
    private lateinit var videoKey: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_media_player,
            container,
            false
        )
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var url: String
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
                    is ResourceApiState.VideoSuccess -> {
                        binding.progressbarId.visibility = View.GONE
                        videoKey = it.response.results[0].key
                    }
                }
            }
        }
        binding.watchMovieId.setSafeOnClickListener {
            val intent = YouTubeStandalonePlayer.createVideoIntent(
                requireActivity(),
                URL_YouTUBE,
                videoKey,
                0,
                true,
                false
            )
            startActivity(intent)


        }

    }

    private fun init() {
        binding.dateId.text = moviePlayArgs.moviePlay.release_date.toString()
        val urlImage = moviePlayArgs.moviePlay.poster_path.toString()
        Glide.with(requireContext())
            .load(URL_IMAGE + urlImage)
            .placeholder(R.drawable.ic_folder)
            .into(binding.posterId)
        VIDEO_ID = moviePlayArgs.moviePlay.id.toInt()
        binding.overviewDetailId.text = moviePlayArgs.moviePlay.overview.toString()
        VIDEO_ID?.let { it -> viewModel.videoViewModelFun(it) }

    }

}

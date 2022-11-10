package com.rmaproject.radiorodjaplayer.ui.home.tv

import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import com.rmaproject.radiorodjaplayer.R
import com.rmaproject.radiorodjaplayer.databinding.FragmentTvBinding


class TvFragment : Fragment(R.layout.fragment_tv) {

    private val binding: FragmentTvBinding by viewBinding()
    private lateinit var videoPlayer: StyledPlayerView
    private lateinit var mediaSource: MediaSource
    private lateinit var urlType: URLType
    private lateinit var player: ExoPlayer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        videoPlayer = binding.videoView
        setupVideoView(videoPlayer)
    }

    private fun setupVideoView(view: StyledPlayerView) {
        player = ExoPlayer.Builder(requireContext()).build()
        player.addListener(playerListener)
        view.player = player
        createMediaSource()
        player.setMediaSource(mediaSource)
        player.prepare()
    }

    private fun createMediaSource() {
        urlType = URLType.HLS
        urlType.url = "http://vids.rodja.tv:1935/live/rodja/playlist.m3u8"
        player.seekTo(0)
        when (urlType) {
            URLType.HLS -> {
                val dataSourceFactory = DefaultDataSourceFactory(
                    requireContext(),
                    Util.getUserAgent(requireContext(), requireActivity().applicationInfo.name)
                )

                mediaSource = HlsMediaSource.Factory(dataSourceFactory).createMediaSource(
                    MediaItem.fromUri(Uri.parse(urlType.url))
                )
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            hideSystemUi()
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            showUi()
        }

        requireActivity().window.decorView.requestLayout()
    }

    override fun onResume() {
        super.onResume()
        player.playWhenReady = true
        player.play()
    }

    override fun onPause() {
        super.onPause()
        player.pause()
        player.playWhenReady = false
    }

    override fun onStop() {
        super.onStop()
        player.pause()
        player.playWhenReady = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player.stop()
        player.clearMediaItems()
        player.removeListener(playerListener)

        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private var playerListener = object : Player.Listener {
        override fun onRenderedFirstFrame() {
            super.onRenderedFirstFrame()
            if (urlType == URLType.HLS) {
                videoPlayer.useController = false
            }
        }

        override fun onPlayerError(error: PlaybackException) {
            super.onPlayerError(error)

            Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun hideSystemUi() {
        requireActivity().actionBar?.hide()
        requireActivity().window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_IMMERSIVE or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        requireActivity().findViewById<TabLayout>(R.id.tabLayout).isVisible = false
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar).isVisible = false
        binding.txtFullscreen.isVisible = false
        Toast.makeText(requireContext(), "Untuk keluar dari fullscreen, putar kembali layar anda", Toast.LENGTH_SHORT).show()
    }

    private fun showUi() {
        requireActivity().actionBar?.show()
        requireActivity().window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        requireActivity().findViewById<TabLayout>(R.id.tabLayout).isVisible = true
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar).isVisible = true
        binding.txtFullscreen.isVisible = true
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}

enum class URLType(var url: String) {
    HLS("")
}
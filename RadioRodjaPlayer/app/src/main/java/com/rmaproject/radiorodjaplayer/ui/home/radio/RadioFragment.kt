package com.rmaproject.radiorodjaplayer.ui.home.radio

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.rmaproject.radiorodjaplayer.R
import com.rmaproject.radiorodjaplayer.databinding.FragmentRadioBinding
import com.rmaproject.radiorodjaplayer.ui.service.RadioPlayerService
import snow.player.PlayerClient
import snow.player.audio.MusicItem
import snow.player.playlist.Playlist

class RadioFragment : Fragment(R.layout.fragment_radio) {

    private val binding: FragmentRadioBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = resources.getStringArray(R.array.choose_quality)
        val autoCompleteTextView = (binding.qualityChooser.editText as? MaterialAutoCompleteTextView)
        val playerClient = PlayerClient.newInstance(requireContext(), RadioPlayerService::class.java)

        initialisation(items, autoCompleteTextView)

        binding.apply {
            playButton.setOnClickListener {
                playRadio(items, autoCompleteTextView, playerClient)
            }
            stopButton.setOnClickListener {
                stopRadio(playerClient)
            }
        }

        binding.playButton.isVisible = !playerClient.isPlaying
    }

    private fun initialisation(items: Array<String>, autoCompleteTextView: MaterialAutoCompleteTextView?) {
        autoCompleteTextView?.setSimpleItems(items)
        autoCompleteTextView?.setText(items[0], false)
        binding.rodjaImg.load(R.drawable.rodja_image) {
            crossfade(true)
        }
    }

    private fun stopRadio(playerClient: PlayerClient) {
        playerClient.stop()
    }

    private fun playRadio(items: Array<String>, autoCompleteTextView: MaterialAutoCompleteTextView?, playerClient: PlayerClient) {
        if (playerClient.isPlaying) {
            playerClient.stop()
        } else {
            when (autoCompleteTextView?.text.toString()) {
                items[0] -> {
                    val playlist = setPlayListHigh()
                    playerClient.connect { success ->
                        if (success) {
                            playerClient.setPlaylist(playlist, true)
                            Snackbar.make(binding.root, "Radio Diputar...", Snackbar.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), playerClient.errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                items[1] -> {
                    val playlist = setPlayListLow()
                    playerClient.connect { success ->
                        if (success) {
                            playerClient.setPlaylist(playlist, true)
                            Snackbar.make(binding.root, "Radio Diputar...", Snackbar.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), playerClient.errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun setPlayListHigh() : Playlist {
        return Playlist.Builder()
            .append(MusicItem.Builder()
                .setTitle("Radio Rodja 756 AM")
                .setAutoDuration(true)
                .setUri("https://radioislamindonesia.com/rodja-low.mp3")
                .setIconUri("https://www.radiorodja.com/wp-content/uploads/radio-rodja-512px-emblem.png")
                .autoDuration()
                .build()
            )
            .build()
    }

    private fun setPlayListLow() : Playlist {
        return Playlist.Builder()
            .append(MusicItem.Builder()
                .setTitle("Radio Rodja 756 AM")
                .setAutoDuration(true)
                .setUri("https://radioislamindonesia.com/rodja.mp3")
                .setIconUri("https://www.radiorodja.com/wp-content/uploads/radio-rodja-512px-emblem.png")
                .autoDuration()
                .build()
            )
            .build()
    }
}
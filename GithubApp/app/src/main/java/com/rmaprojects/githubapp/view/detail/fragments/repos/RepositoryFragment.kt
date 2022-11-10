package com.rmaprojects.githubapp.view.detail.fragments.repos

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaprojects.githubapp.R
import com.rmaprojects.githubapp.databinding.FragmentRepositoryBinding
import com.rmaprojects.githubapp.di.Injection
import com.rmaprojects.githubapp.view.detail.fragments.repos.adapter.RepositoriesAdapter
import com.rmaprojects.githubapp.viewmodel.GithubViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.math.log

class RepositoryFragment : Fragment(R.layout.fragment_repository) {

    private val binding:FragmentRepositoryBinding by viewBinding()
    private val viewModel:GithubViewModel by viewModels{
        Injection.provideGithubViewModelFactory(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginUser = arguments?.getString(GET_LOGIN)?: ""
        setAdapter(loginUser)

    }

    private fun setAdapter(loginUser: String) {
        lifecycleScope.launch {
            try {
                val response = viewModel.getUserRepos(loginUser)
                val adapter = RepositoriesAdapter(response)
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            } catch (e:Exception) {
                Log.d("ERROR", e.toString())
            }
        }
    }

    companion object {
        const val GET_LOGIN = "LOGINGET"
    }
}
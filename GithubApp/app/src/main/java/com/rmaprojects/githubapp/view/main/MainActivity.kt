package com.rmaprojects.githubapp.view.main

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaprojects.githubapp.R
import com.rmaprojects.githubapp.databinding.ActivityMainBinding
import com.rmaprojects.githubapp.di.Injection
import com.rmaprojects.githubapp.model.userlist.UserListResponse
import com.rmaprojects.githubapp.view.detail.DetailActivity
import com.rmaprojects.githubapp.view.main.adapter.ListUserAdapter
import com.rmaprojects.githubapp.viewmodel.GithubViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding:ActivityMainBinding by viewBinding()
    private val viewModel:GithubViewModel by viewModels {
        Injection.provideGithubViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAdapterListUser()
        setupSearch()
    }

    private fun setupSearch() {
        var userNameQuery: String
        binding.searchInput.editText?.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                userNameQuery = textView.text.toString()
                if (userNameQuery == "") {
                    setAdapterListUser()
                } else {
                    setAdapterWithSearch(userNameQuery)
                }
                true
            } else {
                false
            }
        }
    }

    private fun setAdapterWithSearch(userNameQuery: String) {
        lifecycleScope.launch {
            val response = viewModel.searchUser(userNameQuery).items
            if (response != null) {
                val adapter = ListUserAdapter(response)
                setAdapter(adapter)
            } else {
                val adapter = ListUserAdapter(UserListResponse())
                setAdapter(adapter)
            }
        }
    }

    private fun setAdapterListUser() {
        lifecycleScope.launch {
            val response = viewModel.getListUser()
            val adapter = ListUserAdapter(response)
            setAdapter(adapter)
        }
    }

    private fun setAdapter(adapter: ListUserAdapter) {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        adapter.itemOnClick = { userItem ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.GET_USER, userItem.login)
            startActivity(intent)
        }
    }
}
package com.pawel.mymvi.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pawel.mymvi.R
import com.pawel.mymvi.data.api.ApiHelperImpl
import com.pawel.mymvi.data.api.RetrofitBuilder
import com.pawel.mymvi.data.model.DogsFacts
import com.pawel.mymvi.data.model.User
import com.pawel.mymvi.ui.main.adapter.MainAdapter
import com.pawel.mymvi.ui.main.intent.MainIntent
import com.pawel.mymvi.ui.main.viewState.MainState
import com.pawel.mymvi.ui.main.viewmodel.MainViewModel
import com.pawel.mymvi.util.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dog_fact_layout.*
import kotlinx.android.synthetic.main.item_layout.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var adapter = MainAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
        setupViewModel()
        observeViewModel()
        setupClicks()
    }

    private fun setupClicks() {
        buttonFetchUser.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }
        }
        btn_fetchDogsFacts.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchDogsFacts)
            }
        }
    }

    private fun setupUi() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(
                    RetrofitBuilder.apiService
                )
            )
        )[MainViewModel::class.java]
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {}
                    is MainState.Loading -> {
                        buttonFetchUser.visibility = View.GONE
                        btn_fetchDogsFacts.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                    is MainState.Users -> {
                        progressBar.visibility = View.GONE
                        buttonFetchUser.visibility = View.GONE
                        btn_fetchDogsFacts.visibility = View.GONE
                        renderUsersList(it.user)
                    }
                    is MainState.DogsFacts -> {
                        progressBar.visibility = View.GONE
                        buttonFetchUser.visibility = View.GONE
                        btn_fetchDogsFacts.visibility = View.GONE
                        renderDogsFactsList(it.dogsFacts)
                    }
                    is MainState.GoBackToMainView -> {
                        recyclerView.visibility = View.GONE
                        buttonFetchUser.visibility = View.VISIBLE
                        btn_fetchDogsFacts.visibility = View.VISIBLE
                    }
                    is MainState.Error -> {
                        progressBar.visibility = View.GONE
                        buttonFetchUser.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun renderDogsFactsList(dogsFacts: DogsFacts) {
        recyclerView.visibility = View.VISIBLE
        dogsFacts.let {
            if (it.success) adapter.addData(it.facts as List<Any>)
        }
    }

    private fun renderUsersList(users: List<User>) {
        recyclerView.visibility = View.VISIBLE
        users.let {
            adapter.addData(it)
        }
    }

    override fun onBackPressed() {
        if (dogListContainer != null || userListContainer != null) {
            adapter.cleanList()
            recyclerView.removeAllViews()
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.GoBackToMainView)
            }
        } else {
            super.onBackPressed()
        }
    }
}
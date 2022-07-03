package com.picpay.desafio.android.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.ui.adapters.UserListAdapter
import com.picpay.desafio.android.ui.viewmodel.PicPayViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserListAdapter
    private val picPayViewModel: PicPayViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        setupView()
        picPayViewModel.handleInitView()
    }

    private fun setupView() {
        binding.recyclerView.adapter = UserListAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.userListProgressBar.visibility = View.VISIBLE
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun setupListeners() {
        lifecycleScope.launch() {
            picPayViewModel.user.collect {
                binding.userListProgressBar.visibility = View.GONE
                handleListOfUser(it.list)
            }

        }
    }

    private fun handleListOfUser(list: List<User>) {
        adapter.users = list
    }
}

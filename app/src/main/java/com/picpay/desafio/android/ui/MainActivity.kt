package com.picpay.desafio.android.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.ui.adapters.UserListAdapter
import com.picpay.desafio.android.ui.viewmodel.PicPayViewModel
import com.picpay.desafio.android.ui.viewmodel.UserUIState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserListAdapter
    private val viewModel by viewModel<PicPayViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupView()
        setupObserver()
        viewModel.handleInitView()
    }

    private fun setupView() {
        adapter = UserListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.userListProgressBar.visibility = View.VISIBLE
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.user.collect {
                handleLoading(it)
                handleList(it)
                handleError(it)
            }

        }
    }

    private fun handleError(uiState: UserUIState) {
        if (uiState.uiError.isNotEmpty()){
            Toast.makeText(this, R.string.generic_error, Toast.LENGTH_LONG).show()
        }
    }

    private fun handleList(uiState: UserUIState) {
        val currentList = uiState.list
        if (currentList.isNotEmpty()){
            adapter.users = currentList
        }
    }

    private fun handleLoading(uiState: UserUIState) {
        if (uiState.isLoading) {
            binding.userListProgressBar.visibility = View.VISIBLE
        } else {
            binding.userListProgressBar.visibility = View.GONE
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        if (adapter.users.isNotEmpty()) {
            outState.putParcelableArrayList(USER_LIST, ArrayList(adapter.users))
        }
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val parcelableArrayList = savedInstanceState.getParcelableArrayList<User>(USER_LIST)
        parcelableArrayList?.let {
            adapter.users = it.toList()
        }
        super.onRestoreInstanceState(savedInstanceState)
    }

    companion object {
        const val USER_LIST = "user_list"
    }
}

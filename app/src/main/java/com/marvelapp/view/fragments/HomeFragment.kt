package com.marvelapp.view.fragments

import android.app.Dialog
import androidx.lifecycle.ViewModelProvider
import com.marvelapp.R
import com.marvelapp.core.fragments.BaseFragment
import com.marvelapp.databinding.FragmentHomeBinding
import com.marvelapp.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    lateinit var viewModel: HomeViewModel

    override fun initViews(){

        initViewModel()
    }

    private fun initViewModel(){

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.homeViewModel = viewModel
        observeViewModel()

        viewModel.getCharacters(requireActivity())
    }

    private fun observeViewModel(){

        viewModel.loading.observe(this, { isLoading ->

            if (isLoading)
                showProgressDialog()
            else{
                hideProgressDialog()

                if (binding.swipeRefreshLayout.isRefreshing)
                    binding.swipeRefreshLayout.isRefreshing = false
            }
        })

        viewModel.error.observe(this, { errorResponse ->

            errorResponse.message?.let {
                showMessage(it)
            }
        })
    }

    //<editor-fold desc="Show & Hide ProgressDialog">

    private var mSwipeRefreshBackround: Dialog? = null

    override fun showProgressDialog() {

        if (binding.swipeRefreshLayout.isRefreshing) {

            mSwipeRefreshBackround ?: run {

                mSwipeRefreshBackround = Dialog(requireContext())
                mSwipeRefreshBackround!!.setCancelable(false)
            }

            mSwipeRefreshBackround!!.show()
        }
        else
            super.showProgressDialog()
    }

    override fun hideProgressDialog() {

        if(binding.swipeRefreshLayout.isRefreshing){

            binding.swipeRefreshLayout.isRefreshing = false

            mSwipeRefreshBackround?.let {

                if(it.isShowing)
                    it.dismiss()
            }
        }
        else
            super.hideProgressDialog()
    }

    //</editor-fold>
}
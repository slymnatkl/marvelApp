package com.marvelapp.view.fragments

import android.app.Dialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.marvelapp.R
import com.marvelapp.core.fragments.BaseFragment
import com.marvelapp.databinding.FragmentHomeBinding
import com.marvelapp.repository.model.Character
import com.marvelapp.view.adapters.CharacterListAdapter
import com.marvelapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    //<editor-fold desc="Init Views">

    override fun initViews(){

        initViewModel()
        initAdapter()
    }

    private fun initAdapter(){

        viewModel.adapterCharacterList.setOnCharacterItemClickListener(object : CharacterListAdapter.CharacterItemClickListener{
            override fun onCharacterItemClicked(character: Character) {
                navigateToDetail(character)
            }
        })
    }

    //</editor-fold>

    //<editor-fold desc="View Model">

    private val viewModel: HomeViewModel by viewModels()

    private fun initViewModel(){

        binding.homeViewModel = viewModel
        observeViewModel()
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

            errorResponse?.message?.let {
                showMessage(it)
            }
        })
    }

    //</editor-fold>

    //<editor-fold desc="Navigate Fragments">

    private fun navigateToDetail(character: Character){

        val action = HomeFragmentDirections.actHomeToDetail(character)
        findNavController().navigate(action)
    }

    //</editor-fold>

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
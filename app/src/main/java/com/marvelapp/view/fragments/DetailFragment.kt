package com.marvelapp.view.fragments

import androidx.lifecycle.ViewModelProvider
import com.marvelapp.R
import com.marvelapp.core.fragments.BaseFragment
import com.marvelapp.databinding.FragmentDetailBinding
import com.marvelapp.repository.model.Character
import com.marvelapp.viewmodel.DetailViewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private var character: Character? = null

    //<editor-fold desc="Init Views">

    override fun initViews(){

        getBundle()
        binding.character = character
        initViewModel()
    }

    private fun getBundle(){

        arguments?.let {
            character = DetailFragmentArgs.fromBundle(it).character
        }
    }

    //</editor-fold>

    //<editor-fold desc="View Model">

    lateinit var viewModel: DetailViewModel

    private fun initViewModel(){

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding.detailViewModel = viewModel
        observeViewModel()

        character?.let {
            viewModel.init(requireContext(), it.id!!)
        }
    }

    private fun observeViewModel(){

        viewModel.loading.observe(this, { isLoading ->

            if (isLoading)
                showProgressDialog()
            else
                hideProgressDialog()
        })

        viewModel.error.observe(this, { errorResponse ->

            errorResponse.message?.let {
                showMessage(it)
            }
        })
    }

    //</editor-fold>

}
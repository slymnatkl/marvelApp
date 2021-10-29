package com.marvelapp.core.activities

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.marvelapp.core.extensions.getProgressDialog

abstract class BaseActivity<VDBinding : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : AppCompatActivity()
{
    lateinit var binding: VDBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViews()
        initViews()
    }

    private fun bindViews(){

        binding = DataBindingUtil.setContentView(this@BaseActivity, layoutRes)
        setContentView(binding.root)
    }

    abstract fun initViews()

    //<editor-fold desc="Progress">

    private var progressDialog: Dialog? = null

    fun showProgressDialog(){

        progressDialog = getProgressDialog()
        progressDialog?.show()
    }

    fun hideProgressDialog(){

        progressDialog?.dismiss()
    }

    //</editor-fold>
}
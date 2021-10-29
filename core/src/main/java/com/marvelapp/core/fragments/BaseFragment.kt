package com.marvelapp.core.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.marvelapp.core.activities.BaseActivity
import com.marvelapp.core.extensions.showMessage

abstract class BaseFragment<VDBinding : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : Fragment()
{
    lateinit var binding: VDBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    abstract fun initViews()

    protected fun showMessage(message: String){
        (activity as BaseActivity<*>).showMessage(message)
    }

    protected fun showProgressDialog(){
        (activity as BaseActivity<*>).showProgressDialog()
    }

    protected fun hideProgressDialog(){
        (activity as BaseActivity<*>).hideProgressDialog()
    }
}
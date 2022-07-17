package com.estarta.listingapp.vvm.base

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import com.estarta.listingapp.R
import com.estarta.listingapp.BR

abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel>(@LayoutRes contentLayoutId: Int) :
    Fragment(contentLayoutId), CoroutineScope by MainScope() {
    var successDrawable: Drawable? = null
    var errorDrawable: Drawable? = null

    private lateinit var privateBinding: B

    protected val binding: B
        get() = if (isViewCreated) privateBinding
        else throw IllegalStateException("binding should be not null at this state.")

    private var isViewCreated = false

    protected abstract val viewModel: VM

    protected open fun B.setupUI() {}
    protected abstract fun VM.observeViewModel()



    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isViewCreated = true
        return if (::privateBinding.isInitialized) privateBinding.root
        else run {
            super.onCreateView(inflater, container, savedInstanceState)?.also {
                privateBinding = DataBindingUtil.bind(it)!!
            }
        }
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        successDrawable =
            ContextCompat.getDrawable(activity as AppActivity, R.drawable.ic_success_alert)
        errorDrawable =
            ContextCompat.getDrawable(activity as AppActivity, R.drawable.ic_error_alert)


        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
        }
        binding.setupUI()
        viewModel.observeViewModel()
    }
    }

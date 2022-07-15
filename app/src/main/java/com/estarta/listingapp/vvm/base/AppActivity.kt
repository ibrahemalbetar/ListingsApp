package com.estarta.listingapp.vvm.base

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.estarta.listingapp.vvm.listings.ListingsFragment
import com.estarta.listingapp.R

class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        replaceFragment(
            ListingsFragment(),
            "f"
        )
    }

    fun replaceFragment(
        fragment: Fragment?,
        tag: String?
    ) {
        val ft = supportFragmentManager.beginTransaction()
        if (supportFragmentManager.findFragmentByTag(tag) != null) {
            ft.replace(R.id.mainFragment, fragment!!, tag)
        } else {
            if (TextUtils.isEmpty(tag)) {
                ft.replace(R.id.mainFragment, fragment!!, tag)
            } else {
                ft.replace(R.id.mainFragment, fragment!!, tag)
                ft.addToBackStack(tag)
            }
        }
        ft.commit()
    }
}
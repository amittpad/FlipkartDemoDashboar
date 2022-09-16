package com.app.birlasoft.base

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.app.birlasoft.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AbstractBaseActivity() {
    private val context: Context? = null

    @SuppressLint("HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    protected fun successToast(message: String?) {
        val spannableString = SpannableString(message)
        spannableString.setSpan(
            ForegroundColorSpan(Color.GREEN), 0, spannableString.length, 0
        )
        spannableString.setSpan(
            AbsoluteSizeSpan(40), 0, spannableString.length, 0
        )
        val toast = Toast.makeText(applicationContext, spannableString, Toast.LENGTH_SHORT)
        toast.show()

    }

    protected fun errorToast(message: String?) {
        val spannableString = SpannableString(message)
        spannableString.setSpan(
            ForegroundColorSpan(Color.RED), 0, spannableString.length, 0
        )
        spannableString.setSpan(
            AbsoluteSizeSpan(40), 0, spannableString.length, 0
        )
        val toast = Toast.makeText(applicationContext, spannableString, Toast.LENGTH_SHORT)
        toast.show()
    }

    protected fun showSnackBar(message: String?) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message!!, Snackbar.LENGTH_LONG)
        val sbView = snackBar.view
        val textView = sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        textView.textAlignment =
            View.TEXT_ALIGNMENT_CENTER
        snackBar.show()
    }


    protected fun loadFragment(fragment: Fragment) {
        val fragmentManager = this.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        fragmentTransaction.replace(R.id.mainContent, fragment)
        fragmentTransaction.commitAllowingStateLoss()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
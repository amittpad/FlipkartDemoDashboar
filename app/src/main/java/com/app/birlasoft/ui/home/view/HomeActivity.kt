package com.app.birlasoft.ui.home.view

import android.os.Bundle
import com.app.birlasoft.R
import com.app.birlasoft.base.BaseActivity
import com.app.birlasoft.databinding.ActivityHomeBinding
import com.app.birlasoft.ui.category.view.CategoryFragment
import com.app.birlasoft.ui.home.fragment.HomeFragment
import com.app.birlasoft.ui.orders.view.OrderListFragment
import com.app.birlasoft.ui.profile.view.ProfileFragment
import com.app.birlasoft.ui.wishlist.view.WishListFragment

class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        onClickMenuButton()
    }

    private fun initView() {
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, HomeFragment())
            .commit()
    }

    private fun onClickMenuButton() {
        binding.rlHome.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.content_frame, HomeFragment())
                .commit()
        }

        binding.rlCategory.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.content_frame, CategoryFragment())
                .commit()
        }

        binding.rlProfile.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.content_frame, ProfileFragment())
                .commit()
            loadFragment(ProfileFragment())
        }

        binding.rlWishlist.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.content_frame, WishListFragment())
                .commit()
        }

        binding.rlMore.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.content_frame, OrderListFragment())
                .commit()
        }
    }
}
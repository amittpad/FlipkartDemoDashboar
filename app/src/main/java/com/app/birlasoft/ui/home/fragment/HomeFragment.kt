package com.app.birlasoft.ui.home.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.app.birlasoft.R
import com.app.birlasoft.base.BaseFragment
import com.app.birlasoft.data.model.BannerData
import com.app.birlasoft.data.model.FeatureProductData
import com.app.birlasoft.data.model.ShopByCategoryData
import com.app.birlasoft.databinding.FragmentHomeBinding
import com.app.birlasoft.ui.home.adapter.FeatureProductsRecyclerAdapter
import com.app.birlasoft.ui.home.adapter.ShopByCategoryRecyclerAdapter
import com.app.birlasoft.ui.home.adapter.SliderPagerAdapter
import java.util.*

class HomeFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val bannerList = ArrayList<BannerData>()
    private val shopByCategoryList = ArrayList<ShopByCategoryData>()
    private val featureProductList = ArrayList<FeatureProductData>()
    private val trendingProductList = ArrayList<FeatureProductData>()
    private val newArrivalProductList = ArrayList<FeatureProductData>()
    private lateinit var shopByCategoryRecyclerAdapter: ShopByCategoryRecyclerAdapter
    private lateinit var featureProductsRecyclerAdapter: FeatureProductsRecyclerAdapter
    private var currentPage = 0
    private var dotsCount = 0
    private lateinit var dots: Array<ImageView?>

    override fun getFragmentView(
        inflater: LayoutInflater?,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setOnClicks()
        initView()
        prepareBannerData()
        prepareShopByCategoryData()
        prepareFeatureProductListData()
        prepareTrendingProductListData()
        prepareNewArrivalProductListData()
        return binding.root
    }

    private fun setOnClicks() {


    }

    private fun initView() {


    }


    override fun onClick(view: View?) {
        when (view?.id) {

        }

    }

    /** fetch banner list related work */
    private fun prepareBannerData() {
        var bannerdata = BannerData("1", R.drawable.ic_banner_1)
        bannerList.add(bannerdata)

        bannerdata = BannerData("2", R.drawable.ic_banner_2)
        bannerList.add(bannerdata)

        bannerdata = BannerData("3", R.drawable.ic_banner_3)
        bannerList.add(bannerdata)

        loadBannerListToAdapter(bannerList);

    }

    private fun loadBannerListToAdapter(bannerList: ArrayList<BannerData>) {
        val viewPagerAdapter =
            SliderPagerAdapter(
                activity,
                bannerList
            )
        binding.viewPager.adapter = viewPagerAdapter
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == viewPagerAdapter.count) {
                currentPage = 0
            }
            binding.viewPager.setCurrentItem(currentPage++, true)
        }
        val timer = Timer() // This will create a new Thread
        val PERIOD_MS: Long = 4000
        //delay in milliseconds before task is to be executed
        val DELAY_MS: Long = 2000
        timer.schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(Update)
            }
        }, DELAY_MS, PERIOD_MS)
        dotsCount = viewPagerAdapter.count
        dots = arrayOfNulls(dotsCount)
        binding.sliderDots.removeAllViews()
        for (i in 0 until dotsCount) {
            try {
                dots[i] = ImageView(activity)
            } catch (e: Exception) {
            }
            try {
                dots[i]
                    ?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.non_active_dot
                        )
                    )
            } catch (e: Exception) {
            }
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            binding.sliderDots.addView(dots[i], params)
        }
        try {
            dots[0]?.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.active_dot
                )
            )
        } catch (e: Exception) {
        }
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until dotsCount) {
                    try {
                        dots[i]?.setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.non_active_dot
                            )
                        )
                    } catch (e: Exception) {
                    }
                }
                try {
                    dots[position]?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.active_dot
                        )
                    )
                } catch (e: Exception) {
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }


    /** fetch shop by category list related work */
    private fun prepareShopByCategoryData() {
        var shopByCategoryData = ShopByCategoryData("1", "Grocery", R.drawable.ic_gorcery)
        shopByCategoryList.add(shopByCategoryData)

        shopByCategoryData = ShopByCategoryData("2", "Appliances", R.drawable.ic_appliance)
        shopByCategoryList.add(shopByCategoryData)

        shopByCategoryData = ShopByCategoryData("3", "Books", R.drawable.ic_books)
        shopByCategoryList.add(shopByCategoryData)

        shopByCategoryData = ShopByCategoryData("4", "Bath & Body", R.drawable.ic_bath_body)
        shopByCategoryList.add(shopByCategoryData)

        shopByCategoryData =
            ShopByCategoryData("5", "Fashion Garments", R.drawable.ic_fashion_garments)
        shopByCategoryList.add(shopByCategoryData)

        shopByCategoryData = ShopByCategoryData("6", "Toys", R.drawable.ic_toys)
        shopByCategoryList.add(shopByCategoryData)

        loadShopByCategoryList(shopByCategoryList);

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadShopByCategoryList(shopByCategoryList: ArrayList<ShopByCategoryData>) {
        shopByCategoryRecyclerAdapter =
            ShopByCategoryRecyclerAdapter(requireContext(), shopByCategoryList)
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvHomeCategories.layoutManager = layoutManager
        binding.rvHomeCategories.itemAnimator = DefaultItemAnimator()
        binding.rvHomeCategories.adapter = shopByCategoryRecyclerAdapter
        binding.rvHomeCategories.setHasFixedSize(true)
    }


    /** fetch feature product list related work */
    private fun prepareFeatureProductListData() {
        var featureProductData =
            FeatureProductData("1", "MacBook 2", R.drawable.ic_product_1, "80000", "70000")
        featureProductList.add(featureProductData)

        featureProductData =
            FeatureProductData(
                "2",
                "Men Solid Spread Collar Casual Shirt",
                R.drawable.ic_product_2,
                "1800",
                "1200"
            )
        featureProductList.add(featureProductData)

        loadFeatureProductList(featureProductList);

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadFeatureProductList(featureProductList: ArrayList<FeatureProductData>) {
        featureProductsRecyclerAdapter =
            FeatureProductsRecyclerAdapter(requireContext(), featureProductList)
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvHomeFeatureProduct.layoutManager = layoutManager
        binding.rvHomeFeatureProduct.itemAnimator = DefaultItemAnimator()
        binding.rvHomeFeatureProduct.adapter = featureProductsRecyclerAdapter
        binding.rvHomeFeatureProduct.setHasFixedSize(true)
    }


    /** fetch trending product list related work */
    private fun prepareTrendingProductListData() {
        var featureProductData =
            FeatureProductData("1", "Canon EOS 5D 2", R.drawable.ic_product_3, "80000", "70000")
        trendingProductList.add(featureProductData)

        featureProductData =
            FeatureProductData("2", "MacBook 2", R.drawable.ic_product_1, "80000", "70000")
        trendingProductList.add(featureProductData)

        loadTrendingProductList(trendingProductList);

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadTrendingProductList(trendingProductList: ArrayList<FeatureProductData>) {
        featureProductsRecyclerAdapter =
            FeatureProductsRecyclerAdapter(requireContext(), trendingProductList)
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvHomeTrendingProduct.layoutManager = layoutManager
        binding.rvHomeTrendingProduct.itemAnimator = DefaultItemAnimator()
        binding.rvHomeTrendingProduct.adapter = featureProductsRecyclerAdapter
        binding.rvHomeTrendingProduct.setHasFixedSize(true)
    }


    /** fetch new arrival list related work */
    private fun prepareNewArrivalProductListData() {
        var featureProductData =
            FeatureProductData("1", "Coriander", R.drawable.ic_product_4, "80", "40")
        newArrivalProductList.add(featureProductData)

        featureProductData =
            FeatureProductData("2", "Chicken Masala- 100gm", R.drawable.ic_product_5, "60", "55")
        newArrivalProductList.add(featureProductData)

        featureProductData =
            FeatureProductData("1", "Coriander", R.drawable.ic_product_4, "80", "40")
        newArrivalProductList.add(featureProductData)

        featureProductData =
            FeatureProductData("2", "Chicken Masala- 100gm", R.drawable.ic_product_5, "60", "55")
        newArrivalProductList.add(featureProductData)

        featureProductData =
            FeatureProductData("1", "Coriander", R.drawable.ic_product_4, "80", "40")
        newArrivalProductList.add(featureProductData)

        featureProductData =
            FeatureProductData("2", "Chicken Masala- 100gm", R.drawable.ic_product_5, "60", "55")
        newArrivalProductList.add(featureProductData)

        featureProductData =
            FeatureProductData("1", "Coriander", R.drawable.ic_product_4, "80", "40")
        newArrivalProductList.add(featureProductData)

        featureProductData =
            FeatureProductData("2", "Chicken Masala- 100gm", R.drawable.ic_product_5, "60", "55")
        newArrivalProductList.add(featureProductData)

        loadNewArrivalProductList(newArrivalProductList);

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadNewArrivalProductList(newArrivalProductList: ArrayList<FeatureProductData>) {
        featureProductsRecyclerAdapter =
            FeatureProductsRecyclerAdapter(requireContext(), newArrivalProductList)
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvHomeNewArrivals.layoutManager = layoutManager
        binding.rvHomeNewArrivals.itemAnimator = DefaultItemAnimator()
        binding.rvHomeNewArrivals.adapter = featureProductsRecyclerAdapter
        binding.rvHomeNewArrivals.setHasFixedSize(true)
    }

}

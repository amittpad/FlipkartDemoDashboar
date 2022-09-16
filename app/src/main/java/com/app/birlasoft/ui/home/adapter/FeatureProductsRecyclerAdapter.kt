package com.app.birlasoft.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.birlasoft.R
import com.app.birlasoft.data.model.FeatureProductData
import com.app.birlasoft.databinding.RowProductBinding
import com.app.birlasoft.utils.GlideConnector

internal class FeatureProductsRecyclerAdapter(
    var context: Context,
    private var featureProductList: ArrayList<FeatureProductData>
) : RecyclerView.Adapter<FeatureProductsRecyclerAdapter.MyViewHolder>() {
    private lateinit var binding: RowProductBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = RowProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        featureProductList[position].let { holder.bind(position, it) }

    }

    override fun getItemCount(): Int {
        return featureProductList.size
    }

    class MyViewHolder(private val binding: RowProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int, featureProductList: FeatureProductData) {
            binding.productName.text = featureProductList.title
            GlideConnector.getInstance()
                .loadImageDirectly(
                    binding.productImage.context,
                    featureProductList.image,
                    binding.productImage
                )

            binding.offerPrice.text =
                binding.offerPrice.resources.getString(R.string.Rs) + featureProductList.specialPrice
            binding.originalPrice.text =
                binding.offerPrice.resources.getString(R.string.Rs) + featureProductList.price

            binding.originalPrice.paintFlags =
                binding.originalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            if (position == 0) {
                binding.tvOutOfStock.visibility = View.GONE
            }
        }
    }

}
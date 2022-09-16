package com.app.birlasoft.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.app.birlasoft.R
import com.app.birlasoft.data.model.ShopByCategoryData
import com.app.birlasoft.databinding.RowHomeCategoryBinding
import com.app.birlasoft.utils.GlideConnector

internal class ShopByCategoryRecyclerAdapter(
    var context: Context,
    private var shopByCategoryList: ArrayList<ShopByCategoryData>,
    private var lastPosition: Int = -1

) : RecyclerView.Adapter<ShopByCategoryRecyclerAdapter.MyViewHolder>() {
    private lateinit var binding: RowHomeCategoryBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = RowHomeCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        shopByCategoryList[position].let { holder.bind(position, it) }
        onScrollItemAnimation(position, holder.itemView)
    }

    override fun getItemCount(): Int {
        return shopByCategoryList.size
    }

    class MyViewHolder(private val binding: RowHomeCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, shopByCategoryList: ShopByCategoryData) {
            binding.tvCategoryName.text = shopByCategoryList.title
            GlideConnector.getInstance()
                .loadImageDirectly(
                    binding.ivCategoryImage.context,
                    shopByCategoryList.image,
                    binding.ivCategoryImage
                )
        }
    }


    private fun onScrollItemAnimation(position: Int, itemView: View) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.item_animation_fall_down)
            itemView.startAnimation(animation)
            lastPosition = position
        }
    }

}
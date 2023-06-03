package com.bashirli.bootcamp3iyun.ui.home

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bashirli.bootcamp3iyun.databinding.ItemProductBinding
import com.bashirli.bootcamp3iyun.model.product.ProductResponse
import com.bashirli.bootcamp3iyun.model.product.ProductResponseItem
import com.squareup.picasso.Picasso

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private val arrayList=ArrayList<ProductResponseItem>()

    inner class HomeViewHolder(val binding:ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layout=ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item= arrayList[position]
        Picasso.get().load(item.image).into(holder.binding.imageProduct)
        holder.binding.item=item
    }

    fun updateList(list:List<ProductResponseItem>){
        arrayList.clear()
        arrayList.addAll(list)
        notifyDataSetChanged()
    }

}
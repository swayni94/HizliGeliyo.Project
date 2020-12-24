package com.example.hizligeliyoproject.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hizligeliyoproject.databinding.CardviewProductBinding
import com.example.hizligeliyoproject.network.model.Product
import com.squareup.picasso.Picasso

class HomeProjectAdapter(val productFullData: List<Product>) : RecyclerView.Adapter<HomeProjectAdapter.ViewHolder>(){

    class ViewHolder(val binding : CardviewProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =CardviewProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(productFullData.get(position).image).resize(195, 195).into(holder.binding.cardviewProductImage)
        holder.binding.cardviewProductTitle.text = productFullData.get(position).title
        holder.binding.cardviewProductPice.text = productFullData.get(position).price.toString()+" TL"
    }

    override fun getItemCount(): Int {
        return productFullData.size
    }
}
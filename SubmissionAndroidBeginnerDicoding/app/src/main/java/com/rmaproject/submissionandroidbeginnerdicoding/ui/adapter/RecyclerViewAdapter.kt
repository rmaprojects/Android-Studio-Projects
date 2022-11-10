package com.rmaproject.submissionandroidbeginnerdicoding.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.rmaproject.submissionandroidbeginnerdicoding.R
import com.rmaproject.submissionandroidbeginnerdicoding.databinding.ItemRecyclerViewBinding
import com.rmaproject.submissionandroidbeginnerdicoding.model.ProductModel
import com.rmaproject.submissionandroidbeginnerdicoding.ui.adapter.RecyclerViewAdapter.RecyclerViewAdapterViewHolder

class RecyclerViewAdapter (private val productList: MutableList<ProductModel>) : Adapter<RecyclerViewAdapterViewHolder>() {

    var onItemClickCallback: ((ProductModel) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapterViewHolder {
        return RecyclerViewAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false))
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: RecyclerViewAdapterViewHolder, position: Int) {
        val product = productList[position]
        holder.binding.cardViewProduct.setOnClickListener {
            onItemClickCallback?.invoke(product)
        }
        holder.bindView(product)
    }

    class RecyclerViewAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding:ItemRecyclerViewBinding by viewBinding()
        fun bindView(product: ProductModel) {
            binding.txtProductName.text = product.productName
            binding.imgPreview.load(product.productImageUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_baseline_image_24)
            }
        }
    }
}
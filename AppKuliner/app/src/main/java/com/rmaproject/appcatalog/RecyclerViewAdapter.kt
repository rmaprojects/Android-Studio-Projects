package com.rmaproject.appcatalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaproject.appcatalog.RecyclerViewAdapter.*
import com.rmaproject.appcatalog.databinding.ItemKulinerBinding

class RecyclerViewAdapter(val listKuliner:ArrayList<String>, val hargaKuliner:ArrayList<Int>, val listImageMenu:ArrayList<Int>, val listRating:List<Float>) : RecyclerView.Adapter<RecyclerViewAdapterViewHolder>(){

    var gotoDetailMenuActivity:((Int) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerViewAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kuliner, parent, false)
        return RecyclerViewAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapterViewHolder, position: Int) {
        val itemMenuKuliner = listKuliner[position]
        val hargaMenuKuliner = hargaKuliner[position]
        val imageMenuKuliner = listImageMenu[position]
        val ratingMenuKuliner = listRating[position]
        holder.bindView(itemMenuKuliner,hargaMenuKuliner, imageMenuKuliner, ratingMenuKuliner)
        holder.binding.cardView.setOnClickListener {
            gotoDetailMenuActivity?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return listKuliner.size
    }

    class RecyclerViewAdapterViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val binding: ItemKulinerBinding by viewBinding()
        fun bindView(itemMenuKuliner:String, itemHargaKuliner:Int, imageMenu:Int, ratingMenu:Float) {
            binding.hargaMenu.text = "Rp.$itemHargaKuliner"
            binding.namaMenu.text = itemMenuKuliner
            binding.gambarMenu.setImageResource(imageMenu)
            binding.ratingMenu.rating = ratingMenu
        }
    }

}
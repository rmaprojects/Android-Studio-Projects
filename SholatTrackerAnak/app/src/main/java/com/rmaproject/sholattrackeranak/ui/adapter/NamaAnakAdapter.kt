package com.rmaproject.sholattrackeranak.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaproject.sholattrackeranak.R
import com.rmaproject.sholattrackeranak.api.model.GetNamaAnakModelItem
import com.rmaproject.sholattrackeranak.api.model.NamaAnakModel
import com.rmaproject.sholattrackeranak.databinding.ItemNamaAnakBinding
import com.rmaproject.sholattrackeranak.ui.adapter.NamaAnakAdapter.NamaAnakAdapterViewHolder

class NamaAnakAdapter(val namaAnak: NamaAnakModel) : RecyclerView.Adapter<NamaAnakAdapterViewHolder>() {

    var listener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamaAnakAdapterViewHolder {
        return NamaAnakAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_nama_anak, parent, false))
    }

    override fun onBindViewHolder(holder: NamaAnakAdapterViewHolder, position: Int) {
        val listNamaAnak = namaAnak.listNamaAnakModel[position]
        holder.holder(listNamaAnak)
        holder.binding.cardViewNamaAnak.setOnClickListener {
            listener?.invoke()
        }
    }

    override fun getItemCount(): Int {
        return namaAnak.listNamaAnakModel.size
    }

    class NamaAnakAdapterViewHolder(itemView: View): ViewHolder(itemView) {
        val binding:ItemNamaAnakBinding by viewBinding()
        fun holder(listNamaAnak:GetNamaAnakModelItem) {
            binding.namaAnak.text = listNamaAnak.namaAnak
        }
    }
}
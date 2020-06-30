package com.wipro.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wipro.assignment.R
import com.wipro.assignment.databinding.ListItemBinding
import com.wipro.model.InformationData

class RecyclerAdapter(private val infoList: ArrayList<InformationData>) :
    RecyclerView.Adapter<RecyclerAdapter.CardHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return CardHolder(binding)
    }

    override fun getItemCount() = infoList.size

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val itemPhoto = infoList[position]
        holder.bindInfoData(itemPhoto)
    }

    fun clear() {
        infoList.clear()
        notifyDataSetChanged()
    }

    class CardHolder(private val listItemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(listItemBinding.root) {

        fun bindInfoData(informationData: InformationData) {
            listItemBinding.infoData = informationData
            Picasso.get().load(informationData.image)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(listItemBinding.imageView)
        }
    }
}
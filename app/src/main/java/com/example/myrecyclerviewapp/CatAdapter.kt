package com.example.myrecyclerviewapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerviewapp.model.CatUiModel

class CatAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<CatViewHolder>() {

    private val catData = mutableListOf<CatUiModel>()
    fun setData(catData: List<CatUiModel>) {
        this.catData.clear()
        this.catData.addAll(catData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = layoutInflater.inflate(R.layout.item_cat, parent, false)
        return CatViewHolder(view, imageLoader, object : CatViewHolder.OnClickListener {
            override fun okClick(catData: CatUiModel) {
                onClickListener.onItemClick(catData)
            }

        })
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bindData(catData[position])
    }

    override fun getItemCount() = catData.size

    interface OnClickListener {
        fun onItemClick(cateData: CatUiModel)
    }
}
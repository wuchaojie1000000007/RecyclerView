package com.example.myrecyclerviewapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerviewapp.ImageLoader
import com.example.myrecyclerviewapp.R
import com.example.myrecyclerviewapp.model.CatUiModel
import com.example.myrecyclerviewapp.model.ListItemUiModel
import com.example.myrecyclerviewapp.viewholder.CatViewHolder
import com.example.myrecyclerviewapp.viewholder.ListItemViewHolder
import com.example.myrecyclerviewapp.viewholder.TitleViewHolder

private const val VIEW_TYPE_TITLE = 0
private const val VIEW_TYPE_CAT = 1

class ListItemAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<ListItemViewHolder>() {

    private val listData = mutableListOf<ListItemUiModel>()
    val swipeToDeleteCallback = SwipeToDeleteCallback()

    fun setData(listData: List<ListItemUiModel>) {
        this.listData.clear()
        this.listData.addAll(listData)
        notifyDataSetChanged()
    }

    private fun removeItem(position: Int) {
        listData.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addItem(position: Int, item:ListItemUiModel){
        listData.add(position, item)
        notifyItemInserted(position)
    }

    override fun getItemViewType(position: Int) = when (listData[position]) {
        is ListItemUiModel.Title -> VIEW_TYPE_TITLE
        is ListItemUiModel.Cat -> VIEW_TYPE_CAT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        VIEW_TYPE_TITLE -> {
            val view = layoutInflater.inflate(R.layout.item_title, parent, false)
            TitleViewHolder(view)
        }
        VIEW_TYPE_CAT -> {
            val view = layoutInflater.inflate(R.layout.item_cat, parent, false)
            CatViewHolder(view, imageLoader, object : CatViewHolder.OnClickListener {
                override fun okClick(catData: CatUiModel) {
                    onClickListener.onItemClick(catData)
                }
            })
        }
        else -> {
            throw IllegalArgumentException("Unknown view type requested: $viewType")
        }
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bindData(listData[position])
    }

    override fun getItemCount() = listData.size

    interface OnClickListener {
        fun onItemClick(cateData: CatUiModel)
    }

    inner class SwipeToDeleteCallback :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ) = false

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ) = if (viewHolder is CatViewHolder) {
            makeMovementFlags(
                ItemTouchHelper.ACTION_STATE_IDLE,
                ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
            ) or makeMovementFlags(
                ItemTouchHelper.ACTION_STATE_DRAG,
                ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
            )
        } else {
            0
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            removeItem(position)
        }
    }
}
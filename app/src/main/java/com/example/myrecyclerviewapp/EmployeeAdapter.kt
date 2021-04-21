package com.example.myrecyclerviewapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerviewapp.model.EmployeeUiModel

class EmployeeAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<EmployeeViewHolder>() {

    private val employeeData = mutableListOf<EmployeeUiModel>()
    fun setData(employeeData: List<EmployeeUiModel>) {
        this.employeeData.clear()
        this.employeeData.addAll(employeeData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = layoutInflater.inflate(R.layout.item_employee, parent, false)
        return EmployeeViewHolder(view, imageLoader)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bindData(employeeData[position])
    }

    override fun getItemCount() = employeeData.size
}















package com.example.myrecyclerviewapp

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerviewapp.model.EmployeeRole
import com.example.myrecyclerviewapp.model.EmployeeUiModel
import com.example.myrecyclerviewapp.model.Gender

private val FEMALE_SYMBOL by lazy {
    HtmlCompat.fromHtml("&#9793;", HtmlCompat.FROM_HTML_MODE_LEGACY)
}

private val MALE_SYMBOL by lazy {
    HtmlCompat.fromHtml("&#9794;", HtmlCompat.FROM_HTML_MODE_LEGACY)
}

private const val UNKNOWN_SYMBOL = "?"

class EmployeeViewHolder(
    containerView: View,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(containerView) {

    private val employeePhotoView: ImageView by lazy { containerView.findViewById(R.id.item_employee_photo) }
    private val employeeNameView: TextView by lazy { containerView.findViewById(R.id.item_employee_name) }
    private val employeeRoleView: TextView by lazy { containerView.findViewById(R.id.item_employee_role) }
    private val employeeBiographyView: TextView by lazy { containerView.findViewById(R.id.item_employee_biography) }
    private val employeeGenderView: TextView by lazy { containerView.findViewById(R.id.item_employee_gender) }

    fun bindData(employeeUiModel: EmployeeUiModel) {
        imageLoader.loadImage(employeeUiModel.imageUrl, employeePhotoView)
        employeeNameView.text = employeeUiModel.name
        employeeRoleView.text = when (employeeUiModel.role) {
            EmployeeRole.HumanResources -> "Human Resources"
            EmployeeRole.Management -> "Management"
            EmployeeRole.Technology -> "Technology"
        }
        employeeBiographyView.text = employeeUiModel.biography
        employeeGenderView.text = when (employeeUiModel.gender) {
            Gender.Female -> FEMALE_SYMBOL
            Gender.Male -> MALE_SYMBOL
            Gender.Unknown -> UNKNOWN_SYMBOL
        }
    }
}
























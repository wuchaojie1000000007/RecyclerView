package com.example.myrecyclerviewapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerviewapp.model.CatBreed
import com.example.myrecyclerviewapp.model.CatUiModel
import com.example.myrecyclerviewapp.model.Gender

private val FEMALE_SYMBOL by lazy {
    HtmlCompat.fromHtml("&#9793;", HtmlCompat.FROM_HTML_MODE_LEGACY)
}

private val MALE_SYMBOL by lazy {
    HtmlCompat.fromHtml("&#9794;", HtmlCompat.FROM_HTML_MODE_LEGACY)
}

private const val UNKNOWN_SYMBOL = "?"


class CatViewHolder(
    private val containerView: View,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(containerView) {

    private val catPhotoView: ImageView by lazy { containerView.findViewById(R.id.item_cat_photo) }
    private val catNameView: TextView by lazy { containerView.findViewById(R.id.item_cat_name) }
    private val catBreedView: TextView by lazy { containerView.findViewById(R.id.item_cat_breed) }
    private val catBiographyView: TextView by lazy { containerView.findViewById(R.id.item_cat_biography) }
    private val catGender: TextView by lazy { containerView.findViewById(R.id.item_cat_gender) }

    fun bindData(catUiModel: CatUiModel) {
        imageLoader.loadImage(catUiModel.imageUrl, catPhotoView)
        catNameView.text = catUiModel.name
        catBreedView.text = when (catUiModel.breed) {
            CatBreed.AmericanCurl -> "American Curl"
            CatBreed.BalineseJavanese -> "Balinese Javanese"
            CatBreed.ExoticShorthair -> "Exotic Short hair"
        }
        catBiographyView.text = catUiModel.biography
        catGender.text = when (catUiModel.gender) {
            Gender.Male -> MALE_SYMBOL
            Gender.Female -> FEMALE_SYMBOL
            Gender.Unknown -> UNKNOWN_SYMBOL
        }
    }
}


















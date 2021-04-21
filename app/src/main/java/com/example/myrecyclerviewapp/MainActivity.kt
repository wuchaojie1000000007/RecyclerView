package com.example.myrecyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerviewapp.model.*

class MainActivity : AppCompatActivity() {

    private val catAdapter by lazy {
        CatAdapter(
            layoutInflater,
            GlideImageLoader(this),
            object : CatAdapter.OnClickListener {
                override fun onItemClick(cateData: CatUiModel) {
                    showSelectionDialog(cateData)
                }

            }
        )
    }
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recycler_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = catAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        catAdapter.setData(
            listOf(

                CatUiModel(

                    Gender.Male,

                    CatBreed.BalineseJavanese,

                    "Fred",

                    "Silent and deadly",

                    "https://cdn2.thecatapi.com/images/DBmIBhhyv.jpg"

                ),

                CatUiModel(

                    Gender.Female,

                    CatBreed.ExoticShorthair,

                    "Wilma",

                    "Cuddly assassin",

                    "https://cdn2.thecatapi.com/images/KJF8fB_20.jpg"

                ),

                CatUiModel(

                    Gender.Unknown,

                    CatBreed.AmericanCurl,

                    "Curious George",

                    "Award winning investigator",

                    "https://cdn2.thecatapi.com/images/vJB8rwfdX.jpg"

                )

            )
        )
    }

    private fun showSelectionDialog(catUiModel: CatUiModel) {
        AlertDialog.Builder(this).setTitle("Agent selected")
            .setMessage("You have selected agency ${catUiModel.name}")
            .setPositiveButton("OK") { _, _ -> }
            .show()
    }
}



















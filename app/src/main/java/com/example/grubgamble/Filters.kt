package com.example.grubgamble

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class Filters : AppCompatActivity() {
    private lateinit var foodtypeAdapter: TypeAdapter
    private lateinit var foodrtypeAdapter: TypeRAdapter

    private fun hideSoftKeyboard(view: View) {
        val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filters)
        foodtypeAdapter = TypeAdapter(mutableListOf())
        foodrtypeAdapter = TypeRAdapter(mutableListOf())

        val rvFoodRItems = findViewById<RecyclerView>(R.id.rvFoodRItems)
        val rvFoodItems = findViewById<RecyclerView>(R.id.rvFoodItems)
        val btnAddFoodR = findViewById<Button>(R.id.btnAddFoodR)
        val btnDelFoodR = findViewById<Button>(R.id.btnDelFoodR)
        val btnAddFood = findViewById<Button>(R.id.btnAddFood)
        val btnDelFood = findViewById<Button>(R.id.btnDelFood)
        val etFoodItems = findViewById<EditText>(R.id.etFoodItems)
        val etFoodRItems = findViewById<EditText>(R.id.etFoodRItems)

        rvFoodRItems.adapter = foodrtypeAdapter
        rvFoodRItems.layoutManager = LinearLayoutManager(this)

        rvFoodItems.adapter = foodtypeAdapter
        rvFoodItems.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.btnAddFood).setOnClickListener {
            hideSoftKeyboard(it)
        }

        findViewById<Button>(R.id.btnAddFoodR).setOnClickListener {
            hideSoftKeyboard(it)
        }

        btnAddFoodR.setOnClickListener {
            val foodRTitle = etFoodRItems.text.toString()
            if(foodRTitle.isNotEmpty()) {
                val typeR = FoodRType(foodRTitle)
                foodrtypeAdapter.addFRType(typeR)
                etFoodRItems.text.clear()
                hideSoftKeyboard(it)
            }
        }

        btnDelFoodR.setOnClickListener {
            foodrtypeAdapter.deleteFRType()
        }

        btnAddFood.setOnClickListener {
            val foodTitle = etFoodItems.text.toString()
            if(foodTitle.isNotEmpty()) {
                val type = FoodType(foodTitle)
                foodtypeAdapter.addFType(type)
                etFoodItems.text.clear()
                hideSoftKeyboard(it)
            }
        }

        btnDelFood.setOnClickListener {
            foodtypeAdapter.deleteFType()
        }
    }
}
package com.example.grubgamble

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TypeRAdapter (
    private val fRTypes: MutableList<FoodRType>
) : RecyclerView.Adapter<TypeRAdapter.FRTypeViewHolder>(){

    class FRTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FRTypeViewHolder {
        return TypeRAdapter.FRTypeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_foodr,
                parent,
                false

            )
        )
    }

    fun addFRType(foodrtype: FoodRType ) {
        fRTypes.add(foodrtype)
        notifyItemInserted(fRTypes.size - 1)
    }

    fun deleteFRType() {
        fRTypes.removeAll { foodrtype ->
            foodrtype.isChecked
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return fRTypes.size
    }

    private fun toggleStrikeThrough(tvFoodRTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvFoodRTitle.paintFlags = tvFoodRTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvFoodRTitle.paintFlags = tvFoodRTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: FRTypeViewHolder, position: Int) {
        val curFRType = fRTypes[position]
        holder.itemView.apply {
            val tvFoodRTitle = findViewById<TextView>(R.id.tvFoodRTitle)
            val cbDone = findViewById<CheckBox>(R.id.cbDone)
            tvFoodRTitle.text = curFRType.title
            cbDone.isChecked = curFRType.isChecked
            toggleStrikeThrough(tvFoodRTitle, curFRType.isChecked)
            cbDone.setOnCheckedChangeListener { _ , isChecked ->
                toggleStrikeThrough(tvFoodRTitle, isChecked)
                curFRType.isChecked = !curFRType.isChecked
            }
        }
    }
}
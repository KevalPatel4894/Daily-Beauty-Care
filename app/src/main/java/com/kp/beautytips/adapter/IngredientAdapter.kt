package com.kp.beautytips.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.kp.beautytips.R
import com.kp.beautytips.activity.IngredientDetailActivity
import com.kp.beautytips.model.IngredientModel
import com.kp.beautytips.utils.AppUtils
import com.bumptech.glide.Glide
import java.util.Locale

class IngredientAdapter(
    private val context: Context,
    private val originalList: List<IngredientModel>,
    private val onFilteredCallback: (Int) -> Unit
) : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>(), Filterable {

    private var filteredList: List<IngredientModel> = originalList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        return IngredientViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false)
        )
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredient = filteredList[position]
        holder.tvName.text = context.getString(ingredient.nameResId)
        holder.tvBenefits.text = context.getString(ingredient.benefitsResId)

        Glide.with(context)
            .load(ingredient.imageResId)
            .placeholder(R.drawable.ic_book_white)
            .error(R.drawable.ic_book_white)
            .into(holder.imgIngredient)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, IngredientDetailActivity::class.java).apply {
                putExtra("ingredient_key", ingredient.key)
                putExtra("ingredient_name_res", ingredient.nameResId)
                putExtra("ingredient_benefits_res", ingredient.benefitsResId)
                putExtra("ingredient_image_res", ingredient.imageResId)
                putExtra("ingredient_keywords_res", ingredient.keywordsResId)
            }
            context.startActivity(intent)
            AppUtils.startFromRightToLeft(context)
        }
    }

    override fun getItemCount(): Int = filteredList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase(Locale.getDefault())?.trim() ?: ""
                
                val resultsList = if (query.isEmpty()) {
                    originalList
                } else {
                    originalList.filter { ingredient ->
                        val localizedName = context.getString(ingredient.nameResId).lowercase(Locale.getDefault())
                        val localizedBenefits = context.getString(ingredient.benefitsResId).lowercase(Locale.getDefault())
                        localizedName.contains(query) || localizedBenefits.contains(query)
                    }
                }

                val results = FilterResults()
                results.values = resultsList
                results.count = resultsList.size
                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as? List<IngredientModel> ?: originalList
                notifyDataSetChanged()
                onFilteredCallback(filteredList.size)
            }
        }
    }

    class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgIngredient: AppCompatImageView = itemView.findViewById(R.id.imgIngredient)
        val tvName: AppCompatTextView = itemView.findViewById(R.id.tvIngredientName)
        val tvBenefits: AppCompatTextView = itemView.findViewById(R.id.tvIngredientBenefits)
    }
}

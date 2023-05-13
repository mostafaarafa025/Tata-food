package com.example.tatafood.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tatafood.R
import com.example.tatafood.databinding.CategoryRowBinding
import com.example.tatafood.databinding.OverRowBinding
import com.example.tatafood.model.Category

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    inner class ViewHolder(val viewBinding:CategoryRowBinding):RecyclerView.ViewHolder(viewBinding.root){
            fun bind(item:Category){
                itemView.setOnClickListener {
                    onItemClickListener?.invoke(item)
                }
        viewBinding.apply {
            Glide.with(itemView)
                .load(item.strCategoryThumb)
                .error(R.drawable.ic_wifi_broken)
                .placeholder(R.drawable.loading)
                .into(categoryImageView)
                categoryName.text=item.strCategory

        }

}

    }
    private val differCallback = object : DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
           return oldItem.idCategory == newItem.idCategory
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
           return oldItem == newItem
        }
    }
    var onItemClickListener: ((Category) -> Unit)? = null
    val differ=AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CategoryRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=differ.currentList[position]
        holder.bind(item)
    }


}
package com.example.tatafood.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tatafood.R
import com.example.tatafood.databinding.OverRowBinding
import com.example.tatafood.model.Meal

class OverAdapter: RecyclerView.Adapter<OverAdapter.ViewHolder>() {
   inner class ViewHolder(val viewBinding:OverRowBinding):RecyclerView.ViewHolder(viewBinding.root){
        fun bind (item:Meal){
            itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
            viewBinding.apply {
                Glide.with(itemView)
                    .load(item.strMealThumb)
                    .error(R.drawable.ic_wifi_broken)
                    .placeholder(R.drawable.loading)
                    .into(overImage)

            }
        }
    }
    var onItemClickListener: ((Meal) -> Unit)? = null
    private val diffUtil=object :DiffUtil.ItemCallback<Meal>(){
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
           return oldItem.idMeal==newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
           return oldItem==newItem
        }
    }
     val differ=AsyncListDiffer(this,diffUtil)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            OverRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
            )
        )
    }

    override fun getItemCount(): Int {
      return  differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//       val data=differ.currentList[position]
//        Glide.with(holder.itemView)
//            .load(data.strMealThumb)
//            .into(holder.viewBinding.overImage)
        val item=differ.currentList[position]
        holder.bind(item)

    }

}
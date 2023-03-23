package com.test.testapp.ui.welcome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.testapp.R
import com.test.testapp.databinding.CategoryItemBinding
import com.test.testapp.ui.welcome.data.CategoryItem

class CategoryAdapter:RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {
    val datas = mutableListOf<CategoryItem>()

        var onItemClick:((categoryItem:CategoryItem,pos:Int)->Unit)?=null
    class CategoryVH(binding:CategoryItemBinding):RecyclerView.ViewHolder(binding.root) {

        val _binding = binding

        fun setName(name:String) {
            _binding.tvCategoryName.text = name
        }
        fun isSelect(isSelect:Boolean) {
            _binding.tvCategoryName.isSelected = isSelect
        }
    }

    fun addDatas (data:List<CategoryItem>) {
        datas.addAll(data)
        notifyDataSetChanged()
    }

    fun setDefaultSelected() {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        return CategoryVH(CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        val item = datas[position]
        holder.itemView.setOnClickListener {
            clearAll()
            item.isSelected =true
            notifyItemChanged(position)
            onItemClick?.invoke(item,position)
        }
        holder.setName(item.categoryName)
        holder.isSelect(item.isSelected)
    }

    private fun clearAll() {
        for((index,item) in datas.withIndex()) {
            if (item.isSelected) {
                item.isSelected =false
                notifyItemChanged(index)
                break
            }
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}
package com.test.testapp.ui.welcome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.testapp.R
import com.test.testapp.databinding.CategoryItemBinding
import com.test.testapp.databinding.ProductItemBinding
import com.test.testapp.ui.welcome.data.CategoryItem
import com.test.testapp.ui.welcome.data.Product

class ProductAdapter:RecyclerView.Adapter<ProductAdapter.ProductVH>() {
    val datas = mutableListOf<Product>()

    var onItemClick:((item:Product, pos:Int)->Unit)?=null
    class ProductVH(binding:ProductItemBinding):RecyclerView.ViewHolder(binding.root) {

        val _binding = binding

        fun setName(name:String) {
            _binding.tvProductName.text = name
        }

    }

    fun setNewData (data:MutableList<Product>) {
        datas.clear()
        datas.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
        return ProductVH(ProductItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ProductVH, position: Int) {
        val item = datas[position]
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item,position)
        }
        holder.setName(datas[position].productName)
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}
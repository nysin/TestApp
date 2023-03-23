package com.test.testapp.ui.productdetail

import android.graphics.Rect
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import coil.load
import com.test.testapp.R
import com.test.testapp.databinding.BenfitItemBinding
import com.test.testapp.databinding.ProductDetailItemBinding
import com.test.testapp.ui.productdetail.data.Benefit
import com.test.testapp.ui.productdetail.data.ProductDetailItem
import com.test.testapp.utils.DpUtils


class ProductDetailAdapter: RecyclerView.Adapter<ProductDetailAdapter.ProductDetailVH>() {
    private val datas = mutableListOf<ProductDetailItem>()

    var onImgClick:((item:ProductDetailItem, pos:Int)->Unit)?=null

    fun setNewData(productDetailList: MutableList<ProductDetailItem>) {
        datas.clear()
        datas.addAll(productDetailList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailVH {
        return ProductDetailVH(ProductDetailItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ProductDetailVH, position: Int) {
        val item = datas[position]
        holder.binding.tvProductName.text = item.productName
        holder.binding.tvDesc.text = item.productDescription
        holder.addData(item.benefits)
        holder.binding.imgDetail.load(item.productImageLandscape) {
            placeholder(R.mipmap.ic_launcher)
            error(R.mipmap.ic_launcher)
        }

        holder.binding.imgDetail.setOnClickListener {
            onImgClick?.invoke(item,position)
        }


    }

    override fun getItemCount(): Int {
        return datas.size
    }

    class ProductDetailVH(val binding: ProductDetailItemBinding): RecyclerView.ViewHolder(binding.root) {
        var benfitAdapter:BenfitAdapter
        init {
            binding.benfitList.layoutManager = LinearLayoutManager(itemView.context)
            benfitAdapter = BenfitAdapter()

            binding.benfitList.adapter = benfitAdapter
        }

        fun addData (benfit: MutableList<Benefit>) {
            benfitAdapter.setNewData(benfit)
        }

    }

    class BenfitAdapter:RecyclerView.Adapter<BenfitAdapter.BenfitVH>() {

        val datas = mutableListOf<Benefit>()

        fun setNewData(benfit:MutableList<Benefit>) {
            datas.clear()
            datas.addAll(benfit)
            notifyDataSetChanged()

        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BenfitVH {
            return BenfitVH(BenfitItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }

        override fun onBindViewHolder(holder: BenfitVH, position: Int) {
            val benfit =datas[position]
            holder.binding.tvBenfitTitle.text = benfit.summaryTitle
            holder.binding.imgBenfit.load(benfit.image) {
                placeholder(R.mipmap.ic_launcher)
                error(R.mipmap.ic_launcher)
            }
            holder.binding.tvBenfitDesc.text = benfit.description
        }

        override fun getItemCount(): Int {

            return datas.size
        }

        class BenfitVH(val binding:BenfitItemBinding):RecyclerView.ViewHolder(binding.root) {

        }
    }
}
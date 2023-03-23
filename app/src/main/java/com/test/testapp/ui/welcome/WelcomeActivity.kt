package com.test.testapp.ui.welcome

import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.testapp.base.BaseActivity
import com.test.testapp.base.BaseViewModel
import com.test.testapp.databinding.ActivityWelcomeBinding
import com.test.testapp.ui.productdetail.ProductDetailActivity
import com.test.testapp.ui.welcome.data.CategoryItem
import com.test.testapp.ui.welcome.data.Product
import com.test.testapp.ui.welcome.vm.CategoryVM
import com.test.testapp.utils.ToastUtils

class WelcomeActivity:BaseActivity<ActivityWelcomeBinding>() {

    override fun getViewBinding(): ActivityWelcomeBinding {
        return ActivityWelcomeBinding.inflate(layoutInflater)
    }

   lateinit var productAdapter:ProductAdapter
   lateinit var categoryAdapter: CategoryAdapter
   lateinit var categoryVM:CategoryVM
   private var mHandler: Handler = Handler(Looper.getMainLooper())
    override fun initViews() {
        super.initViews()
        categoryVM = CategoryVM()


        productAdapter = ProductAdapter()
        categoryAdapter = CategoryAdapter()
        categoryAdapter.onItemClick = {categoryItem: CategoryItem, pos: Int ->

            productAdapter.setNewData(categoryItem.products)
        }

        productAdapter.onItemClick = {item: Product, pos: Int ->
            val intent = Intent(this@WelcomeActivity,ProductDetailActivity::class.java)
            intent.putExtra("productUrl",item.productDetails)
            startActivity(intent)
        }
        binding.listProducts.layoutManager = LinearLayoutManager(this)
        binding.listCategorys.layoutManager = LinearLayoutManager(this)
        binding.listProducts.adapter = productAdapter
        binding.listCategorys.adapter = categoryAdapter

        categoryVM.categoryRes.observe(this) {
            if (it == null) {
                ToastUtils.showToast("there is an error in the Requesting")
            }else {
                categoryAdapter.addDatas(it)
                if (categoryAdapter.itemCount>0) {
                    mHandler.postDelayed({
                      binding.listCategorys.findViewHolderForAdapterPosition(0)?.itemView?.performClick();
                    },500)
                }

            }
        }

        categoryVM.getCategory({showLoading()},{dissLoading()})
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeMessages(0)
    }


}
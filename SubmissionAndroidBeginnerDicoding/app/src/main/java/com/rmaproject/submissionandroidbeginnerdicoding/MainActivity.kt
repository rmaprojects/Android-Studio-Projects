package com.rmaproject.submissionandroidbeginnerdicoding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaproject.submissionandroidbeginnerdicoding.databinding.ActivityMainBinding
import com.rmaproject.submissionandroidbeginnerdicoding.model.ProductModel
import com.rmaproject.submissionandroidbeginnerdicoding.theme.ThemeSharedPreferences
import com.rmaproject.submissionandroidbeginnerdicoding.ui.adapter.RecyclerViewAdapter
import com.rmaproject.submissionandroidbeginnerdicoding.ui.pages.DetailPage
import com.rmaproject.submissionandroidbeginnerdicoding.ui.pages.ProfilePage

class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCurrentTheme()
        val splashScreen = installSplashScreen()

        setContentView(R.layout.activity_main)

        splashScreen.setKeepOnScreenCondition {false}

        val productList : MutableList<ProductModel> = getProductList()
        setAdapter(productList)
        setTopAppbar()
    }

    private fun setTopAppbar() {
        binding.toolBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.btn_switch_dark_mode -> {
                    when (ThemeSharedPreferences(this).theme) {
                        0 -> {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                            ThemeSharedPreferences(this).theme = 1
                        }
                        1 -> {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                            ThemeSharedPreferences(this).theme = 0
                        }
                    }
                    true
                }
                R.id.nav_about -> {
                    startActivity(Intent(this, ProfilePage::class.java))
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun getProductList(): MutableList<ProductModel> {
        val result = mutableListOf<ProductModel>()
        val productList = resources.getStringArray(R.array.product_list)

        productList.forEach { productListRaw ->
            val rippedProductList = productListRaw.split("|")
            val productName = rippedProductList[0]
            val productPrice = rippedProductList[1]
            val productDescription = rippedProductList[2]
            val productRating = rippedProductList[3]
            val productUrlImage = rippedProductList[4]
            val productUrlStore = rippedProductList[5]
            val productModel = ProductModel(productName, productPrice, productDescription, productRating, productUrlImage, productUrlStore)

            result.add(productModel)
        }

        return result
    }

    private fun setCurrentTheme() {
        when (ThemeSharedPreferences(this).theme) {
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }

    private fun setAdapter(productList: MutableList<ProductModel>) {
        val adapter = RecyclerViewAdapter(productList)
        binding.recyclerView.adapter = adapter

        adapter.onItemClickCallback = { productModel ->
            startActivity(Intent(this, DetailPage::class.java).apply {
                putExtra(DetailPage.PRODUCT_KEY, productModel)
            })
        }
    }


}
package com.rmaproject.submissionandroidbeginnerdicoding.ui.pages

import android.content.Intent
import android.hardware.display.DeviceProductInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.net.toUri
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.rmaproject.submissionandroidbeginnerdicoding.R
import com.rmaproject.submissionandroidbeginnerdicoding.databinding.ActivityDetailBinding
import com.rmaproject.submissionandroidbeginnerdicoding.model.ProductModel

class DetailPage : AppCompatActivity() {

    private val binding:ActivityDetailBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val productInfo = intent.extras?.getParcelable<ProductModel>(PRODUCT_KEY) as ProductModel

        setupTextValues(productInfo)
        setupButtons(productInfo)

    }

    private fun setupButtons(productInfo: ProductModel) {
        binding.toolBar.setNavigationOnClickListener {
            finish()
        }
        binding.btnShare.setOnClickListener {
            shareProduct(productInfo)
        }
        binding.btnStore.setOnClickListener {
            openStore(productInfo)
        }
    }

    private fun openStore(productInfo: ProductModel) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = productInfo.productStoreUrl.toUri()
        startActivity(intent)
    }

    private fun shareProduct(productInfo: ProductModel) {
        val intentShareProduct = Intent()
        intentShareProduct.type = "text/plain"
        intentShareProduct.action = Intent.ACTION_SEND
        intentShareProduct.putExtra(Intent.EXTRA_TEXT, "Ayo beli ${productInfo.productName} hanya di: ${productInfo.productStoreUrl}")
        startActivity(Intent.createChooser(intentShareProduct, "Share ${productInfo.productName} via..."))
    }

    private fun setupTextValues(productInfo: ProductModel) {
        binding.imgProduct.load(productInfo.productImageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_baseline_image_24)
        }

        binding.txtProductDetail.text = productInfo.productDescription
        binding.txtPriceAndRating.text = "Rp.${productInfo.productPrice} | ${productInfo.productRating}"
        binding.toolBar.title = productInfo.productName
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    companion object {
        const val PRODUCT_KEY = "KEYPRODUCT"
    }
}
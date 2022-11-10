package com.rmaproject.appcatalog

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rmaproject.appcatalog.databinding.ActivityKulinerBinding

class KatalogActivity : AppCompatActivity() {

    private val binding: ActivityKulinerBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kuliner)

        val menuMakanan = arrayListOf(
            "Lenovo Ideapad 330",
            "Acer Aspire ES1-132",
            "Lenovo V14-ADA-DID",
            "Lenovo Ideapad S145",
            "HP Pavilion 13-AN1033TU",
            "Lenovo Ideapad S340",
            "Acer Nitro 5 AN515-52-51T2",
            "Lenovo IdeaPad L340",
            "Huawei Matebook D15",
            "ASUS TUF Gaming FX505DY-R5561T"
        )

        val hargaMenu = arrayListOf(
            3700000,
            3010000,
            5050000,
            5499000,
            7500000,
            7499000,
            9250000,
            9500000,
            8700000,
            9939000
        )

        val listImage = arrayListOf(
            R.drawable.idepad_330,
            R.drawable.acer_aspire_es1,
            R.drawable.lenovo_v14,
            R.drawable.lenovo_ideapad_s145,
            R.drawable.hp_pavillon_13,
            R.drawable.lenovo_ideapad_s340,
            R.drawable.acer_nitro_5,
            R.drawable.lenovo_ideapad_l340,
            R.drawable.huawei_d15,
            R.drawable.tuf_gaming
        )

        val listRating = arrayListOf(
            2F,
            3F,
            2.5F,
            3.5F,
            4F,
            5F,
            4F,
            4.5F,
            3.5F,
            5F,
        )

        setMenuAdapter(menuMakanan, hargaMenu, listImage, listRating)



        val pilihanTema = arrayOf(
            "Dark Mode",
            "Light Mode"
        )

        binding.topAppBar.setOnMenuItemClickListener{ menuItem ->
            when (menuItem.itemId) {
                R.id.setDarkMode -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Pilih Tema Warna")
                        .setItems(pilihanTema) { _, which ->
                            when (which){
                                0 -> {
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                                }
                                1 -> {
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                                }
                            }
                        }
                        .show()
                    true
                }
                R.id.aboutMe -> {
                    startActivity(Intent(this, AboutMeActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }

//        binding.recyclerView.addOnScrollListener(object: OnScrollListener(){
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//            }
//        })
    }

    private fun setMenuAdapter(menuMakanan:ArrayList<String>, hargaMenu:ArrayList<Int>, listImage:ArrayList<Int>, listRating:ArrayList<Float>) {
        val adapter = RecyclerViewAdapter(menuMakanan, hargaMenu, listImage, listRating)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.gotoDetailMenuActivity = { position ->
            var imageMenu = listImage[position]
            var hargaMakanan = hargaMenu[position]
            var namaMenu = menuMakanan[position]
            val intent = Intent(this, KatalogMenuActivity::class.java)
            val bundle = Bundle()
            bundle.putString(KatalogMenuActivity.KEYNAMAMENU, namaMenu)
            bundle.putInt(KatalogMenuActivity.KEYIMAGE, imageMenu)
            bundle.putInt(KatalogMenuActivity.KEYHARGAMENU, hargaMakanan)
            bundle.putInt(KatalogMenuActivity.POSKEY, position)
            intent.putExtras(bundle)
            startActivity(intent)
            finish()
        }

    }

}
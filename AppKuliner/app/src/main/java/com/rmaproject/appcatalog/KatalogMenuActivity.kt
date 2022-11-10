package com.rmaproject.appcatalog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ShareCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaproject.appcatalog.databinding.ActivityDetailMenuBinding

class KatalogMenuActivity : AppCompatActivity() {

    private val binding: ActivityDetailMenuBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_menu)
        val getter = intent.extras
        val menuName = getter!!.getString(KEYNAMAMENU)
        val hargaMenu = getter.getInt(KEYHARGAMENU)
        val imageMenu = getter.getInt(KEYIMAGE)
        val position = getter.getInt(POSKEY)

        binding.detailImage.setImageResource(imageMenu)
        binding.detailNamaMenu.text = menuName
        binding.detailHargaMenu.text = "Rp.${hargaMenu}"
        binding.topAppBar.setNavigationOnClickListener {
            startActivity(Intent(this, KatalogActivity::class.java))
            finish()
        }

        val menuDesc = arrayListOf(
            "Simpel namun berperforma tinggi merupakan inspirasi terbaik dalam memilih Lenovo Ideapad 330 sebagai rekomendasi laptop gaming murah.\n" +
                    "\n" +
                    "Pasalnya, laptop ini sudah dilengkapi oleh SSD dengan chipset AMD Radeon sehingga sangat cocok untuk bekerja sambil gaming secara optimal.\n" +
                    "\n" +
                    "Laptop ini diklaim mampu memenuhi kebutuhan gaming ringan, sehingga kamu pun tidak akan kebosanan saat bekerja. ",

            "Mudah dibawa kemana-mana adalah kunci utama dalam memilih Acer Aspire ES51 dengan spesifikasi memadai.\n" +
                    "\n" +
                    "Tak hanya sekadar cukup untuk bermain game ringan, laptop ini juga memiliki layar resolusi HD dengan prosesor AMD dan ram 2GB.\n" +
                    "\n" +
                    "Selain itu, harga yang ditawarkan juga sangat terjangkau, yakni mulai Rp 3 jutaan, sekalipun lebih murah dari smartphone. \n" +
                    "\n",

            "Dalam beberapa waktu ke Belakang, Lenovo selalu dipilih oleh banyak pencari laptop gaming terbaik.\n" +
                    "\n" +
                    "Kamu pun bisa memilih Lenovo V14 dengan kelengkapan fitur dengan prosesor AMD Athlon Gold dengan grafis Radeon RX Vega 3.\n" +
                    "\n" +
                    "Tak hanya itu, laptop murah ini juga dilengkapi oleh SSD 256 GB, sehingga bermain game pun semakin ngebut.",

            "Dengan harga Rp5 jutaan, laptop gaming cantik paket lengkap bisa kamu jadikan referensi bersama Lenovo Ideapad S145.\n" +
                    "\n" +
                    "Laptop gaming dengan harga Rp5 jutaan ini sudah dilengkapi oleh prosesor AMD Ryzen 3 dengan HDD 1 TB.\n" +
                    "\n" +
                    "Adapun, untuk kebutuhan gaming kelas sederhana laptop ini sudah lebih dari cukup untuk memenuhi program keseluruhan.",

            "Untuk kamu yang membutuhkan laptop gaming murah dengan portabilitas maksimal dan berlayar ringkas, HP Pavilion 13-AN1033TU merupakan pilihan yang menarik. \n" +
                    "\n" +
                    "Meski laptop ini menggunakan Intel i3 generasi ke-10, namun Intel mengklaim jika performa laptop ini mampu memainkan game-game online ringan.\n" +
                    "\n" +
                    "Tak hanya dari sisi kemampuan dan portabilitas yang baik, laptop ini juga memiliki layar dengan resolusi HD yang berkualitas tinggi. \n" +
                    "\n" +
                    "Pengalaman grafis dan performa yang baik menjadi pilihan tepat untuk meningkatkan pengalaman bermain. ",
            "Praktis dan ringan merupakan referensi penting dalam memilih laptop gaming murah seperti Lenovo Ideapad S340.\n" +
                    "\n" +
                    "Tak hanya sekadar kencang, laptop ini juga mempunyai performa tinggi sekalipun sudah dilengkapi oleh SSD 512GB.\n" +
                    "\n" +
                    "Harga yang ditawarkan pun juga menarik, yakni mulai dari Rp7 jutaan sampai Rp10 jutaan untuk varian termahal dengan prosesor Intel. ",
            "Produsen laptop asal Taiwan ini terkenal dengan kepiawaiannya dalam menghasilkan laptop-laptop berkualitas.\n" +
                    "\n" +
                    "Meski terkenal sebagai merek laptop personal, namun Acer memberanikan diri dengan mengeluarkan Acer Nitro 5 AN515-52-51T2 sebagai laptop gaming murah mereka. \n" +
                    "\n" +
                    "Meski laptop 15 inci ini sudah cukup berumur, namun kelengkapan laptop ini masih sangat relevan untuk 2021. \n" +
                    "\n" +
                    "Pasalnya, laptop ini dilengkapi oleh RAM 8GB, dengan SSD 1TB yang sangat memadai untuk kebutuhan bermain sekalipun bekerja harian. ",
            "Jika kamu menginginkan laptop gaming sebagai salah satu jenis investasi yang baik, Lenovo IdeaPad L340 merupakan pilihan yang sangat menarik. \n" +
                    "\n" +
                    "Sebab, laptop ini memiliki desain yang sangat timeless dengan inspirasi hitam nan elegan yang memanjakan mata. \n" +
                    "\n" +
                    "Tak hanya dari sisi desain, laptop ini juga memiliki kelengkapan fitur yang sangat baik, bahkan sisi penyimpanan laptop ini merupakan keunggulan utamanya. \n" +
                    "\n" +
                    "Jika laptop lainnya hanya memiliki satu ruang penyimpanan, Lenovo IdeaPad ini memiliki dua ruang penyimpanan dengan total 1,5 TB.",
            "Baru-baru ini, Huawei meluncurkan laptop terbaru mereka yakni Huawei Matebook D15. \n" +
                    "\n" +
                    "Meski bukan laptop gaming murni, namun bisa dikatakan jika laptop ini sangat memadai untuk kebutuhan bermain game. \n" +
                    "\n" +
                    "Pasalnya, laptop ini dilengkapi oleh prosesor AMD Ryzen 5 yang sangat baik untuk kebutuhan bermain game. \n" +
                    "\n" +
                    "Tak hanya dilengkapi prosesor yang tinggi, Huawei Matebook D15 juga memiliki bobot yang sangat ringan yakni 1,5kg. \n" +
                    "\n" +
                    "Jika dilihat dari sisi desain, kapan lagi kamu memiliki laptop gaming dengan pengalaman seperti ultrabook. ",
            "Jika kamu memimpikan produk Asus ROG namun anggaran dana tidak sampai, laptop ini bisa menjadi pilihan yang menarik.\n" +
                    "\n" +
                    "Pasalnya, Asus TUF series ini kerap dinilai sebagai “Baby ROG”, dengan spesifikasi yang mumpuni. \n" +
                    "\n" +
                    "Banyak ulasan menarik mengenai laptop ini yang sangat tepat sebagai laptop budget gaming terbaik. \n" +
                    "\n" +
                    "Adapun laptop ini dilengkapi oleh RAM 8gb dengan prosesor AMD Ryzen 5 yang sangat cukup untuk kebutuhan bermain harian. \n" +
                    "\n" +
                    "Game online seperti CS GO, PES 2021, DOTA, dan PUBG dapat berfungsi dengan sempurna. "
        )

        binding.descriptionMenu.text = menuDesc[position]

        binding.shareButton.setOnClickListener {
            val text: String =
                """$menuName
                    |${menuDesc[position]}
                    |
                    |Beli hanya di RMA Tech Projects!
                """.trimMargin()

            ShareCompat.IntentBuilder(this)
                .setText(text)
                .setType("text/plain")
                .setChooserTitle("Share Menu")
                .startChooser()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val back = Intent(this, KatalogActivity::class.java)
        startActivity(back)
        finish()
    }

    companion object {
        const val KEYNAMAMENU = "NAMAMENUKEY"
        const val KEYHARGAMENU = "HARGAMENUKEY"
        const val KEYIMAGE = "IMAGEKEY"
        const val POSKEY = "KEYPOS"
    }

}
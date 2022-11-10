package com.rmaproject.belajarkotlin.fundamentals

fun main() {
    //Apa itu Indexing?
    //Indexing merupakan sebuah cara yang memudahkan kita untuk mengakses elemen yang berada di dalam sebuah
    // Collection dengan memanfaatkan index atau posisi dari elemen tersebut.
    // Posisi dari sebuah elemen pada umumnya dimulai dari angka 0.

    val arrayTest = arrayOf("Mamang", "babang", "mamank")
    val mamang = arrayTest[0]   //index 0   //index1   //index2

    println(mamang) //Mamang

    //Nilai 0 yang berada pada indexing di atas adalah posisi karakter yang akan diakses.
    // Selain itu, kita juga dapat melakukan iterasi terhadap objek String dengan menggunakan for-loop seperti
    // berikut:

    val namaGwejh = "Raka Muhammad Al-Hafidz"
    for (i in namaGwejh) {
        println(i)
    }

    val intArray = Array(13) { i -> i * 1 }
    val arrayMutable = mutableListOf<Int>()
    intArray.forEach {
        arrayMutable.add(it)
    }
    print(arrayMutable)

}
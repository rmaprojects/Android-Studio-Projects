package com.rmaproject.belajarkotlin.controlflows

import kotlin.random.Random

fun main() {
/*
Apa itu Control Flow?

Ketika kita mengembangkan sebuah program, tentu kita harus tahu seperti apa alurnya. Control flow adalah cara kita mengontrol alur dari sebuah program berdasarkan kondisi saat program tersebut berjalan.

Terdapat beberapa bagian dari control flow yang akan kita pelajari, antara lain:

    1. Enumeration

    2. When Expression

    3. Expression & Statement

    4. While and Do While

    5. Range and For Loop

    6. Break and Continue Labels
*/

    //Enumerasi:
    val rasKulit = RasKulit.WHITE

    when (rasKulit) {
        RasKulit.BLACK -> {
            println("Ras kulit hitam")
        }
        RasKulit.WHITE -> {
            println("Ras kulit putih")
        }
    }

    //when statement

    val mamang:Any = 12312312

    when (mamang) {
        is Int -> {
            println("Mamang adalah String")
        }
        is Float -> {
            println("Mamang adalah String")
        }
        else -> {
            println("Mamang bukan String")
        }
    }

    val value =  27
    val ranges = 10..50

    /*
    Selain itu, when expression juga bisa kita gunakan untuk memeriksa nilai yang terdapat pada sebuah Range atau Collection.
    Range sendiri merupakan salah satu tipe data yang unik di mana kita dapat menentukan nilai awal dan nilai akhir.
    Range dan Collection akan dibahas terpisah pada sub-modul berikutnya.
    Berikut adalah contoh saat kita hendak mengecek apakah sebuah nilai ada di dalam sebuah Range atau tidak.
     */

    when(value){
        in ranges -> println("value is in the range")
        !in ranges -> println("value is outside the range")
        else -> println("value undefined")
    }

//    Sejak Kotlin 1.3, kita dapat menangkap subjek dari when expression di dalam sebuah variabel. Contohnya seperti berikut:
    val registerNumber = when(val regis = getRegisterNumber()){
        in 1..50 -> 50 * regis
        in 51..100 -> 100 * regis
        else -> regis
    }

    //While dan Do While

    //Contoh While loop:
    var i = 0
    while (i < 10){
        println("i = $i")
        i++
    }

    //Contoh Do While loop:
    do {
        println("i = $i")
        i++
    } while (i < 10)

    /* Range */

    //Contoh Range:
    val rangeInt = 1..10
    val rangeIntWithStep = 1..10 step 2

    rangeInt.forEach {
        print("$it ")
    }
    println(rangeInt.step)

    rangeIntWithStep.forEach {
        print("$it ")
    }
    println(rangeIntWithStep.step)

    //Bisa juga menggunakan .downTo() atau .rangeTo() untuk menentukan nilai awal dan nilai akhir dari Range.

    //Contoh Range:
    val rangeIntDownTo = 10.downTo(1)
    val rangeIntTo = 1.rangeTo(10)

    //Untuk mengecek apakah didalam range ada 7
    val tenToOne = 10.downTo(1)
    if (7 in tenToOne) {
        println("Value 7 available")
    }

    //Contoh For Loop:
    for (forVariable in 1..10){
        println("i = $forVariable")
    }

    val range = 1.rangeTo(5)
    for (rangeValue in ranges){
        println("value is $rangeValue!")
    }


}

fun getRegisterNumber() = Random.nextInt(100)

enum class RasKulit(val value:Int) {
    WHITE(0),
    BLACK(1),
}
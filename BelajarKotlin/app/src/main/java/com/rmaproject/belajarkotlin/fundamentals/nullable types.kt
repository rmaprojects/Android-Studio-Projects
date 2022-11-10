package com.rmaproject.belajarkotlin

fun main() {
//    val text:String = null    //compile time error
    val text:String? = null     //compile time success
    println(text)

//val textLength = text.length // compile time error

    if (text != null){
        val textLength = text.length // ready to go
    }

    /*Elvis Operator (?:)
    Elvis operator memungkinkan kita untuk menetapkan default value atau nilai dasar jika objek bernilai null.
     */

    val teks: String? = null
    val teksLength = text?.length ?: 7
//    Kode di atas sebenarnya sama seperti ketika kita menggunakan if/else berikut:
    val textLength = if (text != null) text.length else 7
//    Elvis akan mengembalikan nilai text.length jika text tidak bernilai null. Sebaliknya, jika text bernilai null maka default value yang akan dikembalikan.
}
package com.rmaproject.belajarkotlin

fun main() {
    val name = "Mamang"

    print("My name is...")
    print(name)

    print(if (name == "Mamang") "Mamang" else "Not Mamang") //inline if else

    //Escaped String
    /*
    Kotlin memiliki dua jenis tipe Literal String, yang pertama adalah Escaped String yang memungkinkan kita
     untuk mengurangi ambiguitas nilai yang berada di dalam sebuah String.
      Misalnya ketika kita mendefinisikan sebuah String berikut:
     */

    val statement = "Kotlin is Awesome!"
    //Maka jika ingin mengetikkan "Kotlin is "Awesome!""
    //maka kita harus menggunakan escape sequence seperti ini:

    val statementModified = "Kotlin is \"Awesome!\""

    //Raw string example
    val rawStringExample = """Ini adalah raw string example"""
    val test = "mamang \t "
}
package com.rmaproject.belajarkotlin

fun main() {
    val mamang = "Mamangk"
    mamang.tambah()
    println("Hello World")
    print(mamang.tambah())
}


fun String.tambah() : String {
    return "Hello $this"
}
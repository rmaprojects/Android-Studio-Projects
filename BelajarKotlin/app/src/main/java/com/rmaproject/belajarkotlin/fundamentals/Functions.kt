package com.rmaproject.belajarkotlin

fun main() {
    val nama = readLine().toString()
    val umur = readLine()?.toInt()

    val setUser = setUser(nama, umur!!.toInt())
    println(setUser)
    printUser(nama)
}

fun printUser(name:String) {
    println(name)
}

private fun setUser(name:String, age:Int) : String {
    return "Nama mu adalah $name, dan umurmu adalah: $age"
}
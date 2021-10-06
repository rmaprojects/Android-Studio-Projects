package com.rmaproject.quran


fun main() {
//    println("Ini ngeprint Bismillah")
//    bismillah()
//    println(TestReturnValueInt(1,2))
//    println(TestReturnValueString())
//    println(TestReturnValueBoolean())
    //VARIABLE (VAL & VAR)


//    val kondisilampu = readLine()
//    print(kondisilampu)
//WhenControlFlow()
//    var nama:String
//    print("Nama: ")
//    nama = readLine()!!
//
//    var inputNilai:Int
//    print("Masukkan Nilai: ")
//    inputNilai = readLine()!!.toInt()
//
//    var kelas:String
//    print("Masukkan Kelas: ")
//    kelas = readLine()!!
//
//    println(nama)
//    println(kelas)
//    println(cekNilaiWhen(inputNilai))
//    println(cekNilaiIf(inputNilai))

//    var nama:String = null  //Kalau g pakek tanda tanya g boleh kosong
//    var nama2:String? = null    //Kalau ada tanda tanya boleh kosong null

//    nama = "Babang"
//    nama = "Mamang"

//    println(nama)
//    println(nama2)
    ForLoop()
    ArrayLoop()
}
fun cekNilaiWhen(nilai:Int):String {

    var HasilAkhir = ""

    when (nilai) {
        in 0..20 -> {
            HasilAkhir = "Nilai E"
        }
        in 21..40 -> {
            HasilAkhir = "Nilai D"
        }
        in 41..60 -> {
            HasilAkhir = "Nilai C"
        }
        in 61..80 -> {
            HasilAkhir = "Nilai B"
        }
        in 81..100 -> {
            HasilAkhir = "Nilai A"
        }
        else -> println("Grade tak tahu")
    }

    return HasilAkhir
}

fun cekNilaiIf (nilai:Int):String {
    var HasilAkhir = ""

    if (nilai <= 20) {
        HasilAkhir = "Nilai E"
    } else if (nilai <= 40) {
        HasilAkhir = "Nilai D"
    } else if (nilai <= 60) {
        HasilAkhir = "Nilai C"
    } else if (nilai <= 80) {
        HasilAkhir = "Nilai B"
    } else if (nilai <= 100) {
        HasilAkhir = "Nilai A"
    }

    return HasilAkhir
}

fun bismillah(){ //Tidak mengembalikan nilai
    println("Bismillah")
}

fun TestReturnValueInt(bilangan1 : Int, bilangan2 : Int) : Int { //Mengembalikan Nilai
    val jumlah = bilangan1 + bilangan2
    return jumlah
}

fun TestReturnValueInt() : Int {
    return 0
}

fun TestReturnValueString() : String {
    return "Function String"
}

fun TestReturnValueBoolean() : Boolean {
    return true
}

fun StatementUseless() {
    val angka = 5 //Val tidak dapat diubah
    var nilai = 2  //Var dapat diubah

    var babang:String = "Babang"
    var bab :Char = 'b'

    //if-else statement
    if (babang.equals("Babang")) {
        println("Hello World")
    } else {
        println("Goodbye World")
    }
    //switch statement (when)
    var angka1:Int = 20
    var angka2:Int = 20
    var jumlah:Int
}

fun WhenControlFlow() {

    val LampuMerah = "Merah"
    val LampuKuning = "Kuning"
    val LampuHijau = "Hijau"

    print("Warna lampunya apa? ")
    val WarnaLampu =  readLine()!!


    when (WarnaLampu) {
        LampuHijau ->  {
            println("Jalan")
            println("Apa lagi? Jalan coy")
            //Kalau perintahnya ada lebih dari 1, pakai kurawal. Kalau hanya 1, g bisa
        }
        LampuKuning -> println("Siap-Siap")
        LampuMerah -> println("BERHENTI")
        else -> println("Anda memangnya di Jepang, lampunya Biru?")

    }
}

fun ForLoop() {
    val i:Int = 0

    //".." = <
    // "until" = <=
    // "%" (Modulus) = sisa hasil pembagian
    for (i in 1..10){
        println(i)
    }
    for (i in 1 until 10){
        println(i)
    }
    for (i in 1..10){
        if (i % 2!= 0){
            println("$i Ganjil")
        } else {
            println("$i Genap")
        }
    }
}

fun ArrayLoop(){
    val Fruits: Array<String> = arrayOf("Apel", "Sirsak", "Manggis")
    for (i in Fruits[1]){
        print(i)
    }
}


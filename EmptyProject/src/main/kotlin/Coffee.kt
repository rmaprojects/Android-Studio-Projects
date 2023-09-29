/**
 * TODO: 1. Buat 3 jenis-jenis kopi dengan meng-extend class Coffee seperti contoh
 */
abstract class Coffee(
    val coffeeName: String,
    val coffeePrice: Int
)

//Contoh: (Buat 3 lagi yak)
class Espresso: Coffee(
    coffeeName = "Expresso",
    coffeePrice = 1000
)
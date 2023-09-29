import kotlinx.coroutines.delay

/**
 * Note: JANGAN MENGGANTI FILE INI,
 * MENGGANTI FILE INI ATAU MEMODIFIKASI SERTA MENGOTAK-ATIK FILE INI
 * = NILAI 0
 */

abstract class CoffeeOrder(
    open val consumerName: String,
    open val coffee: Coffee
) {
    abstract fun orderFinished()
}

class RegisteredCoffeeOrder(
    override val consumerName: String,
    override val coffee: Coffee
): CoffeeOrder(
    consumerName, coffee
) {

    override fun orderFinished() {
        throw Exception("Kopi yang belum dibuat tidak bisa diserahkan ke consumer")
    }
}

class ProcessedCoffeeOrder(
    override val consumerName: String,
    override val coffee: Coffee
): CoffeeOrder(
    consumerName,
    coffee
) {
    override fun orderFinished() {
        println("Pesanan $consumerName dengan kopi: ${coffee.coffeeName} sudah jadi")
    }
}

suspend fun processCoffeeOrder(coffeeOrder: RegisteredCoffeeOrder): ProcessedCoffeeOrder  {
    delay(2000)
    return ProcessedCoffeeOrder(
        coffeeOrder.consumerName,
        coffeeOrder.coffee
    )
}

suspend fun registerCoffeeOrder(consumerName: String, coffee: Coffee): RegisteredCoffeeOrder {
    delay(2000)
    return RegisteredCoffeeOrder(
        consumerName,
        coffee
    )
}

sealed class OrderState {
    data class Register(val coffeeOrder: RegisteredCoffeeOrder): OrderState()
    data class Processing(val coffeeOrder: RegisteredCoffeeOrder): OrderState()
    data class CompletedOrder(val coffeeOrder: ProcessedCoffeeOrder): OrderState()
}
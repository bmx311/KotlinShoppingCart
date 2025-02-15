package shoppingCart

/**
 * Returns a map of productId -> MutableMap("name" to String, "price" to Double, "stock" to Int).
 * Your task: Add exactly 5 items with correct details (IDs 1..5).
 * Example:
 *   1 -> { "name"="Laptop", "price"=350.000, "stock"=10 },
 *   2 -> { "name"="Smart TV", "price"=200.000, "stock"=5 },
 *   3 -> { "name"="Headphones", "price"=50.000, "stock"=20 },
 *   4 -> { "name"="Gaming Console", "price"=150.000, "stock"=8 },
 *   5 -> { "name"="Wireless Mouse", "price"=8.500, "stock"=15 },
 */
fun createStoreInventory(): MutableMap<Int, MutableMap<String, Any>> {
    return mutableMapOf(
        1 to mutableMapOf("key1" to "Laptop", "price1" to 350.00, "stock" to 10),
        2 to mutableMapOf("key2" to "Smart TV", "price2" to 200.00, "stock" to 5),
        3 to mutableMapOf("key3" to "Headphones", "price3" to 50.00, "stock" to 20),
        4 to mutableMapOf("key4" to "Gaming Console", "price4" to 150.00, "stock" to 8),
        5 to mutableMapOf("key5" to "Wireless Mouse", "price5" to 8.500, "stock" to 15)
    )
}

    /**
     * Tries to add [quantity] of [productId] to [cart], if there's enough stock in [storeInventory].
     * - If valid, reduce the stock and increase the cart quantity for this product.
     * - Return true if successful, false otherwise.
     */
    fun addToCart(
        storeInventory: MutableMap<Int, MutableMap<String, Any>>,
        cart: MutableMap<Int, Int>,
        productId: Int,
        quantity: Int
    ): Boolean {
        if (!storeInventory.containsKey(productId)) {
            return false
        }

        // Get the current stock of the product
        val productDetails = storeInventory[productId]!!
        val currentStock = productDetails["stock"] as Int

        // Check if there's enough stock to add to cart
        if (quantity > currentStock) {
            return false
        }

        // Reduce the stock in the inventory
        productDetails["stock"] = currentStock - quantity

        // Update the cart
        // If product already in cart, increase quantity
        // If not, add new entry
        cart[productId] = (cart[productId] ?: 0) + quantity

        return true

    }


    /**
     * Removes [quantity] of [productId] from [cart], restoring the same amount to [storeInventory].
     * - If cart has enough of this product, remove it and restore stock.
     * - Return true if successful, false otherwise.
     */
    fun removeFromCart(
        storeInventory: MutableMap<Int, MutableMap<String, Any>>,
        cart: MutableMap<Int, Int>,
        productId: Int,
        quantity: Int
    ): Boolean {
        if (!cart.containsKey(productId)) {
            return false
        }

        val currentCartQuantity = cart[productId]!!

        if (quantity > currentCartQuantity) {
            return false
        }

        // Update the cart
        cart[productId] = currentCartQuantity - quantity

        // Remove the product from the cart if quantity becomes zero
        if (cart[productId] == 0) {
            cart.remove(productId)
        }

        // Restore the stock in the inventory
        val productDetails = storeInventory[productId]!!
        productDetails["stock"] = (productDetails["stock"] as Int) + quantity

        return true
    }
    /**
     * Calculates the total cost of items in [cart].
     * - For each (productId -> quantity), retrieve the price from [storeInventory].
     * - Multiply (price * quantity) and sum for the final total.
     */
    fun calculateTotal(
        storeInventory: MutableMap<Int, MutableMap<String, Any>>,
        cart: MutableMap<Int, Int>
    ): Double {
        return cart.entries.sumOf { (productId, quantity) ->
            val price = storeInventory[productId]!!["price"] as Double
            price * quantity
        }
    }

    /**
     * Returns a list of product IDs whose "name" contains [keyword].
     * - For example, if keyword = "lap", then "Laptop" (ID 1) should match.
     * - Matching is case-insensitive.
     */
    fun filterProductsByName(
        storeInventory: MutableMap<Int, MutableMap<String, Any>>,
        keyword: String
    ): List<Int> {
        return storeInventory.entries
            .filter { (_, details) ->
                (details["name"] as String).lowercase().contains(keyword.lowercase())
            }
            .map { it.key }
    }

    /**
     * Entry point for the project.
     * Run the JUnit tests to see if you've implemented each function correctly.
     */
    fun main() {
        println("Welcome to the Kotlin Store project!")
        println("All functions currently throw NotImplementedError.")
        println("Open each function and replace TODO(...) with your implementation.")
    }

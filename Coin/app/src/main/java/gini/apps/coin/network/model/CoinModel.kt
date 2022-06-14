package gini.apps.coin.network.model

data class CoinModel (
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)

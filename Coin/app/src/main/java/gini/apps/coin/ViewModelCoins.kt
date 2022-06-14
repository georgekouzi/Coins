package gini.apps.coin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gini.apps.coin.network.ApiManager
import kotlinx.coroutines.launch
import java.util.Locale.filter
import kotlin.text.Typography.dollar

const val DOLLAR = "USD"

class ViewModelCoins : ViewModel() {


    private val _coinsData = MutableLiveData<Map<String, Double>>()
    val coinsData: LiveData<Map<String, Double>> get() = _coinsData
    private val coinManagement = Coin()
    private val allCoinsDataAboveDollar: MutableMap<String, Double> = mutableMapOf()
    private val allCoinsDataBelowDollar: MutableMap<String, Double> = mutableMapOf()

    val CoinsDataAboveDollar get() = allCoinsDataAboveDollar.toMap()
    val CoinsDataBelowDollar get() = allCoinsDataBelowDollar.toMap()

    fun showCoin() {
        viewModelScope.launch {
            val result = ApiManager.create().coin().rates
            coinManagement.update(result)
            _coinsData.postValue(coinManagement.coinsData)
            getAllCoinsDataAboveDollar()
            getAllCoinsDataBelowDollar()
        }
    }

    fun getAllCoinsDataAboveDollar() {
        allCoinsDataAboveDollar.putAll(coinManagement.coinsData.filter { (_, value) -> value <= coinManagement.coinsData[DOLLAR]!! }
            .toMap())
    }

    fun getAllCoinsDataBelowDollar() {
        allCoinsDataBelowDollar.putAll(coinManagement.coinsData.filter { (_, value) -> value > coinManagement.coinsData[DOLLAR]!! }
            .toMap())
    }
}
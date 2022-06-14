package gini.apps.coin

import android.util.Log
class Coin(private val _coinsData: MutableMap<String, Double> = mutableMapOf()){
    val coinsData :MutableMap<String, Double> get() = _coinsData
    fun update(map: Map<String, Double>){
        _coinsData.putAll(map)
    }
}
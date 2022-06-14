package gini.apps.coin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import gini.apps.coin.adapters.Adapter
import gini.apps.coin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding
        get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[ViewModelCoins::class.java]
        viewModel.showCoin()
        viewModel.coinsData.observe(this) { mapOfCoins ->
            setCoinRecycle(mapOfCoins)
        }




    }


    private fun setCoinRecycle(listOfCoin : Map<String,Double>){
        binding.myRecycle.layoutManager = GridLayoutManager(this,2)
        binding.myRecycle.setHasFixedSize(true)
        val placeAdapter = Adapter(listOfCoin)
        binding.myRecycle.adapter =placeAdapter


    }
}
package com.robo.eventdiscovery


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.robo.eventdiscovery.adapter.ProductAdapter
import com.robo.eventdiscovery.model.Product
import com.robo.eventdiscovery.viewmodel.MainViewModel
import com.robo.eventdiscovery.viewmodel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val products: RecyclerView
        get() = findViewById(R.id.rcProducts)
    private val loadingProgressBar: ProgressBar
        get() = findViewById(R.id.progressBar)
    private lateinit var productAdapter: ProductAdapter
    private var productList = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as EventDiscoveryApplication).applicationComponent.inject(this)

        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
        productAdapter = ProductAdapter(productList)
        products.adapter = productAdapter
        products.layoutManager = LinearLayoutManager(this)

        mainViewModel.productsLiveData.observe(this, Observer {
            productList.addAll(it)
            productAdapter.notifyDataSetChanged()
        })

        mainViewModel.requestInProgress.observe(this, Observer {
            when(it){
                true -> loadingProgressBar.visibility = View.VISIBLE
                false -> loadingProgressBar.visibility = View.GONE
            }
        })
    }

}



















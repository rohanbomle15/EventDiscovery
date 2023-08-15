package com.robo.eventdiscovery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robo.eventdiscovery.R
import com.robo.eventdiscovery.model.Product

class ProductAdapter(private val dataSet: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>(), Filterable {

    var dataSetFiltered: List<Product> = dataSet

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView
        val tvPrice: TextView
        val tvDescription: TextView

        init {
            tvTitle = view.findViewById(R.id.title)
            tvPrice = view.findViewById(R.id.price)
            tvDescription = view.findViewById(R.id.description)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.product_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val product = dataSetFiltered[position]
        viewHolder.tvTitle.text = product.title
        viewHolder.tvPrice.text = product.price.toString()
        viewHolder.tvDescription.text = product.description
    }

    override fun getItemCount() = dataSetFiltered.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString()?.lowercase() ?: ""
                if (charString.isEmpty()) dataSetFiltered = dataSet else {
                    val filteredList = ArrayList<Product>()
                    dataSet
                        .filter {
                            (it.title.lowercase().contains(constraint!!)) or
                                    (it.description.lowercase().contains(constraint))

                        }
                        .forEach { filteredList.add(it) }
                    dataSetFiltered = filteredList

                }
                return FilterResults().apply { values = dataSetFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                dataSetFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<Product>
                notifyDataSetChanged()
            }
        }
    }

}

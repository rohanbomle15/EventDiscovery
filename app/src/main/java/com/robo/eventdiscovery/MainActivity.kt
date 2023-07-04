package com.robo.eventdiscovery

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.robo.eventdiscovery.adapter.EventAdapter
import com.robo.eventdiscovery.database.EventDatabase
import com.robo.eventdiscovery.databinding.ActivityMainBinding
import com.robo.eventdiscovery.model.Event
import com.robo.eventdiscovery.model.Status
import com.robo.eventdiscovery.viewmodel.MainViewModel
import com.robo.eventdiscovery.viewmodel.MainViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: EventAdapter
    private var eventList = mutableListOf<Event>()

    private fun requireBinding() = requireNotNull(binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewModel()
        setupView()
        setObservers()
    }

    private fun setupViewModel() {
        application.let {
            val database = EventDatabase.getDatabaseInstance(it)
            viewModel = ViewModelProvider(this, MainViewModelFactory(database))[MainViewModel::class.java]
        }
    }

    private fun setupView() {
        val binding = requireBinding()
        binding.rvEvents.layoutManager = GridLayoutManager(this, 1)
        adapter = EventAdapter(application.applicationContext)
        adapter.submitList(eventList)
        binding.rvEvents.addItemDecoration(
            DividerItemDecoration(
                binding.rvEvents.context,
                (binding.rvEvents.layoutManager as GridLayoutManager).orientation
            )
        )
        binding.rvEvents.adapter = adapter

        binding.etSearchEvent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    //viewModel.searchEvent(searchText = it.toString())
                    adapter.filter.filter(it.toString())
                }
            }
        })
    }

    private fun setObservers() {
        val binding = requireBinding()
        viewModel.getEventDetails().observe(this, Observer {
            it?.let { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        binding.rvEvents.visibility = View.VISIBLE
                        binding.pbEvents.visibility = View.GONE

                    }
                    Status.LOADING -> {
                        binding.pbEvents.visibility = View.VISIBLE
                        binding.rvEvents.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        binding.rvEvents.visibility = View.VISIBLE
                        binding.pbEvents.visibility = View.GONE
                    }
                }
            }
        })

        viewModel.filteredEventsLiveData.observe(this) { result ->
            result?.let {
                updateListView(it)
            }
        }
    }

    private fun updateListView(list: List<Event>) {
        eventList.clear()
        eventList.addAll(list)
        adapter.submitList(eventList)
    }
}
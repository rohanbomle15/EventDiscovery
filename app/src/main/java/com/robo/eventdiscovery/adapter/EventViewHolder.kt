package com.robo.eventdiscovery.adapter

import androidx.recyclerview.widget.RecyclerView
import com.robo.eventdiscovery.databinding.EventItemBinding
import com.robo.eventdiscovery.model.Event

class EventViewHolder(
private val binding: EventItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(eventDetails : Event) {
        binding.event = eventDetails
    }
}
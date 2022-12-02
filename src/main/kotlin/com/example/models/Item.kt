package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Item(val id: String, val description: String, val parId: String, val sortField: String, val typeId: Int) {
    val parent: Item?
        get() = ItemCollection.find { it.id == parId }

    var child: List<Item>? = mutableListOf()
        get() = ItemCollection.filter { it.parId == this.id }
}

var ItemCollection = mutableListOf<Item>()

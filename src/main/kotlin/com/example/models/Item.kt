package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Item(val id: String, val description: String, val parId: String, val sortField: String, val typeId: Int)

var ItemCollection = mutableListOf<Item>()

package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class FuncLoc(val id: String?,
                   val description: String?,
                   val parId: String?,
                   val sortField: String?,
                   val typeId: Int?)

class FuncLocItem(val funcLoc: FuncLoc,
                  val parent: FuncLoc?,
                  var children: MutableList<FuncLoc>? = mutableListOf())
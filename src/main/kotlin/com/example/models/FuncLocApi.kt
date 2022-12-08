package com.example.models

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/v1")
class FuncLocApi {
    @Serializable
    @Resource("floc")
    class FuncLoc(val parent: FuncLocApi = FuncLocApi()) {
        @Serializable
        @Resource("branch")
        class Branch(val parent: FuncLoc = FuncLoc(), val id: String)

        @Serializable
        @Resource("childs")
        class Childs(val parent: FuncLoc = FuncLoc(), val id: String)

        @Serializable
        @Resource("changes")
        class Changes(val parent: FuncLoc = FuncLoc(), val from: String)
    }
}
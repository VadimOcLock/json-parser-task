package com.example.controllers

import com.example.models.FuncLoc
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*

class FuncLocController(private val funcLocClient: FuncLocClient = FuncLocClient()) {
    suspend fun callBranch(call: ApplicationCall) {
        call.respond(
            FreeMarkerContent("index.ftl", null)
        )
    }
    suspend fun getParentBranches(call: ApplicationCall) {
        val idStr = call.parameters["id"]
        val tempId = "D4"

        var parentId = if(idStr.isNullOrEmpty() || idStr == "#") tempId
        else idStr

        val branches = funcLocClient.getFuncLocBranch(parentId)
        val parentBranches = branches.filter { it.parId.equals(parentId, true) }
        var fileContent = generate(parentBranches)

        call.respondText(fileContent, ContentType.Application.Json)
    }

    private fun generate(funcLoc: List<FuncLoc>): String {
        var t = funcLoc.map { convertToJsTreeFormat(it) }.toList()
        var tt = t.joinToString(",")
        var sb = StringBuilder()
        sb.append("[").append(tt).append("]")

        return sb.toString()
    }

    private fun convertToJsTreeFormat(funcLoc: FuncLoc): String {
        var sb = StringBuilder()
        var parent = funcLoc.parId
        if (parent.isNullOrEmpty() || parent == "D4") parent = "#"
        var hasChildren = !funcLoc.parId.isNullOrEmpty()
        sb.append("{")
        sb.append("\"id\":\"${funcLoc.id}\", \"parent\":\"${parent}\", \"text\":\"${funcLoc.description}\"");
        if (hasChildren)
            sb.append(",\"children\":true");
        if (!hasChildren)
            sb.append(",\"icon\":\"jstree-file\"");
        sb.append("}");

        return sb.toString()
    }
}
package me.challenge.webmvc.api.member.dto

import com.fasterxml.jackson.annotation.JsonInclude
import me.challenge.webmvc.common.ResultCode

@JsonInclude(JsonInclude.Include.NON_NULL)
data class TaskDto(
    var resultCode: ResultCode,
    var taskId: String? = null,
    var totalCount: Int? = null,
    var progressCount: Int? = null
)


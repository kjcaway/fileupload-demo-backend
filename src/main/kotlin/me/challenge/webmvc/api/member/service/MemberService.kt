package me.challenge.webmvc.api.member.service

import me.challenge.webmvc.api.member.dto.TaskDto
import org.springframework.web.multipart.MultipartFile

interface MemberService {
    fun uploadMembersFromFile(file: MultipartFile): TaskDto
    fun getTaskProgress(taskId: String): TaskDto
}
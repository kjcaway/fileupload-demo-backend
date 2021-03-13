package me.challenge.webmvc.api.member

import me.challenge.webmvc.api.member.dto.TaskDto
import me.challenge.webmvc.api.member.service.MemberService
import me.challenge.webmvc.common.ResultCode
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("\${api.base.path}/member")
class MemberController(
    private val memberService: MemberService
) {
    private val logger: Log = LogFactory.getLog(MemberController::class.java)

    /**
     * csv 파일 업로드
     *
     * @param memberFile MultipartFile
     * @return ResponseEntity<TaskDto>
     */
    @PostMapping("/upload")
    fun upload(@RequestPart("memberFile") memberFile: MultipartFile): ResponseEntity<TaskDto> {
        return try {
            val dto = memberService.uploadMembersFromFile(memberFile)
            ResponseEntity(dto, HttpStatus.OK)
        } catch (e: Exception){
            logger.error(e.localizedMessage, e)
            return ResponseEntity(TaskDto(ResultCode.FAIL), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    /**
     * 처리량 조회
     *
     * @param taskId String
     * @return ResponseEntity<TaskDto>
     */
    @GetMapping("/{taskId}")
    fun getProgressInfo(@PathVariable taskId: String): ResponseEntity<TaskDto> {
        return try {
            val dto = memberService.getTaskProgress(taskId)
            ResponseEntity(dto, HttpStatus.OK)
        } catch (e: Exception){
            logger.error(e.localizedMessage, e)
            return ResponseEntity(TaskDto(ResultCode.FAIL), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
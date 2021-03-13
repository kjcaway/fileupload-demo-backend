package me.challenge.webmvc.api.member.service

import com.opencsv.bean.CsvToBeanBuilder
import me.challenge.webmvc.api.member.dto.TaskDto
import me.challenge.webmvc.api.member.entity.Member
import me.challenge.webmvc.api.member.parser.MemberCsv
import me.challenge.webmvc.api.member.repository.MemberRepository
import me.challenge.webmvc.common.Progress
import me.challenge.webmvc.common.ResultCode
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val taskExecutor: Executor,
    private val progress: Progress
): MemberService {
    private val logger: Log = LogFactory.getLog(MemberServiceImpl::class.java)

    override fun uploadMembersFromFile(file: MultipartFile): TaskDto {
        try {
            val taskId = UUID.randomUUID().toString()
            val reader = BufferedReader(InputStreamReader(file.inputStream))

            val csvToBean = CsvToBeanBuilder<MemberCsv>(reader)
                .withType(MemberCsv::class.java)
                .withIgnoreLeadingWhiteSpace(true)
                .build()

            val memberList = csvToBean.parse()
            val entityList = mutableListOf<Member>()

            var num = 1
            memberList.forEach{
                entityList.add(Member(it, taskId, num++))
            }

            // run async
            bulkInsertMembers(entityList)

            return TaskDto(
                    resultCode = ResultCode.SUCCESS,
                    taskId = taskId,
                    totalCount = num
            )
        } catch (e: Exception){
            throw e
        }
    }

    override fun getTaskProgress(taskId: String): TaskDto {
        try {
            return TaskDto(
                    resultCode = ResultCode.SUCCESS,
                    taskId = taskId,
                    progressCount = progress.get(taskId)
            )
        } catch (e: Exception){
            throw e
        }
    }

    @Async
    private fun bulkInsertMembers(list: List<Member>){
        CompletableFuture.runAsync({
            memberRepository.saveAll(list)
        }, taskExecutor).thenAcceptAsync {
            logger.info("async task is completed")
        }
    }
}
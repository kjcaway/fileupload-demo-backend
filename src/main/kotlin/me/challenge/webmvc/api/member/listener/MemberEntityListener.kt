package me.challenge.webmvc.api.member.listener

import me.challenge.webmvc.api.member.entity.Member
import me.challenge.webmvc.common.Progress
import javax.persistence.PostPersist

class MemberEntityListener(
    private val progress: Progress
) {
    private val PROGRESS_SIZE = 100

    @PostPersist
    fun postPersist(member: Member){
        if(member.num%PROGRESS_SIZE == 0){
            // 처리량 갱신
            progress.set(member.taskId, member.num)
        }
    }
}
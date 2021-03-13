package me.challenge.webmvc.api.member.repository

import me.challenge.webmvc.api.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface MemberRepository: JpaRepository<Member, Long> {
}
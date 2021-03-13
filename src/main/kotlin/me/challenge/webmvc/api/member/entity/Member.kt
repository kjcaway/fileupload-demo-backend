package me.challenge.webmvc.api.member.entity

import me.challenge.webmvc.api.member.listener.MemberEntityListener
import me.challenge.webmvc.api.member.parser.MemberCsv
import org.springframework.data.domain.Persistable
import javax.persistence.*

@Entity
@Table(name = "tbl_member")
@EntityListeners(value = [MemberEntityListener::class])
class Member (
    @Id
    var id: Long = 0,
    var firstname: String? = null,
    var lastname: String? = null,
    var email: String? = null,

    @Transient
    var taskId: String = "",
    @Transient
    var num: Int = 0
): Persistable<Long> {
    constructor(memberCsv: MemberCsv, taskId: String, num: Int): this(){
        this.id = memberCsv.id
        this.firstname = memberCsv.firstname
        this.lastname = memberCsv.lastname
        this.email = memberCsv.email
        this.taskId = taskId
        this.num = num
    }

    override fun getId(): Long {
        return id
    }

    override fun isNew(): Boolean {
        return true
    }
}
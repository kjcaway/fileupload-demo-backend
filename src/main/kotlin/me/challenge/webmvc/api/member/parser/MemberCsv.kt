package me.challenge.webmvc.api.member.parser

import com.opencsv.bean.CsvBindByName

data class MemberCsv(
    @CsvBindByName
    var id: Long = 0,
    @CsvBindByName
    var firstname: String? = null,
    @CsvBindByName
    var lastname: String? = null,
    @CsvBindByName
    var email: String? = null,
)

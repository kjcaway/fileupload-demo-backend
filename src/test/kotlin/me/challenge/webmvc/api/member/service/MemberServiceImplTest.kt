package me.challenge.webmvc.api.member.service

import com.opencsv.bean.CsvToBeanBuilder
import me.challenge.webmvc.api.member.entity.Member
import me.challenge.webmvc.api.member.parser.MemberCsv
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import java.io.BufferedReader
import java.io.InputStreamReader

@SpringBootTest
internal class MemberServiceImplTest{
    @Test
    fun `multipartfile read and parse test`(){
        var data = "id,firstname,lastname,email"
        data += "\n100,Nanete,Yusuk,Nanete.Yusuk@yopmail.com"
        val file = MockMultipartFile(
                "memberFile",
                "test.csv",
                MediaType.TEXT_PLAIN_VALUE,
                data.toByteArray()
        )

        val reader = BufferedReader(InputStreamReader(file.inputStream))

        val csvToBean = CsvToBeanBuilder<MemberCsv>(reader)
                .withType(MemberCsv::class.java)
                .withIgnoreLeadingWhiteSpace(true)
                .build()

        val memberList = csvToBean.parse()

        assertEquals(memberList.size, 1)
        assertEquals(memberList[0].id, 100)
        assertEquals(memberList[0].firstname, "Nanete")
        assertEquals(memberList[0].lastname, "Yusuk")
        assertEquals(memberList[0].email, "Nanete.Yusuk@yopmail.com")
    }

    @Test
    fun `create entity list test`(){
        val list = listOf(MemberCsv(100, "Nanete", "Yusuk", "Nanete.Yusuk@yopmail.com"))

        val entityList = mutableListOf<Member>()

        list.forEachIndexed{ idx, it ->
            entityList.add(Member(it, "test", (idx+1)+1))
        }

        assertEquals(list.size, entityList.size)
        assertEquals(list[0].id, entityList[0].id)
    }
}

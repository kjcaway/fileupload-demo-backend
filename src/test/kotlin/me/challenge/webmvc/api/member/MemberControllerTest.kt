package me.challenge.webmvc.api.member

import me.challenge.webmvc.api.member.repository.MemberRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
internal class MemberControllerTest{
    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext
    @MockBean
    private lateinit var memberRepository: MemberRepository
    private lateinit var mockMvc: MockMvc

    @Test
    fun `upload api test`(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()

        var data = "id,firstname,lastname,email"
        data += "\n100,Nanete,Yusuk,Nanete.Yusuk@yopmail.com"

        val file = MockMultipartFile(
                "memberFile",
                "test.csv",
                MediaType.TEXT_PLAIN_VALUE,
                data.toByteArray()
        )

        mockMvc.perform(multipart("/api/member/upload").file(file))
                .andExpect(status().isOk);
    }

    @Test
    fun `task check api test`(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()

        mockMvc.perform(get("/api/member/taskid"))
                .andExpect(status().isOk)
    }
}
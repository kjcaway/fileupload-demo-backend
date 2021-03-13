package me.challenge.webmvc.common

import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

/**
 * 실시간 DB 입력 처리량 확인을 위한 컴포넌트
 */
@Component
class Progress {
    private lateinit var map: HashMap<String, Int>

    @PostConstruct
    fun postConstruct(){
        map = hashMapOf()
    }

    fun set(taskId: String, num: Int){
        map[taskId] = num
    }

    fun get(taskId: String): Int?{
        return map[taskId]
    }
}
package me.challenge.webmvc.aop

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch


@Aspect
@Component
class CheckTimeAop {
    private val logger: Log = LogFactory.getLog(CheckTimeAop::class.java)

    @Around("execution(* org.springframework.data.jpa.repository.JpaRepository+.saveAll(..))))")
    fun timeLogger(proceedingJoinPoint: ProceedingJoinPoint): Object{
        val stopWatch = StopWatch()
        stopWatch.start("runMethod")
        val result = proceedingJoinPoint.proceed() as Object
        stopWatch.stop()
        logger.info("=== saveAll() method running time(s) : " + stopWatch.totalTimeSeconds)

        return result
    }
}
package com.alekseysamoylov.productservice.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.javasimon.SimonManager
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*


@Aspect
@Component
class ProfileAspect {
    private val LOGGER = LoggerFactory.getLogger(ProfileAspect::class.java)

    private val STOPWATCH = SimonManager.getStopwatch(ProfileAspect::class.java.getName())

    @Pointcut("within(com.alekseysamoylov.productservice.controllers..*) && @annotation(org.javasimon.aop.Monitored)")
    fun profilingPointcut() {
    }

    @Around("profilingPointcut()")
    @Throws(Throwable::class)
    fun profileAround(joinPoint: ProceedingJoinPoint): Any? {
        LOGGER.info("Profiling: Enter: {}.{}() with argument[s] = {}", joinPoint.signature.declaringTypeName,
                joinPoint.signature.name, Arrays.toString(joinPoint.args))
        try {
            val split = STOPWATCH.start()
            val result = joinPoint.proceed()
            split.stop()
            LOGGER.info("Profiling: Exit: {}.{}() with result = {}", joinPoint.signature.declaringTypeName,
                    joinPoint.signature.name, result)
            LOGGER.info("Profiling: Time: {}", STOPWATCH)
            return result
        } catch (e: IllegalArgumentException) {
            LOGGER.error("Profiling: Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.args),
                    joinPoint.signature.declaringTypeName, joinPoint.signature.name)
            throw e
        }
    }
}

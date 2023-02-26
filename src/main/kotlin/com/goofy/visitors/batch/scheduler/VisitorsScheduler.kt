package com.goofy.visitors.batch.scheduler

import com.goofy.visitors.batch.job.VisitorsJob
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class VisitorsScheduler(
    private val visitorsJob: VisitorsJob
) {
    @Scheduled(cron = "0 0 0 * * *")
    fun runVisitorsJob() = visitorsJob.runDaily()
}

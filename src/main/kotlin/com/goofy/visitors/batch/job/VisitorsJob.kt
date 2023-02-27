package com.goofy.visitors.batch.job

import com.goofy.visitors.application.RedisCacheService
import com.goofy.visitors.domain.VisitorsSnapshot
import com.goofy.visitors.infrastructure.VisitorsSnapshotRepository
import org.springframework.stereotype.Component

@Component
class VisitorsJob(
    private val visitorsSnapshotRepository: VisitorsSnapshotRepository,
    private val redisCacheService: RedisCacheService
) {
    fun runDaily() = visitorsSnapshotRepository.saveAll(
        redisCacheService.getDailyVisitors()
            .map { visitor ->
                VisitorsSnapshot.of(visitor.target, visitor.count)
            }
    )
}

package com.goofy.visitors.infrastructure

import com.goofy.visitors.domain.VisitorsSnapshot
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface VisitorsSnapshotRepository : R2dbcRepository<VisitorsSnapshot, Long> {

}

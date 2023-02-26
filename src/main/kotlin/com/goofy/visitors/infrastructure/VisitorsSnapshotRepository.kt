package com.goofy.visitors.infrastructure

import com.goofy.visitors.domain.VisitorsSnapshot
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface VisitorsSnapshotRepository : ReactiveMongoRepository<VisitorsSnapshot, String>

package com.goofy.visitors.infrastructure

import com.goofy.visitors.domain.Visitors
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface VisitorsRepository : R2dbcRepository<Visitors, String>

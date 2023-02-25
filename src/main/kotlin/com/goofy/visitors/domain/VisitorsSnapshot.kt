package com.goofy.visitors.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("visitors_snapshot")
class VisitorsSnapshot(
    @Id
    val id: Long = -1,

    val target: String,

    val count: Long,

    @CreatedDate
    @Column("created_at")
    val createdAt: LocalDateTime,

    @LastModifiedDate
    @Column("modified_at")
    val modifiedAt: LocalDateTime
)

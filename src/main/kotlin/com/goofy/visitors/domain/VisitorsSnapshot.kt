package com.goofy.visitors.domain

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.ZonedDateTime

@Document(collation = "Visitors_snapshot")
class VisitorsSnapshot(
    @Id
    val target: String,

    val count: Long,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "Asia/Seoul")
    var createdAt: ZonedDateTime = ZonedDateTime.now()
) {
    companion object {
        fun of(target: String, count: String): VisitorsSnapshot {
            return VisitorsSnapshot(
                target = target,
                count = count.toLong()
            )
        }
    }
}

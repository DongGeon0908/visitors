package com.goofy.visitors.dto

data class VisitorsResponse(
    val dailyCount: String,
    val totalCount: String
) {
    companion object {
        fun of(dailyCount: String, totalCount: String) = VisitorsResponse(dailyCount, totalCount)
    }
}

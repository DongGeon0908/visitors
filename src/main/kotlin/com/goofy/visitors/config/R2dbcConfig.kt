package com.goofy.visitors.config

import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@EnableR2dbcAuditing
@EnableR2dbcRepositories
class R2dbcConfig {
}
package org.dev.otte.movie.query.dao.support

import org.dev.otte.movie.command.domain.ClovaStudioEngineSetting
import org.springframework.data.jpa.repository.JpaRepository

fun ClovaStudioEngineSettingDaoSupport.get(): ClovaStudioEngineSetting = findAll().last()

interface ClovaStudioEngineSettingDaoSupport : JpaRepository<ClovaStudioEngineSetting, Long>
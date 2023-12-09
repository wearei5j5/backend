package org.dev.otte.movie.query.dao

import org.dev.otte.movie.query.dao.support.ClovaStudioEngineSettingDaoSupport
import org.dev.otte.movie.query.dao.support.get
import org.dev.otte.movie.query.dto.ClovaStudioEngineSettingResponse
import org.springframework.stereotype.Component

@Component
class ClovaStudioEngineSettingDao(
    private val clovaStudioEngineSettingDaoSupport: ClovaStudioEngineSettingDaoSupport
) {
    fun findClovaStudioEngineSetting(): ClovaStudioEngineSettingResponse {
        return ClovaStudioEngineSettingResponse(clovaStudioEngineSettingDaoSupport.get())
    }
}
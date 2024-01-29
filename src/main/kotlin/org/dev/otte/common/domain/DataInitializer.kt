package org.dev.otte.common.domain

import org.dev.otte.movie.command.domain.ClovaStudioEngineSetting
import org.dev.otte.movie.command.domain.ClovaStudioEngineSettingRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Profile("!product")
@Component
class DataInitializer(
    private val clovaStudioEngineSettingRepository: ClovaStudioEngineSettingRepository,
    private val staticConfigRepository: StaticConfigRepository
) : CommandLineRunner {
    override fun run(vararg args: String) {
        if (clovaStudioEngineSettingRepository.findAll().isNotEmpty() &&
            staticConfigRepository.findAll().isNotEmpty()
        ) {
            return
        }
        clovaStudioEngineSettingRepository.deleteAllInBatch()
        staticConfigRepository.deleteAllInBatch()
        populateClovaStudioEngineSetting()
        populateStaticConfig()
    }

    private fun populateClovaStudioEngineSetting() {
        clovaStudioEngineSettingRepository.save(
            ClovaStudioEngineSetting(
                text = getPrompt(),
                start = "영화:",
                restart = "",
                includeTokens = false,
                topP = 0.8,
                topK = 0,
                maxTokens = 30,
                temperature = 1.0,
                repeatPenalty = 5.0,
                stopBefore = listOf("###"),
                includeAiFilters = true
            )
        )
    }

    private fun populateStaticConfig() {
        staticConfigRepository.save(StaticConfig("http://localhost:3000"))
    }

    private fun getPrompt()=
        "OTT목록, 기분, 상황, 장르를 입력하면 기분과 상황 장르가 모두 적합한 영화를 OTT목록에서 찾은 후 영화, 키워드를 추천해드립니다.\n" +
                "\n" +
                "\n" +
                "OTT목록: netflix,disneyplus,watcha\n" +
                "기분: 꿀꿀함\n" +
                "상황: 상사한테 혼났어\n" +
                "장르: 로맨스를 보고싶어\n" +
                "영화: 내가 사랑했던 모든 남자들에게\n" +
                "키워드: 달콤함, 로맨스, 성장\n" +
                "###\n" +
                "OTT목록: netflix\n" +
                "기분: 따분함\n" +
                "상황: 회사일로 지쳐있음\n" +
                "장르: 액션\n" +
                "영화: 존윅3\n" +
                "키워드: 통쾌함, 시원함, 스트레스 해소\n" +
                "###\n" +
                "OTT목록: netflix,watcha\n" +
                "기분: 지루함\n" +
                "상황: 주말에 한가해서 따분함\n" +
                "장르: 로맨스\n" +
                "영화: 이터널 선샤인\n" +
                "키워드: 시사점, 로맨스, 독특한 서사\n" +
                "###\n" +
                "OTT목록: netflix,coupangplay\n" +
                "기분: 심심함\n" +
                "상황: 공포영화로 스릴을 즐기고 싶어\n" +
                "장르: 공포\n" +
                "영화: 침묵\n" +
                "키워드: 심리적 공포, 미스터리, 드라마\n" +
                "###\n"
}
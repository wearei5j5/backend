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

    private fun getPrompt() =
        "OTT목록, 기분, 상황을 입력하면 기분과 상황에 적합한 영화를 OTT목록에서 찾은 후 영화, 키워드를 추천해드립니다.\n\nOTT목록: netflix,disneyplus,watcha\n기분: 꿀꿀함\n상황: 상사한테 혼났어\n영화: 내가 사랑했던 모든 남자들에게\n키워드: 달콤함, 로맨스, 성장\n###\nOTT목록: netflix,disneyplus,watcha\n기분: 꿀꿀함\n상황: 상사한테 혼났어\n영화: 프린세스 다이어리\n키워드: 훈훈함, 코미디, 기분 좋은\n###\nOTT목록: netflix,watcha\n기분: 지루함\n상황: 주말에 한가해서 따분함\n영화: 이터널 선샤인\n키워드: 시사점, 로맨스, 독특한 서사\n###\nOTT목록: netflix,coupangplay\n기분: 심심함\n상황: 공포영화로 스릴을 즐기고 싶어\n영화: 침묵\n키워드: 심리적 공포, 미스터리, 드라마\n###\n"
}
package org.dev.otte.common.domain

import org.dev.otte.movie.command.domain.ClovaStudioEngineSetting
import org.dev.otte.movie.command.domain.ClovaStudioEngineSettingRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Profile("!product")
@Component
class DataInitializer(
    private val clovaStudioEngineSettingRepository: ClovaStudioEngineSettingRepository
) : CommandLineRunner {
    override fun run(vararg args: String) {
        clovaStudioEngineSettingRepository.save(
            ClovaStudioEngineSetting(
                text = getPrompt(),
                start = "키워드:",
                restart = "###",
                includeTokens = false,
                topP = 0.8,
                topK = 0,
                maxTokens = 30,
                temperature = 0.5,
                repeatPenalty = 5.0,
                stopBefore = listOf("###"),
                includeAiFilters = true
            )
        )
    }

    private fun getPrompt() =
        "OTT플랫폼과 기분과 상황을 입력하면, 적합한 키워드와 함께 해당 OTT에서 제공하는 영화를 추천해드립니다.\n\nOTT: 넷플릭스\n기분: 꿀꿀함\n상황: 상사한테 혼남\n키워드: 드라이브, 신남, 추리물\n영화: 퀸카로 살아남기\n###\nOTT: 티빙\n기분: 신남\n상황: 로또에 당첨됨\n키워드: 신남, 흥분됨, 로또\n영화: 롤러코스터\n###\nOTT: 쿠팡플레이\n기분: 무던함\n상황: 심심한 상황\n키워드: 차분한, 스릴있는\n영화: 곡성\n###\nOTT: \b넷플릭스\n기분: 행복함\n상황: \b여자친구와 1일인 상황\n키워드: 로맨틱, 신남, 데이트 \n영화: 500일의 썸머\n###\nOTT: \b티빙\n기분: 뿌듯함\n상황: \b 길을 잃어버린 아이를 도와준 상황\n키워드: 따뜻함, 도움, 멘토, 인간\n영화: 굿 윌 헌팅\n###\nOTT: 디즈니플러스\n기분: 좋음\n상황: 친구들과 함께 여행 후 숙소에 온 상황\n키워드: 신남, 다이나믹, 재밌는, 블록버스터, 코미디\n영화: 어벤져스 인피니티 워\n###\nOTT: \b디즈니플러스\n기분: 우울함\n상황: \b꿈이 없음\n키워드: 좌절, 우울, 슬픔\n영화: 죽은 시인의 사회\n###\nOTT: 넷플릭스\n기분: \b지금 엄청 스트레스받어\n상황: 회사에서 상사한테 좀 구박을 받았어. 그래서 분노가 차올라\n키워드: 화남, 복수, 액션, 스릴러\n영화: 매드맥스 분노의 도로\n###\n"
}
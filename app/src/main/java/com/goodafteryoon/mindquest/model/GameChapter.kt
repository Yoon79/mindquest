package com.goodafteryoon.mindquest.model

data class GameChoice(
    val text: String,
    val statChanges: Map<String, Int>
)

data class GameChapter(
    val id: Int,
    val title: String,
    val background: String,
    val npcName: String,
    val question: String,
    val choices: List<GameChoice>,
    val isFinalBoss: Boolean = false
)

object GameData {
    val chapters = listOf(
        GameChapter(
            id = 1,
            title = "기억의 방",
            background = "memory_room",
            npcName = "기억의 정령",
            question = "어릴 적 네가 가장 좋아하던 건 뭐였지?",
            choices = listOf(
                GameChoice(
                    text = "게임기 앞에서 밤새던 기억",
                    statChanges = mapOf("creativity" to 2, "concentration" to 1)
                ),
                GameChoice(
                    text = "친구들과 뛰어놀던 운동장",
                    statChanges = mapOf("sociability" to 2, "courage" to 1)
                ),
                GameChoice(
                    text = "조용히 책을 읽던 순간",
                    statChanges = mapOf("concentration" to 2, "curiosity" to 1)
                )
            )
        ),
        GameChapter(
            id = 2,
            title = "거울의 복도",
            background = "mirror_corridor",
            npcName = "거울 속 또 다른 나",
            question = "사람들이 널 어떻게 본다고 생각하지?",
            choices = listOf(
                GameChoice(
                    text = "밝고 재밌는 사람",
                    statChanges = mapOf("sociability" to 2, "courage" to 1)
                ),
                GameChoice(
                    text = "차분하고 신중한 사람",
                    statChanges = mapOf("concentration" to 2, "anxiety" to -1)
                ),
                GameChoice(
                    text = "조금 이상하지만 특별한 사람",
                    statChanges = mapOf("creativity" to 2, "anxiety" to 1)
                )
            )
        ),
        GameChapter(
            id = 3,
            title = "심연의 호수",
            background = "abyss_lake",
            npcName = "불안의 그림자",
            question = "너는 무엇을 가장 두려워하니?",
            choices = listOf(
                GameChoice(
                    text = "혼자가 되는 것",
                    statChanges = mapOf("anxiety" to 2, "relationship" to 1)
                ),
                GameChoice(
                    text = "실패하는 것",
                    statChanges = mapOf("anxiety" to 2, "courage" to -1)
                ),
                GameChoice(
                    text = "진짜 나를 들키는 것",
                    statChanges = mapOf("anxiety" to 1, "courage" to 1)
                )
            )
        ),
        GameChapter(
            id = 4,
            title = "불꽃의 탑",
            background = "fire_tower",
            npcName = "불꽃의 정령",
            question = "지금 네 안에서 가장 뜨겁게 불타는 건 뭐지?",
            choices = listOf(
                GameChoice(
                    text = "성공과 인정",
                    statChanges = mapOf("passion" to 2, "courage" to 1)
                ),
                GameChoice(
                    text = "새로운 지식와 탐험",
                    statChanges = mapOf("curiosity" to 2, "creativity" to 1)
                ),
                GameChoice(
                    text = "누군가와의 진한 연결",
                    statChanges = mapOf("relationship" to 2, "sociability" to 1)
                )
            )
        ),
        GameChapter(
            id = 5,
            title = "별의 정원",
            background = "star_garden",
            npcName = "별을 심는 아이",
            question = "죽기 전에 꼭 이루고 싶은 건?",
            choices = listOf(
                GameChoice(
                    text = "세상에 나만의 발자취 남기기",
                    statChanges = mapOf("passion" to 2, "creativity" to 1)
                ),
                GameChoice(
                    text = "사랑하는 사람과 평온한 시간",
                    statChanges = mapOf("relationship" to 2, "concentration" to 1)
                ),
                GameChoice(
                    text = "새로운 세계를 끝없이 경험하기",
                    statChanges = mapOf("curiosity" to 2, "courage" to 1)
                )
            )
        ),
        GameChapter(
            id = 6,
            title = "심연의 나",
            background = "shadow_self",
            npcName = "심연의 나",
            question = "넌 결국 인정받고 싶었던 거잖아.",
            choices = listOf(
                GameChoice(
                    text = "그렇지 않아",
                    statChanges = mapOf("courage" to 2, "anxiety" to -1)
                ),
                GameChoice(
                    text = "맞아...",
                    statChanges = mapOf("anxiety" to 1, "courage" to -1)
                ),
                GameChoice(
                    text = "모르겠어",
                    statChanges = mapOf("curiosity" to 1, "anxiety" to 0)
                )
            ),
            isFinalBoss = true
        )
    )
}

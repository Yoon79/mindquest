package com.goodafteryoon.mindquest

import com.goodafteryoon.mindquest.model.GameChapter
import com.goodafteryoon.mindquest.model.GameData
import com.goodafteryoon.mindquest.model.PlayerStats

class GameManager {
    private var currentChapterIndex = -1 // -1은 오프닝 상태
    private var playerStats = PlayerStats()
    private var gameCompleted = false
    
    fun startGame() {
        currentChapterIndex = 0
        playerStats = PlayerStats()
        gameCompleted = false
    }
    
    fun getCurrentChapter(): GameChapter? {
        return if (currentChapterIndex >= 0 && currentChapterIndex < GameData.chapters.size) {
            GameData.chapters[currentChapterIndex]
        } else null
    }
    
    fun makeChoice(choiceIndex: Int) {
        val currentChapter = getCurrentChapter() ?: return
        
        if (choiceIndex >= 0 && choiceIndex < currentChapter.choices.size) {
            val choice = currentChapter.choices[choiceIndex]
            
            // 스탯 변경 적용
            choice.statChanges.forEach { (statName, change) ->
                when (statName) {
                    "creativity" -> playerStats.creativity += change
                    "sociability" -> playerStats.sociability += change
                    "concentration" -> playerStats.concentration += change
                    "courage" -> playerStats.courage += change
                    "anxiety" -> playerStats.anxiety += change
                    "passion" -> playerStats.passion += change
                    "curiosity" -> playerStats.curiosity += change
                    "relationship" -> playerStats.relationship += change
                }
            }
            
            // 다음 챕터로 진행
            currentChapterIndex++
            
            // 게임 완료 체크
            if (currentChapterIndex >= GameData.chapters.size) {
                gameCompleted = true
            }
        }
    }
    
    fun getPlayerStats(): PlayerStats = playerStats
    
    fun isGameCompleted(): Boolean = gameCompleted
    
    fun isInOpening(): Boolean = currentChapterIndex == -1
    
    fun restartGame() {
        startGame()
    }
    
    fun getProgress(): Float {
        return if (currentChapterIndex < 0) 0f else {
            (currentChapterIndex.toFloat() / GameData.chapters.size) * 100f
        }
    }
}

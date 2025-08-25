package com.goodafteryoon.mindquest.model

data class PlayerStats(
    var creativity: Int = 0,
    var sociability: Int = 0,
    var concentration: Int = 0,
    var courage: Int = 0,
    var anxiety: Int = 0,
    var passion: Int = 0,
    var curiosity: Int = 0,
    var relationship: Int = 0
) {
    fun getEndingType(): String {
        val maxStat = maxOf(creativity, sociability, concentration, courage, passion, curiosity, relationship)
        
        return when {
            courage > anxiety && courage >= maxStat * 0.8 -> "brave"
            concentration > sociability && concentration >= maxStat * 0.8 -> "contemplative"
            relationship > passion && relationship >= maxStat * 0.8 -> "free"
            else -> "balanced"
        }
    }
    
    fun getEndingTitle(): String {
        return when (getEndingType()) {
            "brave" -> "용감한 나"
            "contemplative" -> "사색하는 나"
            "free" -> "자유로운 나"
            else -> "균형잡힌 나"
        }
    }
}

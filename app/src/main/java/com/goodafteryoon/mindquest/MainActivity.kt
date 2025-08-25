package com.goodafteryoon.mindquest

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.goodafteryoon.mindquest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var gameManager: GameManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        gameManager = GameManager()
        
        // 전체 화면 설정
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        
        setupUI()
        setupCustomFont()
        showOpening()
    }
    
    private fun setupUI() {
        // 버튼 클릭 리스너 설정
        binding.btnChoice1.setOnClickListener { makeChoice(0) }
        binding.btnChoice2.setOnClickListener { makeChoice(1) }
        binding.btnChoice3.setOnClickListener { makeChoice(2) }
        binding.btnStartGame.setOnClickListener { startGame() }
        binding.btnRestart.setOnClickListener { restartGame() }
        binding.btnExit.setOnClickListener { finish() }
    }
    
    private fun setupCustomFont() {
        try {
            val customFont = Typeface.createFromAsset(assets, "fonts/dunggeun.ttf")
            
            // 모든 텍스트뷰에 커스텀 폰트 적용
            binding.tvOpeningNarration.typeface = customFont
            binding.tvChapterTitle.typeface = customFont
            binding.tvNpcName.typeface = customFont
            binding.tvQuestion.typeface = customFont
            binding.tvEndingTitle.typeface = customFont
            binding.tvEndingMessage.typeface = customFont
            
            // 모든 버튼에 커스텀 폰트 적용
            binding.btnStartGame.typeface = customFont
            binding.btnChoice1.typeface = customFont
            binding.btnChoice2.typeface = customFont
            binding.btnChoice3.typeface = customFont
            binding.btnRestart.typeface = customFont
            binding.btnExit.typeface = customFont
        } catch (e: Exception) {
            // 폰트 로드 실패시 기본 폰트 사용
            e.printStackTrace()
        }
    }
    
    private fun showOpening() {
        binding.layoutOpening.visibility = View.VISIBLE
        binding.layoutGame.visibility = View.GONE
        binding.layoutEnding.visibility = View.GONE
        
        // 오프닝 텍스트 애니메이션
        val fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        binding.tvOpeningNarration.startAnimation(fadeIn)
    }
    
    private fun startGame() {
        gameManager.startGame()
        showGameScreen()
    }
    
    private fun showGameScreen() {
        binding.layoutOpening.visibility = View.GONE
        binding.layoutGame.visibility = View.VISIBLE
        binding.layoutEnding.visibility = View.GONE
        
        updateGameUI()
    }
    
    private fun updateGameUI() {
        val currentChapter = gameManager.getCurrentChapter()
        
        if (currentChapter != null) {
            // 챕터 제목 설정
            binding.tvChapterTitle.text = currentChapter.title
            
            // NPC 이름 설정
            binding.tvNpcName.text = currentChapter.npcName
            
            // 질문 설정
            binding.tvQuestion.text = currentChapter.question
            
            // 선택지 설정
            binding.btnChoice1.text = currentChapter.choices[0].text
            binding.btnChoice2.text = currentChapter.choices[1].text
            binding.btnChoice3.text = currentChapter.choices[2].text
            
            // 배경 색상 설정 (챕터별로 다른 색상)
            setBackgroundColor(currentChapter.background)
            
            // 텍스트 박스 애니메이션
            val slideUp = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
            binding.textBox.startAnimation(slideUp)
        }
    }
    
    private fun setBackgroundColor(background: String) {
        val color = when (background) {
            "memory_room" -> ContextCompat.getColor(this, R.color.crt_black)
            "mirror_corridor" -> ContextCompat.getColor(this, R.color.crt_black)
            "abyss_lake" -> ContextCompat.getColor(this, R.color.crt_black)
            "fire_tower" -> ContextCompat.getColor(this, R.color.crt_black)
            "star_garden" -> ContextCompat.getColor(this, R.color.crt_black)
            "shadow_self" -> ContextCompat.getColor(this, R.color.crt_black)
            else -> ContextCompat.getColor(this, R.color.crt_black)
        }
        binding.root.setBackgroundColor(color)
    }
    
    private fun makeChoice(choiceIndex: Int) {
        gameManager.makeChoice(choiceIndex)
        
        if (gameManager.isGameCompleted()) {
            showEnding()
        } else {
            updateGameUI()
        }
    }
    
    private fun showEnding() {
        binding.layoutOpening.visibility = View.GONE
        binding.layoutGame.visibility = View.GONE
        binding.layoutEnding.visibility = View.VISIBLE
        
        val playerStats = gameManager.getPlayerStats()
        val endingTitle = playerStats.getEndingTitle()
        
        binding.tvEndingTitle.text = endingTitle
        binding.tvEndingMessage.text = getString(R.string.ending_final_message)
        
        // 엔딩 애니메이션
        val fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        binding.layoutEnding.startAnimation(fadeIn)
    }
    
    private fun restartGame() {
        gameManager.restartGame()
        showOpening()
    }
    
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            // 전체 화면 유지
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }
}

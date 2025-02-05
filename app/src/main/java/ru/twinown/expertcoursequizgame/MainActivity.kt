package ru.twinown.expertcoursequizgame

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.twinown.expertcoursequizgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //   setContentView(R.layout.activity_main)//отсюда доступ к контексту, который имеет доступ к дисплею
        //тут происходит парсинг и созданиеActivityMainBinding
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.rootLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //анонимный объект
        val viewModel:GameViewModel = GameViewModel(GameRepository.Base())

        binding.firstChoiceButton.setOnClickListener {
            val uiState :GameUiState = viewModel.chooseFirst()
            //изменение состояния экрана
            uiState.update(binding = binding)
        }
        binding.secondChoiceButton.setOnClickListener {
            val uiState :GameUiState = viewModel.chooseSecond()
            uiState.update(binding = binding)
        }
        binding.thirdChoiceButton.setOnClickListener {
            val uiState :GameUiState = viewModel.chooseThird()
            uiState.update(binding = binding)
        }
        binding.fourthChoiceButton.setOnClickListener {
            val uiState :GameUiState = viewModel.chooseFourth()
            uiState.update(binding = binding)
        }
        binding.checkButton.setOnClickListener {
            val uiState :GameUiState = viewModel.check()
            uiState.update(binding = binding)
        }
        binding.nextButton.setOnClickListener {
            val uiState :GameUiState = viewModel.chooseNext()
            uiState.update(binding = binding)
        }

        //первое открытие приложения
        val uiState:GameUiState = viewModel.init()
        uiState.update(binding = binding)


    }
}
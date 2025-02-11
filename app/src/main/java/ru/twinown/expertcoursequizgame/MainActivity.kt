package ru.twinown.expertcoursequizgame

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.twinown.expertcoursequizgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var uiState:GameUiState
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

        //анонимный объект БЫЛ!!!
       val viewModel:GameViewModel = (application as QuizApp).viewModel

        binding.firstChoiceButton.setOnClickListener {
           uiState  = viewModel.chooseFirst()
            //изменение состояния экрана
            uiState.update(binding = binding)
        }
        binding.secondChoiceButton.setOnClickListener {
           uiState = viewModel.chooseSecond()
            uiState.update(binding = binding)
        }
        binding.thirdChoiceButton.setOnClickListener {
          uiState  = viewModel.chooseThird()
            uiState.update(binding = binding)
        }
        binding.fourthChoiceButton.setOnClickListener {
            uiState  = viewModel.chooseFourth()
            uiState.update(binding = binding)
        }
        binding.checkButton.setOnClickListener {
           uiState = viewModel.check()
            uiState.update(binding = binding)
        }
        binding.nextButton.setOnClickListener {
          uiState  = viewModel.chooseNext()
            uiState.update(binding = binding)
        }

        uiState = if (savedInstanceState==null){
            viewModel.init()
        } else{
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
                savedInstanceState.getSerializable(KEY,GameUiState::class.java) as GameUiState
            } else{
                savedInstanceState.getSerializable(KEY) as GameUiState
            }
        }
        uiState.update(binding = binding)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY,uiState)
    }

    companion object{
        private const val KEY = "uiState"
    }
    //назначена ссылка и больше никтогда не меняется
    //чувак ниже создаётся в начале процесса
    //создаётся после конструктора активити (поэтому нам это не подходит все равно!!!),
    // но живёт дольше его объекта
    //ЭТО ОТДЕЛЬНЫЙ КЛАСС!!!!!!, жизнь которого зависит от ПРОЦЕССА!!!
//    object ViewModelContainer{
//        val viewModel = GameViewModel(GameRepository.Base())
//    }

}

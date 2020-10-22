package com.example.retrofitdemo.ui



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitdemo.entity.Question
import com.example.retrofitdemo.repository.QuestionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuestionViewModel(private val questionRepository: QuestionRepository) : ViewModel() {
    var questions : MutableLiveData<List<Question>> = MutableLiveData()

    fun fetchQuestion(tags: String){
        viewModelScope.launch {
            val response = questionRepository.fetchQuestions(tags)
            if(response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main) {
                    questions.value = response.body()?.items
                }
            }
        }
    }
}
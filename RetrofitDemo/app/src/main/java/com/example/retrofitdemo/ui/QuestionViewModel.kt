package com.example.retrofitdemo.ui



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitdemo.entity.Question
import com.example.retrofitdemo.repository.QuestionRepository
import kotlinx.coroutines.launch

class QuestionViewModel(private val questionRepository: QuestionRepository) : ViewModel() {
    var questions : MutableLiveData<List<Question>> = MutableLiveData()

    fun fetchQuestion(tags: String){
        viewModelScope.launch {
            val response = questionRepository.fetchQuestions(tags)
            if(response.isSuccessful && response.body() != null){
                questions.value = response.body()?.items
            }
//                .enqueue(object :
//                Callback<QuestionList> {
//
//                override fun onResponse(call: Call<QuestionList>, response: Response<QuestionList>) {
//
//                    Log.d(MainActivity.tag, "Total Questions: " + (response.body()?.items?.size ?: 0))
//                    val questionList = response.body()
//                    questionList?.items?.let { questions.value = it }
//                    println("AAAAAAAAA")
//                }
//
//                override fun onFailure(call: Call<QuestionList>, t: Throwable) {
//                    Log.e(MainActivity.tag, "Got error : " + t.localizedMessage)
//                    println("BBBBBBB")
//                }
//            })
        }
    }
}
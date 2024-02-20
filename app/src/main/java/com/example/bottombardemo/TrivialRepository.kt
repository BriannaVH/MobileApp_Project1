package com.example.bottombardemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TrivialRepository(private val trivialQuestionDao: TrivialQuestionDao) {

    val allQuestions: LiveData<List<TrivialQuestion>> = trivialQuestionDao.getAllQuestions()
    val searchResults = MutableLiveData<List<TrivialQuestion>>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertQuestion(newQuestion: TrivialQuestion) {
        coroutineScope.launch(Dispatchers.IO) {
            trivialQuestionDao.insertQuestion(newQuestion)
        }
    }

    fun clearQuestionsTable() {
        coroutineScope.launch(Dispatchers.IO) {
            trivialQuestionDao.clearQuestionsTable()
        }
    }

    fun findQuestion(id: Int) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFindQuestion(id).await()
        }
    }

    private fun asyncFindQuestion(id: Int): Deferred<List<TrivialQuestion>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async trivialQuestionDao.findQuestion(id)
        }
}
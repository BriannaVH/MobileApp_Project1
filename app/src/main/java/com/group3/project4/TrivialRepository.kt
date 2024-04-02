package com.group3.project4

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

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun insertQuestions() {
        coroutineScope.launch(Dispatchers.IO) {
            trivialQuestionDao.clearQuestionsTable()

            val question1 = TrivialQuestion("When was UVM founded?", "1791", "1950", "1841", "1799")
            val question2 = TrivialQuestion("What are the UVM colors?", "Green and gold", "Red and blue", "Blue and white", "Black and green")
            val question3 = TrivialQuestion("What is the UVM mascot?", "Catamount", "Lion", "Panther", "Dog")
            val question4 = TrivialQuestion("Where is UVM located?", "Burlington Vermont", "LA, California", "Phoenix Arizona", "St Johnsbury, Vermont")
            val question5 = TrivialQuestion("What Lake is UVM located near?", "Lake Champlain", "Lake Willoughby", "Lake Bomoseen", "Lake Saint Catherine")
            val question6 = TrivialQuestion("What is the name of the main library?", "Howe", "Uris library", "Bobst library", "Mcquade library")
            val question7 = TrivialQuestion("What is the school motto?", "\"Studiis et Rebus Honestis\" — \"For studies and other honest pursuits\"", "“Mens agitat molem.\tMind moves matter.”", "“Lux sit. Let there be light.”", "“Crescat scientia; vita excolatur.Let knowledge increase; let life be enriched.”")
            val question8 = TrivialQuestion("What years are required to live on campus?", "First years", "Second years", "Third years", "A and B")
            val question9 = TrivialQuestion("How many campuses is UVM composed of?", "4", "5", "3", "2")
            val question10 = TrivialQuestion("How many residential complexes are there?", "9", "15", "5", "7")



            trivialQuestionDao.insertQuestion(question1)
            trivialQuestionDao.insertQuestion(question2)
            trivialQuestionDao.insertQuestion(question3)
            trivialQuestionDao.insertQuestion(question4)
            trivialQuestionDao.insertQuestion(question5)
            trivialQuestionDao.insertQuestion(question6)
            trivialQuestionDao.insertQuestion(question7)
            trivialQuestionDao.insertQuestion(question8)
            trivialQuestionDao.insertQuestion(question9)
            trivialQuestionDao.insertQuestion(question10)
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
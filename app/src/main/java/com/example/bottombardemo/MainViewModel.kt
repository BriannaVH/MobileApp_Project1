package com.example.bottombardemo

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(application: Application) : ViewModel() {

    val allQuestions: LiveData<List<TrivialQuestion>>
    private val trivialRepository: TrivialRepository
    val questionSearchResults: MutableLiveData<List<TrivialQuestion>>

    val allCourses: LiveData<List<Course>>
    private val repository: CourseRepository
    val searchResults: MutableLiveData<List<Course>>
    init {
        val trivialDb = TrivialQuestionDatabase.getInstance(application)
        val trivialQuestionDao = trivialDb.trivialQuestionDao()

        trivialRepository = TrivialRepository(trivialQuestionDao)
        allQuestions = trivialRepository.allQuestions
        questionSearchResults = trivialRepository.searchResults

        trivialRepository.clearQuestionsTable()
        trivialRepository.insertQuestions()

        val courseDb = CourseRoomDatabase.getInstance(application)
        val courseDao = courseDb.courseDao()
        repository = CourseRepository(courseDao)

        allCourses = repository.allCourses
        searchResults = repository.searchResults

    }

    //Courses queries
    fun insertCourse(course: Course) {
        repository.insertCourse(course)
    }

    fun findCourse(name: String) {
        repository.findCourse(name)
    }

    fun deleteCourse(name: String) {
        repository.deleteCourse(name)
    }

    fun findQuestion(id: Int) {
        trivialRepository.findQuestion(id)
    }
    fun clearQuestionsTable() {
        trivialRepository.clearQuestionsTable()
    }
}
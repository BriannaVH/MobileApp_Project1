package com.example.bottombardemo

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bottombardemo.Course
import com.example.bottombardemo.CourseRepository
import com.example.bottombardemo.CourseRoomDatabase

class MainViewModel(application: Application) : ViewModel() {

    val allCourses: LiveData<List<Course>>
    private val repository: CourseRepository
    val searchResults: MutableLiveData<List<Course>>

    init {
        val courseDb = CourseRoomDatabase.getInstance(application)
        val courseDao = courseDb.courseDao()
        repository = CourseRepository(courseDao)

        allCourses = repository.allCourses
        searchResults = repository.searchResults
    }

    fun insertCourse(course: Course) {
        repository.insertCourse(course)
    }

    fun findCourse(name: String) {
        repository.findCourse(name)
    }

    fun deleteCourse(name: String) {
        repository.deleteCourse(name)
    }
}
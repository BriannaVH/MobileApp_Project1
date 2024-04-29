package com.universityofvermont.UVMApp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TrivialQuestionDao {

    @Insert
    fun insertCourse(course: Course)

    @Insert
    fun insertQuestion(question: TrivialQuestion)

    @Query("SELECT * FROM courses WHERE courseName = :name")
    fun findCourse(name: String): List<Course>

    @Query("DELETE FROM TrivialQuestions")
    fun clearQuestionsTable()

    @Query("SELECT * FROM TrivialQuestions WHERE questionID = :id")
    fun findQuestion(id: Int): List<TrivialQuestion>

    @Query("SELECT * FROM TrivialQuestions")
    fun getAllQuestions(): LiveData<List<TrivialQuestion>>


}
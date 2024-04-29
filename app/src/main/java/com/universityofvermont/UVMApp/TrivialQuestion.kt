package com.universityofvermont.UVMApp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TrivialQuestions")
class TrivialQuestion {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "questionID")
    var id: Int = 0

    @ColumnInfo(name = "question")
    var question: String = ""

    @ColumnInfo(name = "correctAnswer")
    var correctAnswer: String = ""

    @ColumnInfo(name = "incorrectAnswer1")
    var incorrectAnswer1: String = ""

    @ColumnInfo(name = "incorrectAnswer2")
    var incorrectAnswer2: String = ""

    @ColumnInfo(name = "incorrectAnswer3")
    var incorrectAnswer3: String = ""

    constructor()

    constructor(question: String, correctAnswer: String, incorrectAnswer1: String, incorrectAnswer2: String, incorrectAnswer3: String) {
        this.question = question
        this.correctAnswer = correctAnswer
        this.incorrectAnswer1 = incorrectAnswer1
        this.incorrectAnswer2 = incorrectAnswer2
        this.incorrectAnswer3 = incorrectAnswer3

    }

    override fun toString(): String {
        return "TrivialQuestion(id=$id, question='$question', correctAnswer='$correctAnswer', incorrectAnswer1='$incorrectAnswer1', incorrectAnswer2='$incorrectAnswer2', incorrectAnswer3='$incorrectAnswer3')"
    }


}
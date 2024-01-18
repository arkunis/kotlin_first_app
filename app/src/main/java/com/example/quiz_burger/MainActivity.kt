package com.example.quiz_burger

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun afficherQuiz(view: View){
        val vueQuiz = Intent(this, QuizActivity::class.java)
        startActivity(vueQuiz)
    }

    fun afficherScore(view:View){
        val sauvegarde = getSharedPreferences("com.exemple.quizburger", Context.MODE_PRIVATE)
        var score = sauvegarde.getInt("mesScores", 0)
        if(score in 4..5){
            Toast.makeText(this,"Bravo tu as gagné un burger de ton choix ! Présente ce code en caisse", Toast.LENGTH_LONG).show()
        }
        if(score in 2..3){
            Toast.makeText(this,"Bravo tu as gagné un soda de ton choix ! Présente ce code en caisse", Toast.LENGTH_LONG).show()
        }
        if(score in 0..1){
            Toast.makeText(this,"Tu n'as pas gagné de cadeau", Toast.LENGTH_LONG).show()
        }
    }
}
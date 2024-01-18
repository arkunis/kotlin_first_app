package com.example.quiz_burger

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import layout.Quiz

class QuizActivity : AppCompatActivity() {

    var listeQuestion = ArrayList<Quiz>()
    var nbreBonneReponse: Int = 0
    var questionEncours: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        listeQuestion.add(Quiz("Combien de steak contient le Magik Burger ?", "3","1","4",3))
        listeQuestion.add(Quiz("Combien de steak contient Le Kamikaze Burger ?", "3", "6", "4", 2))
        listeQuestion.add(Quiz("Comment s'appelle le burger contenant du poulet ?", "Le poulet burger", "Le maxi cotecote", "Le chicken run !", 3))
        listeQuestion.add(Quiz("Comment s'appelle les ailes de poulet épicées ?", "Les spicy wings","Les ailes qui piquent la langue !", "Les spicy chickens", 1))
        listeQuestion.add(Quiz("En combien de temps est préparé votre burger ?", "3 min", "5 min","10 min", 2))

        afficherQuiz(listeQuestion.get(questionEncours))
    }

    fun afficherQuiz(quiz: Quiz){
        val textQ = findViewById<TextView>(R.id.questionQuiz)
        val textrQ1 = findViewById<TextView>(R.id.reponseQuiz1)
        val textrQ2 = findViewById<TextView>(R.id.questionQuiz2)
        val textrQ3 = findViewById<TextView>(R.id.questionQuiz3)
        textQ.text = quiz.question
        textrQ1.text = quiz.reponse
        textrQ2.text = quiz.reponse2
        textrQ3.text = quiz.reponse3
    }

    fun controlerReponse(valeur: Int){
        var quiz = listeQuestion.get(questionEncours)
        if(quiz.verifierReponse(valeur)){
            nbreBonneReponse += 1
            Toast.makeText(this, "+1 point", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "0 point", Toast.LENGTH_SHORT).show()
        }

        questionEncours++

        if(questionEncours >= listeQuestion.size){
            var alert = AlertDialog.Builder(this)
            if(nbreBonneReponse>=1){
                alert.setTitle("Partie terminé")
                alert.setMessage("Tu as eu : $nbreBonneReponse bonnes réponses ! \nBravo ! Clique sur le bouton scores pour voir ton cadeaux !")
                alert.setPositiveButton("ok"){ dialogInterface: DialogInterface?, i: Int -> finish()}
                alert.show()
            }else{
                alert.setTitle("Partie terminé")
                alert.setMessage("Tu as eu : $nbreBonneReponse bonnes réponses !\nDésolé, meilleurs chances la prochaine fois !")
                alert.setPositiveButton("ok"){ dialogInterface: DialogInterface?, i: Int -> finish()}
            }

            val sauvegarde = getSharedPreferences("com.exemple.quizburger", MODE_PRIVATE)
            sauvegarde.edit().putInt("mesScores", nbreBonneReponse).apply()
        }
        else{
            afficherQuiz(listeQuestion.get(questionEncours))
        }
    }

    fun reponseUnClick(view: View){
        controlerReponse(1)
    }
    fun reponseDeuxClick(view: View){
        controlerReponse(2)
    }
    fun reponseTroisClick(view: View){
        controlerReponse(3)
    }

}
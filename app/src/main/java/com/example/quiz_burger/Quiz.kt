package layout

class Quiz(var question: String, var reponse : String, var reponse2 : String, var reponse3 : String,
           var bonneReponse: Int) {

    fun verifierReponse(valeur: Int): Boolean {
        return valeur == bonneReponse
    }

}
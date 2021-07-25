package com.example.quizapp

object Constants{

    const val USER_NAME:String = "User_name"
    const val TOTAL_QUESTIONS:String = "Total_Questions"
    const val CORRECTANSWERS:String="Correct_Answers"

    fun getQuestions():ArrayList<Questions>{
        val questionslist = ArrayList<Questions>()

        val que1 = Questions(1,"Which country started world war II?" ,
            "England",
            "Germany",
            "Italy",
            "France",
            2)

        val que2 = Questions(2,"Who was the leader of the Soviet Union during World War II?" ,
            "Mikhail Gorbachev",
            "Nikita Kruschev",
            "Benito Mussolini",
            "Joseph Stalin",
            4)
        val que3 = Questions(3,"When was The People's Republic of China created?" ,
            "1939",
            "2019",
            "1949",
            "1945",
            3)
        val que4 = Questions(4,"Who is the current leader of North Korea?" ,
            "Kim Jong Un",
            "Kim Il Sung",
            "Kim Jong Il",
            "Park San Chook",
            1)
        val que5 = Questions(5,"Which Country won the Copa America 2021?" ,
            "Argentina",
            "Peru",
            "Colombia",
            "Brazil",
            1)
        questionslist.add(que1)
        questionslist.add(que2)
        questionslist.add(que3)
        questionslist.add(que4)
        questionslist.add(que5)
        return questionslist
    }

}

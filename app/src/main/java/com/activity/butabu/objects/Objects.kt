package com.activity.butabu.objects

import com.activity.butabu.dataclasses.Words

class Objects {

    val wordList = ArrayList<Words>()
    val usedWordList = ArrayList<Words>()
    init {
        createWords()
    }

    private fun createWords(){
        val prohibitedWords1 = listOf("Zəng", "Mobil", "Cihaz", "Nömrə")
        val prohibitedWords2 = listOf("Oxumaq", "Roman", "Cild", "Səhifə")
        val prohibitedWords3 = listOf("Laptop", "Texnologiya", "İnternet", "Ekran")
        val prohibitedWords4 = listOf("Maşın", "Sürmək", "Təkər", "Yol")
        val prohibitedWords5 = listOf("Su", "Dalğa", "Göl", "Qum")
        val prohibitedWords6 = listOf("Ev", "Qadın", "Yemək", "Təmizlik")
        val prohibitedWords7 = listOf("Musiqi", "Çalmaq", "Səs", "Alət")
        val prohibitedWords8 = listOf("Damcı", "Bulud", "Yağmaq", "Hava")
        val prohibitedWords9 = listOf("Top", "Komanda", "Oyun", "Qapı")
        val prohibitedWords10 = listOf("İşıq", "Ulduz", "İsti", "Gecə")
        val prohibitedWords11 = listOf("Nişan", "Gəlin", "Bəy", "Məclis")
        val prohibitedWords12 = listOf("Şüşə", "Divar", "Açmaq", "Bağlamaq")
        val prohibitedWords13 = listOf("Valyuta", "Bank", "Məzənnə", "Kağız")
        val prohibitedWords14 = listOf("Gülmək", "Film", "Zarafat", "Tamaşa")
        val prohibitedWords15 = listOf("Təyyarə", "Hava", "Qanad", "Səyahət")
        val prohibitedWords16 = listOf("İçmək", "Su", "İsti", "Şirin")
        val prohibitedWords17 = listOf("Bitki", "Ağac", "Çiçək", "Toxum")
        val prohibitedWords18 = listOf("Tütün", "Çəkmək", "Alov", "Duman")
        val prohibitedWords19 = listOf("Musiqi", "Alət", "Klaviatura", "Çalmaq")
        val prohibitedWords20 = listOf("Texnologiya", "İnternet", "Ekran", "Maus")

        val word1 = Words("Telefon", "category1", "sade", "language1", prohibitedWords1)
        val word2 = Words("Kitab", "category2", "sade", "language2", prohibitedWords2)
        val word3 = Words("Komputer", "category3", "sade", "language3", prohibitedWords3)
        val word4 = Words("Avtomobil", "category4", "sade", "language4", prohibitedWords4)
        val word5 = Words("Dəniz", "category5", "sade", "language5", prohibitedWords5)
        val word6 = Words("Evdar", "category6", "sade", "language6", prohibitedWords6)
        val word7 = Words("Gitar", "category7", "sade", "language7", prohibitedWords7)
        val word8 = Words("Yağış", "category8", "sade", "language8", prohibitedWords8)
        val word9 = Words("Futbol", "category9", "sade", "language9", prohibitedWords9)
        val word10 = Words("Günəş", "category10", "sade", "language10", prohibitedWords10)
        val word11 = Words("Toy", "category11", "sade", "language11", prohibitedWords11)
        val word12 = Words("Pəncərə", "category12", "sade", "language12", prohibitedWords12)
        val word13 = Words("Pul", "category13", "sade", "language13", prohibitedWords13)
        val word14 = Words("Komediya", "category14", "sade", "language14", prohibitedWords14)
        val word15 = Words("Uçmaq", "category15", "sade", "language15", prohibitedWords15)
        val word16 = Words("Çay", "category16", "sade", "language16", prohibitedWords16)
        val word17 = Words("Bağ", "category17", "sade", "language17", prohibitedWords17)
        val word18 = Words("Siqaret", "category18", "sade", "language18", prohibitedWords18)
        val word19 = Words("Piano", "category19", "sade", "language19", prohibitedWords19)
        val word20 = Words("Kompüter", "category20", "sade", "language20", prohibitedWords20)

        for (i in listOf(word1, word2, word3, word4, word5, word6, word7, word8, word9, word10, word11, word12, word13, word14, word15, word16, word17, word18, word19, word20)){
            FireStoreRepository().writeData(i)
        }
    }
}

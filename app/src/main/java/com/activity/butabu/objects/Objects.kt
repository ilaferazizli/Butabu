package com.activity.butabu.objects

import com.activity.butabu.dataclasses.Words
import com.activity.butabu.dataclasses.WordsSolo

class Objects {

    val wordList = ArrayList<WordsSolo>()
    val usedWordList = ArrayList<WordsSolo>()
    init {
        createWordsSolo()
    }

    private fun createWordsSolo(){
        val categoryAnimals = listOf(
            Words("Pişik", "Heyvan", "sade", "azerbaycan", listOf("Ev", "Qat", "Kiçik", "Dost", "Gözəl", "Yemək")),
            Words("İt", "Heyvan", "sade", "azerbaycan", listOf("Ev", "Dost", "Böyük", "Qat", "Gözəl", "Yemək")),
            Words("At", "Heyvan", "sade", "azerbaycan", listOf("Yarış", "Böyük", "Kənd", "Dost", "Yük", "Sürüş")),
            Words("İnək", "Heyvan", "sade", "azerbaycan", listOf("Kənd", "Süd", "Böyük", "Qoyun", "Yemək", "Yük")),
            Words("Qoyun", "Heyvan", "sade", "azerbaycan", listOf("Kənd", "Yemək", "Yük", "Qoyunçuluq", "Süd", "Tüy")),
            Words("Maldar", "Heyvan", "sade", "azerbaycan", listOf("Kənd", "Qoyun", "İnək", "Yük", "Süd", "Yemək")),
            Words("Dəvə", "Heyvan", "sade", "azerbaycan", listOf("Səhra", "Yük", "Böyük", "Kənd", "Süd", "Sürüş")),
            Words("Gəmirici", "Heyvan", "sade", "azerbaycan", listOf("Kiçik", "Ev", "Qida", "Təhlükəsiz", "Tüy", "Şişkin")),
            Words("Tülkü", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Kiçik", "Qızıl", "Həddindən artıq", "Qida", "Dost")),
            Words("Kərtənkələ", "Heyvan", "sade", "azerbaycan", listOf("Bağ", "Kiçik", "Dost", "Yerdə", "Sürünən", "Yemək")),
            Words("Maral", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Böyük", "Qış", "Süd", "Dost", "Yük")),
            Words("Geyik", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Böyük", "Süd", "Yük", "Qış", "Yemək")),
            Words("Sincap", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Kiçik", "Yemək", "Dost", "Şirin", "Qida")),
            Words("Ayı", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Böyük", "Qış", "Yemək", "Dost", "Süd")),
            Words("Canavar", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Böyük", "Dost", "Yemək", "Qida", "Şirin")),
            Words("Qırmızı", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Böyük", "Yemək", "Dost", "Qida", "Süd")),
            Words("Kərtənkələ", "Heyvan", "sade", "azerbaycan", listOf("Yerdə", "Kiçik", "Şirin", "Dost", "Yemək", "Sürünən")),
            Words("Kanguru", "Heyvan", "sade", "azerbaycan", listOf("Səhra", "Böyük", "Sıçrayış", "Yük", "Yemək", "Dost")),
            Words("Ceyran", "Heyvan", "sade", "azerbaycan", listOf("Səhra", "Yemək", "Qida", "Dost", "Böyük", "Qış")),
            Words("Tülkü", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Yırtıcı", "Sürünən", "Gizli", "Yemək", "Dost")),
            Words("Filan", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Böyük", "Yemək", "Dost", "Qida", "Süd")),
            Words("Quş", "Heyvan", "sade", "azerbaycan", listOf("Hava", "Uçan", "Səhər", "Yemək", "Tüy", "Dost")),
            Words("Aqsaqqal", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Böyük", "Yemək", "Qida", "Dost", "Süd")),
            Words("Tülkü", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Yırtıcı", "Şirin", "Gizli", "Dost", "Yemək")),
            Words("Hippopotam", "Heyvan", "sade", "azerbaycan", listOf("Çay", "Böyük", "Suda", "Yemək", "Dost", "Qida")),
            Words("Züraf", "Heyvan", "sade", "azerbaycan", listOf("Səhra", "Böyük", "Yemək", "Dost", "Qida", "Süd")),
            Words("Şir", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Böyük", "Yırtıcı", "Dost", "Yemək", "Qida")),
            Words("Pələng", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Yırtıcı", "Böyük", "Yemək", "Dost", "Qida")),
            Words("Qartal", "Heyvan", "sade", "azerbaycan", listOf("Hava", "Uçan", "Yırtıcı", "Yemək", "Dost", "Tüy")),
            Words("Sırtlan", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Yırtıcı", "Yemək", "Dost", "Qida", "Şirin")),
            Words("Tısbağa", "Heyvan", "sade", "azerbaycan", listOf("Yerdə", "Kiçik", "Dost", "Yemək", "Qida", "Şirin")),
            Words("Müxərrik", "Heyvan", "sade", "azerbaycan", listOf("Kənd", "Kiçik", "Dost", "Yemək", "Qida", "Şirin")),
            Words("Quzu", "Heyvan", "sade", "azerbaycan", listOf("Kənd", "Süd", "Kiçik", "Dost", "Yemək", "Qida")),
            Words("Cavan", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Kiçik", "Yemək", "Dost", "Qida", "Şirin")),
            Words("Kükürt", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Kiçik", "Südlü", "Dost", "Yemək", "Qida")),
            Words("Kalıx", "Heyvan", "sade", "azerbaycan", listOf("Səhra", "Yemək", "Dost", "Böyük", "Qida", "Şirin")),
            Words("Sivri", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Südlü", "Dost", "Yemək", "Şirin", "Qida")),
            Words("Çinçilla", "Heyvan", "sade", "azerbaycan", listOf("Ev", "Kiçik", "Şirin", "Dost", "Yemək", "Qida")),
            Words("Səhra", "Heyvan", "sade", "azerbaycan", listOf("Səhra", "Kiçik", "Dost", "Yemək", "Qida", "Şirin")),
            Words("Quş", "Heyvan", "sade", "azerbaycan", listOf("Hava", "Uçan", "Yemək", "Qida", "Dost", "Şirin")),
            Words("Həvəskar", "Heyvan", "sade", "azerbaycan", listOf("Səhra", "Yemək", "Dost", "Şirin", "Kiçik", "Qida")),
            Words("Dünyalı", "Heyvan", "sade", "azerbaycan", listOf("Səhra", "Böyük", "Yemək", "Dost", "Şirin", "Qida")),
            Words("Dodaqlı", "Heyvan", "sade", "azerbaycan", listOf("Səhra", "Kiçik", "Dost", "Şirin", "Qida", "Yemək")),
            Words("Gözəl", "Heyvan", "sade", "azerbaycan", listOf("Böyük", "Səhra", "Yemək", "Şirin", "Dost", "Qida")),
            Words("Gəmirici", "Heyvan", "sade", "azerbaycan", listOf("Ev", "Kiçik", "Dost", "Yemək", "Qida", "Şirin")),
            Words("Əkiz", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Kiçik", "Yemək", "Dost", "Şirin", "Qida")),
            Words("Çığır", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Böyük", "Yemək", "Dost", "Şirin", "Qida")),
            Words("Kəklik", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Böyük", "Yemək", "Şirin", "Dost", "Qida")),
            Words("Büyücü", "Heyvan", "sade", "azerbaycan", listOf("Ev", "Kiçik", "Yemək", "Şirin", "Dost", "Qida")),
            Words("Mənzərə", "Heyvan", "sade", "azerbaycan", listOf("Səhra", "Böyük", "Yemək", "Şirin", "Dost", "Qida")),
            Words("Sivri", "Heyvan", "sade", "azerbaycan", listOf("Ev", "Kiçik", "Dost", "Şirin", "Yemək", "Qida")),
            Words("Cəngavər", "Heyvan", "sade", "azerbaycan", listOf("Səhra", "Böyük", "Yemək", "Şirin", "Dost", "Qida")),
            Words("Gözəl", "Heyvan", "sade", "azerbaycan", listOf("Ev", "Kiçik", "Dost", "Şirin", "Yemək", "Qida")),
            Words("Çürük", "Heyvan", "sade", "azerbaycan", listOf("Ev", "Kiçik", "Dost", "Şirin", "Yemək", "Qida")),
            Words("Doymuş", "Heyvan", "sade", "azerbaycan", listOf("Səhra", "Böyük", "Yemək", "Şirin", "Dost", "Qida")),
            Words("Sırtlan", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Kiçik", "Yemək", "Şirin", "Dost", "Qida")),
            Words("Çiçəklənmiş", "Heyvan", "sade", "azerbaycan", listOf("Səhra", "Böyük", "Yemək", "Şirin", "Dost", "Qida")),
            Words("Məğlubiyyət", "Heyvan", "sade", "azerbaycan", listOf("Meşə", "Böyük", "Yemək", "Şirin", "Dost", "Qida")),
            Words("Fəsil", "Heyvan", "sade", "azerbaycan", listOf("Səhra", "Böyük", "Yemək", "Şirin", "Dost", "Qida"))
        )



        for (i in categoryAnimals){
            FireStoreRepository().writeData(i)
        }
    }
}

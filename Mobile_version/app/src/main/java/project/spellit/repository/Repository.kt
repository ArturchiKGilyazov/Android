package project.spellit.repository

import project.spellit.repository.database.WordDataBase

//repository = model in MVVM
class Repository{

    companion object {
        val retrofitWorker = RetrofitWorker()
        var db: WordDataBase? = null
        fun getDatabase(): WordDataBase? {
            return db
    }
}


}
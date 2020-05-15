package com.vllenin.database

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.vllenin.database.database.AppDataBase
import com.vllenin.database.database.User
import com.vllenin.database.database.UserDAO

class MainActivity : AppCompatActivity() {

    private lateinit var appDataBase: AppDataBase
    private lateinit var userDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appDataBase = AppDataBase.getInstance(this)
        userDAO = appDataBase.userDAO()

        stubData()

    }

    private fun stubData() {
        val backgroundThread = HandlerThread("BackgroundThread")
        backgroundThread.start()
        val backgroundHandler = Handler(backgroundThread.looper)
        backgroundHandler.post {
            if (userDAO.getAllUser().isEmpty()) {
                val user1 = User(1996, "Do", "Thanh")
                val user2 = User(1234, "XXX", "YYY")
                userDAO.insert(users = *arrayOf(user1, user2))
                for (i in 1..10) {
                    userDAO.insert(User(i.toLong(), "FirstName $i", "LastName $i"))
                }
            }

            userDAO.getAllUser().forEach { user ->
                Log.d("XXX", "$user")
            }

            userDAO.findUserByIds(1996L, 1234L).forEach { user ->
                Log.d("XXX", "--- $user")
            }
        }
    }
}

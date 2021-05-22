package com.rightside.hackaton.config.preferences

import android.content.Context
import android.content.SharedPreferences

class AppSharedPreferences(private val context: Context) {


        private val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(PREFS_FILENAME, 0)

        companion object {
            const val PREFS_FILENAME = "com.rightside.hackaton.prefs"
            const val USER_ID = "userID"
            const val USER_NAME = "userNAME"
            const val USER_EMAIL = "userEMAIL"
        }
        var userName: String
            get() {
                return sharedPreferences.getString(USER_NAME, "").toString()
            }
            set(value) {
                sharedPreferences.edit().putString(USER_NAME, value).apply()
            }

        var userId: String?
            get() {
                return sharedPreferences.getString(USER_ID, null)
            }
            set(value) {
                sharedPreferences.edit().putString(USER_ID, value).apply()
            }
    }

package br.com.introapp.data

import android.content.Context

/**
 * Created by thiagozg on 15/08/17.
 */
class SPInfo(val context : Context) {

    fun updateIntroStatus(status : Boolean) {
        context
            .getSharedPreferences("PREF", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("status", status)
            .apply()
    }

    fun isIntroActivityShown() = context
            .getSharedPreferences("PREF", Context.MODE_PRIVATE)
            .getBoolean("status", false)

}
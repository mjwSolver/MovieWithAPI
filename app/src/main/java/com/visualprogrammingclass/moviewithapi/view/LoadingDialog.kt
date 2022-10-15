package com.visualprogrammingclass.moviewithapi.view

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.visualprogrammingclass.moviewithapi.R

class LoadingDialog(theActivity: Activity){
    private lateinit var isDialog: AlertDialog

    fun startLoading(theActivity:Activity) {
        val inflater = theActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.card_loading, null)

        val builder = AlertDialog.Builder(theActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isDialog = builder.create()
        isDialog.show()
    }

    fun isDismiss(){
        isDialog.dismiss()
    }
}

package com.example.lottiealertdialog

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.labters.lottiealertdialoglibrary.ClickListener
import com.labters.lottiealertdialoglibrary.DialogTypes
import com.labters.lottiealertdialoglibrary.LottieAlertDialog

class LottieAlertDialogActivity : AppCompatActivity() {
    var parentActivity: LottieAlertDialogActivity = this
    lateinit var alertDialog: LottieAlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie_alert_dialog)
        alertDialog = LottieAlertDialog.Builder(this, DialogTypes.TYPE_LOADING)
            .setTitle("Loading")
            .setDescription("Please Wait")
            .build()
        alertDialog.setCancelable(false)
        alertDialog.show()

        Handler().postDelayed(Runnable {
            alertDialog.changeDialog(
                LottieAlertDialog.Builder(parentActivity, DialogTypes.TYPE_QUESTION)
                    .setTitle("What Type")
                    .setDescription("Would you like to see ?")
                    .setPositiveText("Error")
                    .setNegativeText("Warning")
                    .setNoneText("None")
                    .setPositiveButtonColor(Color.parseColor("#f44242"))
                    .setPositiveTextColor(Color.parseColor("#ffeaea"))
                    .setNegativeButtonColor(Color.parseColor("#ffbb00"))
                    .setNegativeTextColor(Color.parseColor("#0a0906"))
                    .setNoneButtonColor(Color.parseColor("#1cd3ef"))
                    .setNoneTextColor(Color.parseColor("#c407c4"))
                    .setPositiveListener(object : ClickListener {
                        override fun onClick(dialog: LottieAlertDialog) {
                            alertDialog.changeDialog(
                                LottieAlertDialog.Builder(parentActivity, DialogTypes.TYPE_ERROR)
                                    .setTitle("Error")
                                    .setDescription("Some error has happened.")
                                    .setPositiveText("Okay")
                                    .setPositiveListener(object : ClickListener {
                                        override fun onClick(dialog: LottieAlertDialog) {
                                            dialog.dismiss()
                                        }
                                    })
                            )
                        }
                    })
                    .setNegativeListener(object : ClickListener {
                        override fun onClick(dialog: LottieAlertDialog) {
                            alertDialog.changeDialog(LottieAlertDialog.Builder(parentActivity, DialogTypes.TYPE_WARNING)
                                .setTitle("Warning")
                                .setDescription("Some warning.")
                                .setPositiveText("Okay")
                                .setPositiveListener(object : ClickListener {
                                    override fun onClick(dialog: LottieAlertDialog) {
                                        dialog.dismiss()
                                    }
                                })
                            )
                        }
                    })
                    .setNoneListener(object : ClickListener {
                        override fun onClick(dialog: LottieAlertDialog) {
                            dialog.dismiss()
                        }
                    })
            )
        }, 4000)
    }

    fun onClick(view: View) {
        alertDialog = LottieAlertDialog.Builder(this, DialogTypes.TYPE_LOADING)
            .setTitle("Loading")
            .setDescription("Please Wait")
            .build()
        alertDialog.setCancelable(false)
        alertDialog.show()

        Handler().postDelayed(Runnable {
            alertDialog.changeDialog(
                LottieAlertDialog.Builder(parentActivity, DialogTypes.TYPE_QUESTION)
                    .setTitle("What Type")
                    .setDescription("Would you like to see ?")
                    .setPositiveText("Error")
                    .setNegativeText("Warning")
                    .setNoneText("None")
                    .setPositiveButtonColor(Color.parseColor("#f44242"))
                    .setPositiveTextColor(Color.parseColor("#ffeaea"))
                    .setNegativeButtonColor(Color.parseColor("#ffbb00"))
                    .setNegativeTextColor(Color.parseColor("#0a0906"))
                    .setNoneButtonColor(Color.parseColor("#1cd3ef"))
                    .setNoneTextColor(Color.parseColor("#c407c4"))
                    .setPositiveListener(object : ClickListener {
                        override fun onClick(dialog: LottieAlertDialog) {
                            alertDialog.changeDialog(
                                LottieAlertDialog.Builder(parentActivity, DialogTypes.TYPE_ERROR)
                                    .setTitle("Error")
                                    .setDescription("Some error has happened.")
                                    .setPositiveText("Okay")
                                    .setPositiveListener(object : ClickListener {
                                        override fun onClick(dialog: LottieAlertDialog) {
                                            dialog.dismiss()
                                        }
                                    })
                            )
                        }
                    })
                    .setNegativeListener(object : ClickListener {
                        override fun onClick(dialog: LottieAlertDialog) {
                            alertDialog.changeDialog(LottieAlertDialog.Builder(parentActivity, DialogTypes.TYPE_WARNING)
                                .setTitle("Warning")
                                .setDescription("Some warning.")
                                .setPositiveText("Okay")
                                .setPositiveListener(object : ClickListener {
                                    override fun onClick(dialog: LottieAlertDialog) {
                                        dialog.dismiss()
                                    }
                                })
                            )
                        }
                    })
                    .setNoneListener(object : ClickListener {
                        override fun onClick(dialog: LottieAlertDialog) {
                            dialog.dismiss()
                        }
                    })
            )
        }, 2000)
    }
}

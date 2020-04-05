package com.wuxiaosu.operatorclient.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.wuxiaosu.operatorclient.R


/**
 * Created by su on 2020/04/04.
 */
class ProgressBarDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = false
        return AlertDialog.Builder(activity!!)
                .setTitle(R.string.dialog_title_progress)
                .setView(R.layout.fragment_progress_dialog)
                .create()
    }
}
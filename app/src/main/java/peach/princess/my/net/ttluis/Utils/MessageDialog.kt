package peach.princess.my.net.ttluis.Utils

import android.os.Build
import android.os.Bundle

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.AnyRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import kotlinx.android.synthetic.main.dialog_generic.*
import peach.princess.my.net.ttluis.Utils.Constants.Dialogs.DESCRIPTION_ID
import peach.princess.my.net.ttluis.Utils.Constants.Dialogs.DIALOG_SIMPLE
import peach.princess.my.net.ttluis.Utils.Constants.Dialogs.FIRST_BUTTON
import peach.princess.my.net.ttluis.Utils.Constants.Dialogs.SECOND_BUTTON
import peach.princess.my.net.ttluis.Utils.Constants.Dialogs.TITLE_ID
import peach.princess.my.net.ttluis.ui.Base.BaseDialog
import java.util.*


/**
 * General dialog for show informative messages throughout the application
 * @author dD
 */
class MessageDialog : BaseDialog() {

    companion object {
        var emptyListener: () -> Unit = { }
        var actionListener: () -> Unit = { }
        var secondActionListener: () -> Unit = { }
        private var dialogType: Int = 0
        private var categoryDialog: Int = 0

        /**
         * Create new instance of MessageDialog
         * @param[message] it is the string for show text in view.
         * @return new instance of MessageDialog with arguments stored.
         */
        fun newInstance(@StringRes titleId: Int,
                        message: String,
                        firstButtonName: String = "",
                        secondButtonName: String = "",
                        action: () -> Unit = emptyListener,
                        action2: () -> Unit = emptyListener,
                        dialogType: Int,
                        @AnyRes categoryDialog: Int,
                        tag: String): MessageDialog {
            this.actionListener = action
            this.secondActionListener = action2
            this.dialogType = dialogType
            this.categoryDialog = categoryDialog

            return MessageDialog().apply {
                arguments = Bundle().apply {
                    putInt(TITLE_ID, titleId)
                    putString(DESCRIPTION_ID, message)
                    putString(FIRST_BUTTON, firstButtonName)
                    putString(SECOND_BUTTON, secondButtonName)
                    putString("DIALOG", tag)
                }
            }
        }
    }

    override val layoutId: Int
        get() = categoryDialog

    /**
     * It is decided which text will be displayed
     */
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleId = arguments?.getInt(TITLE_ID)
        val message = arguments?.getString(DESCRIPTION_ID)
        val firstButton = arguments?.getString(FIRST_BUTTON)
        val secondButton = arguments?.getString(SECOND_BUTTON)
        val tag = arguments?.getString("DIALOG")
        if (titleId != null && message != null && firstButton != null && secondButton != null) {
            setData(titleId, message, firstButton, secondButton)
        }

        /**
         * Verify which buttons will show the dialog according to its type, whether it
         * is simple or with actions
         */
        if (dialogType == DIALOG_SIMPLE) {
            btn_optionTwo.visibility = View.VISIBLE
            btn_optionOne.visibility = View.GONE
            btn_optionTwo.setOnClickListener {
                secondActionListener()
                this.dismiss()

            }

        } else {
            btn_optionOne.setOnClickListener {
                this.dismiss()
                actionListener()
            }
            btn_optionTwo.setOnClickListener {
                this.dismiss()
                secondActionListener()

            }
        }
    }

    /**
     * Set strings in all fields
     */
    private fun setData(titleId: Int, message: String, firsButton: String, secondButton: String) {
        tvTitle.setText(titleId)
        btn_optionOne.text = firsButton
        btn_optionTwo.text = secondButton
        tvDescription.text = message
    }

}
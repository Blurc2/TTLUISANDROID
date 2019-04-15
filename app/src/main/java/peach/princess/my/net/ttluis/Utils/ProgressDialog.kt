package peach.princess.my.net.ttluis.Utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import peach.princess.my.net.ttluis.R


/**
 * Object that showing a progressDialog for each request
 * @author dD
 */
@SuppressLint("StaticFieldLeak")
object ProgressDialog {

    private var progressDialog: Dialog? = null

    /**
     * Show the progress dialog
     * @param[context] is necessary for create the Dialog
     */
    fun show(context: Context,value:Int=0){
        if(progressDialog == null){
            progressDialog = Dialog(context).apply {
                setContentView(R.layout.layout_loading_dialog)
                setCancelable(false)
            }
        }
        progressDialog?.window?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT)
        //progressDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        progressDialog?.show()
    }
    /**
     * Hide the current progress dialog that is showing
     */
    fun dismiss(){
        progressDialog?.let {
            if(it.isShowing) it.dismiss()
        }
        progressDialog = null
    }
}
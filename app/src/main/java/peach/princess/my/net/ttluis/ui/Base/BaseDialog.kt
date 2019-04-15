package peach.princess.my.net.ttluis.ui.Base

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import peach.princess.my.net.ttluis.R


/**
 * BaseDialog class, this must be override in all dialogs
 * @author Itzae
 */
abstract class BaseDialog: DialogFragment() {

    /**
     * Layout resource id for load fragment, this parameter must be override in fragment child
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Create the dialog configuration
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setCancelable(false)
            setCanceledOnTouchOutside(false)
           setOnKeyListener{_,keyCode,_->
               keyCode == android.view.KeyEvent.KEYCODE_BACK
           }
            window.requestFeature(Window.FEATURE_NO_TITLE)
            window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        }
    }


    /**
     * Set theme for dialog from styles values
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomThemeDialog)
    }


    /**
     * Inflate view with the layout id override in child fragment, this method is not necessary
     * implement in this dialog.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container)
    }

    /*fun showDialog(@StringRes title: Int = R.string.error_message, message: String, type: DialogType = DialogType.NOTICE){
        fragmentManager?.showSimpleDialog(title, message, type)
    }*/

}
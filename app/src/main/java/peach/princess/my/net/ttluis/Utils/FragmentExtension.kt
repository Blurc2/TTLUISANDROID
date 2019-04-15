package peach.princess.my.net.ttluis.Utils


import androidx.annotation.AnyRes
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager


/**
 * Created by dD on 02/10/18.
 */

fun FragmentManager.showSimpleDialog(@StringRes titleId: Int, message: String, firstButton: String, secondButton: String,
                                     tag: String = "", action: () -> Unit, action2: () -> Unit,
                                     dialogType: Int, @AnyRes categoryDialog: Int) {
    showDialogOnce(MessageDialog.newInstance(titleId, message, firstButton, secondButton, action, action2, dialogType, categoryDialog,tag), tag)

}

fun FragmentManager.showDialogOnce(dialog: DialogFragment, tag: String){
    if(fragmentAlreadyExist(tag)) return
    dialog.show(this, tag)
}

fun FragmentManager.fragmentAlreadyExist(tag: String): Boolean {
    return this.findFragmentByTag(tag) != null
}




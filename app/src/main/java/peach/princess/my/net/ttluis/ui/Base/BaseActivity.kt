package peach.princess.my.net.ttluis.ui.Base

import android.os.Build

import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.annotation.AnyRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import peach.princess.my.net.ttluis.Utils.showSimpleDialog


/**
 * BaseDialog class, this must be override in all activities
 * @author Itzae
 */
abstract class BaseActivity : AppCompatActivity() {


    /**
     * Set action to android home button is clicked.
     */
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    /**
     * Show a dialog
     */
    fun showCustomDialog(@StringRes titleid: Int, message: String, firstButton: String,
                         secondButton: String, tag: String = "",
                         action: () -> Unit, action2: () -> Unit,
                         dialogType: Int, @AnyRes categoryDialog: Int) {

        supportFragmentManager.showSimpleDialog(
                titleId = titleid,
                message = message,
                firstButton = firstButton,
                secondButton = secondButton,
                tag = tag,
                action = action,
                action2 = action2,
                dialogType = dialogType,
                categoryDialog = categoryDialog)
    }

    /**
     * Create a status bar transparent in current activity.
     */
    fun makeTransparentStatusBar() {
        val flags = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            window.decorView.systemUiVisibility = flags
        }
    }




}
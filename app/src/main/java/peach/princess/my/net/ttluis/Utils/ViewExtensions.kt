package peach.princess.my.net.ttluis.Utils

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import android.text.InputFilter

import android.widget.EditText
import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout


/**
 * Extension functions for Widgets that extend of View
 *
 * @author dD
 */


/**
 * Get the text and cut the ends from a EditText
 */
fun EditText.setMaxLength(length: Int) {
    val filterArray = arrayOfNulls<InputFilter>(1)
    filterArray[0] = InputFilter.LengthFilter(length)
    this.filters = filterArray
}

/**
 * Get the text and cut the ends from a EditText
 */
fun EditText.getString() = this.text.toString().trim()

/**
 * Get the text and cut the ends from a TextInputLayout
 */
fun TextInputLayout.getString() = this.editText!!.getString()

/**
 * Set text with Html format into current TextView
 * @param[text] String id with format html
 */

fun TextView.setHtml(@StringRes text: Int){
    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
        this.text = Html.fromHtml(this.context.getString(text))
    }else{

        this.text = Html.fromHtml(this.context.getString(text), Html.FROM_HTML_MODE_COMPACT)
    }
}

/**
 * Set text with Html format into current TextView
 * @param[text] String with format html
 */
fun TextView.setHtml(text: String){
    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
        this.text = Html.fromHtml(text)
    }else{

        this.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
    }
}

/**
 * Inflate view from a ViewGroup
 * @param[layoutRes] Layout id of the view to inflate
 * @para[attachToRoot] Whether the inflated hierarchy should be attached to the root parameter?
 *        If false, root is only used to create the correct subclass of LayoutParams for the root view.
 * @return The root View of the inflated hierarchy.
 */

fun ViewGroup.inflate(layoutRes: Int, attachToRoot: Boolean = false) : View =

        LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

/**
 * Invert visibility to View, only between VISIBLE and GONE
 */
fun View.invertVisibility() {
    this.visibility = if(this.visibility == View.VISIBLE) View.GONE else View.VISIBLE
}
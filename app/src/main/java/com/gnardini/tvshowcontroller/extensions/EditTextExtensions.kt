package com.gnardini.tvshowcontroller.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.addTextWatcher(callback: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            // Do nothing
        }

        override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // Do nothing
        }

        override fun onTextChanged(optionalCharSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            optionalCharSequence?.let { charSequence ->
                callback.invoke(charSequence.toString())
            }
        }
    })
}

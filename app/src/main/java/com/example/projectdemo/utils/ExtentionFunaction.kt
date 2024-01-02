package com.example.projectdemo.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.text.SpannedString
import android.view.View
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.italic
import androidx.core.text.underline

//for Under funcion
fun Activity.formattedUnderLineFun(textForUnderline: String): SpannedString {
    val formattedText = buildSpannedString {
        append(textForUnderline)
        bold { append("Bold") }
        italic { append("Italic") }
        underline { append("Underline") }
        bold { italic { append("Bold Italic") } }
    }
    return formattedText
}

//for NoneUnder funcion
fun Activity.formattedNoneunderLineFun(textForUnderline: String): SpannedString {
    val formattedText = buildSpannedString {
        append(textForUnderline)
        bold { append("Bold") }
        italic { append("Italic") }
        bold { italic { append("Bold Italic") } }
    }
    return formattedText
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun Context.NetWorkCheck():Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}

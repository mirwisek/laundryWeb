package com.ecommerce.laundryweb

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_FADE
import com.google.android.material.snackbar.Snackbar

fun Drawable.changeDrawableTint(@ColorInt tint: Int) {
    DrawableCompat.setTint(
        DrawableCompat.unwrap(this),
        tint
    )
}


fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

fun View.showSnack(msg: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, msg, duration)
        .setAnimationMode(ANIMATION_MODE_FADE).show()
}
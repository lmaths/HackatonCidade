package com.rightside.hackaton.extensions

import android.view.View
import com.airbnb.lottie.LottieAnimationView

    fun View.visibleUI() {
        this.visibility = View.VISIBLE
    }
    fun View.invisibleUI() {
        this.visibility = View.INVISIBLE
    }

    fun LottieAnimationView.visibleUI() {
        this.visibility = View.VISIBLE
    }
    fun LottieAnimationView.invisibleUI() {
        this.visibility = View.INVISIBLE
    }

package com.antz.movingball

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas

class Grenade(var image : Bitmap) {
    var x : Int = 0
    var y : Int = 0
    var w : Int = 0
    var h : Int = 0
    private var xVelocity = 20
    private var yVelocity = 20

    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    init {
        w = image.width
        h = image.height

        x = screenWidth / 2
        y = screenHeight / 2
    }

    fun draw(canvas : Canvas) {
        canvas.drawBitmap(image, x.toFloat(), y.toFloat(), null)
    }

    fun update() {
        if (x > screenWidth - w || x > w) {
            xVelocity = xVelocity * -1
        }

        if (y > screenHeight - h || y < h) {
            yVelocity = yVelocity * -1
        }

        x += (xVelocity)
        y += (yVelocity)
    }
}
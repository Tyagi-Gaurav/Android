package com.antz.movingball

import android.graphics.Canvas
import android.view.SurfaceHolder

class GameThread(private val surfaceHolder: SurfaceHolder,
                 private val gameView: GameView) : Thread() {
    private var running = false


    fun setRunning(value : Boolean) {
        running = value
    }


    private val targetFPS = 50 // Frames per second

    override fun run() {
        var startTime : Long
        var timeMillis : Long
        var waitTime : Long

        val targetTime = (1000 / targetFPS).toLong()

        while (running) {
            startTime = System.nanoTime()
            canvas = null

            try {
                canvas = surfaceHolder.lockCanvas()

                synchronized(surfaceHolder) {
                    this.gameView.update()
                    this.gameView.draw(canvas!!)
                }
            } catch (e : Exception) {
                e.printStackTrace()
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas)
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000
            waitTime = targetTime - timeMillis

            try {
                if (waitTime > 0) {
                    sleep(waitTime)
                }
            } catch (e : Exception) {
                e.printStackTrace()
            }
        }
    }

    private companion object {
        private var canvas : Canvas? = null
    }
}
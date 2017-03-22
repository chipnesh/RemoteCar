package me.chipnesh.hardware

import lejos.hardware.Button
import lejos.hardware.Key
import lejos.hardware.KeyListener
import lejos.hardware.ev3.LocalEV3
import org.opencv.core.Core

class LegoBrick {
    init {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
    }

    fun escapePressed(): Boolean {
        return Button.ESCAPE.isDown
    }

    fun camera(): Camera {
        return Camera()
    }

    fun escapeHandler(runnable: () -> Unit) {
        Button.ESCAPE.addKeyListener(object : KeyListener {
            override fun keyPressed(k: Key) {
                // ignored
            }

            override fun keyReleased(k: Key) {
                runnable()
            }
        })
    }

    fun waitEscape() {
        Button.ESCAPE.waitForPressAndRelease()
    }
}

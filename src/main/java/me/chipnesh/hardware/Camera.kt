package me.chipnesh.hardware

import java.io.File

class Camera {

    private val openCVVideoProvider = OpenCVVideoProvider()

    fun stream(): VideoProvider {
        return openCVVideoProvider
    }

    fun off() {
        openCVVideoProvider.release()
    }

    fun exist(): Boolean {
        return File("/dev/device0").exists() // todo
    }
}

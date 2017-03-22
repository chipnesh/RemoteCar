package me.chipnesh.hardware

import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.highgui.Highgui
import org.opencv.highgui.Highgui.CV_CAP_PROP_FRAME_HEIGHT
import org.opencv.highgui.Highgui.CV_CAP_PROP_FRAME_WIDTH
import org.opencv.highgui.VideoCapture

class OpenCVVideoProvider internal constructor() : VideoProvider {

    private val mat: Mat
    private val capture: VideoCapture = VideoCapture(0)

    init {
        capture.set(CV_CAP_PROP_FRAME_WIDTH, 160.0)
        capture.set(CV_CAP_PROP_FRAME_HEIGHT, 160.0)
        mat = Mat()
    }

    override fun capture(): ByteArray {
        if (!capture.isOpened) {
            capture.open(0)
        }
        capture.read(mat)
        if (!mat.empty()) {
            val buf = MatOfByte()
            Highgui.imencode(".jpg", mat, buf)
            return buf.toArray()
        } else {
            return ByteArray(0)
        }
    }

    fun release() {
        capture.release()
    }
}

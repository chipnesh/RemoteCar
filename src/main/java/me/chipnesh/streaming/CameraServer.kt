package me.chipnesh.streaming

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import me.chipnesh.hardware.Camera

class CameraServer(private val camera: Camera) {
    private lateinit var dataStream: DataStream
    private var closed: Boolean = false

    fun up() = launch(CommonPool) {
        try {
            val videoProvider = camera.stream()
            dataStream = SocketDataStream()
            dataStream.write(HeaderPackage())

            while (!closed) {
                val imageBytes = videoProvider.capture()

                if (imageBytes.isNotEmpty()) {
                    try {
                        dataStream.write(ImagePackage(imageBytes))
                    } catch (e: Exception) {
                        if (!closed) {
                            dataStream = SocketDataStream()
                            dataStream.write(HeaderPackage())
                        }
                    }

                }
            }
        } catch (e: Exception) {
            throw RuntimeException(e)
        } finally {
            down()
        }
    }

    fun down() {
        closed = true
        camera.off()
        dataStream.close()
    }
}
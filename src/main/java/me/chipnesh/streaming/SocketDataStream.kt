package me.chipnesh.streaming

import java.io.IOException
import java.net.ServerSocket
import java.net.Socket

class SocketDataStream @Throws(IOException::class)
constructor() : DataStream {

    private val ss: ServerSocket = ServerSocket(8080)
    private val sock: Socket

    init {
        sock = ss.accept()
    }

    override fun write(dataPackage: DataPackage) {
        val outputStream = sock.getOutputStream()
        outputStream.write(dataPackage.bytes())
        outputStream.flush()
    }

    override fun close() {
        ss.close()
    }
}

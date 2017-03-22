package me.chipnesh.streaming

import me.chipnesh.streaming.DataPackage.Companion.BOUNDARY_SIGN

class ImagePackage(private val imageBytes: ByteArray) : DataPackage {

    override fun bytes(): ByteArray = """--$BOUNDARY_SIGN
Content-type: image/jpg
Content-Length: ${imageBytes.size}

$imageBytes


""".toByteArray()
}

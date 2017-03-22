package me.chipnesh.streaming

interface DataPackage {
    fun bytes(): ByteArray

    companion object {
        val BOUNDARY_SIGN = "END"
        val NEW_LINE = "\r\n"
    }
}

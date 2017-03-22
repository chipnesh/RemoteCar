package me.chipnesh.hardware

interface VideoProvider {
    fun capture(): ByteArray
}

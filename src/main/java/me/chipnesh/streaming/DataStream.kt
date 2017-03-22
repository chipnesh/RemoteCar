package me.chipnesh.streaming

interface DataStream {
    fun write(dataPackage: DataPackage)
    fun close()
}

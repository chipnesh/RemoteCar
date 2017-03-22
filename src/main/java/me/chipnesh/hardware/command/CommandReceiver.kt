package me.chipnesh.hardware.command

interface CommandReceiver {
    fun isReceived(vararg commands: Command): Boolean

    fun receivedCommand(): Command
}

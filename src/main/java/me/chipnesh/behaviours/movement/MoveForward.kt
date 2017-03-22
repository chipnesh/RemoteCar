package me.chipnesh.behaviours.movement

import me.chipnesh.behaviours.AsyncBehavior
import me.chipnesh.hardware.command.Command
import me.chipnesh.hardware.command.CommandReceiver
import me.chipnesh.behaviours.Pilot

class MoveForward(private val receiver: CommandReceiver, private val pilot: Pilot) : AsyncBehavior() {

    override fun action() {
        pilot.forward()
    }

    override fun suppress() = pilot.stop()
    override fun takeControl() = receiver.isReceived(*Command.WITH_TOP_LEFT)
}

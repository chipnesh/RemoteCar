package me.chipnesh.behaviours.movement

import me.chipnesh.behaviours.AsyncBehavior
import me.chipnesh.hardware.command.Command
import me.chipnesh.hardware.command.CommandReceiver
import me.chipnesh.behaviours.Pilot

class MoveRight(private val controller: CommandReceiver, private val pilot: Pilot) : AsyncBehavior() {

    override fun action() {
        pilot.right()
    }

    override fun suppress() = pilot.neutral()
    override fun takeControl() = controller.isReceived(*Command.WITH_BOTTOM_RIGHT)
}

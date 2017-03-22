package me.chipnesh

import me.chipnesh.behaviours.Pilot
import me.chipnesh.behaviours.movement.MoveBackward
import me.chipnesh.behaviours.movement.MoveForward
import me.chipnesh.behaviours.movement.MoveLeft
import me.chipnesh.behaviours.movement.MoveRight
import me.chipnesh.hardware.command.IRReceiver
import me.chipnesh.hardware.LegoBrick
import me.chipnesh.streaming.CameraServer

object Main {

    @JvmStatic fun main(args: Array<String>) {
        val legoBrick = LegoBrick()
        val camera = legoBrick.camera()

        var cameraServer: CameraServer? = null
        if (camera.exist()) {
            cameraServer = CameraServer(camera)
            cameraServer.up()
        }

        val controller = IRReceiver()
        val pilot = Pilot()

        val forward = MoveForward(controller, pilot)
        val backward = MoveBackward(controller, pilot)
        val left = MoveLeft(controller, pilot)
        val right = MoveRight(controller, pilot)

        val behaviours = arrayOf(forward, backward, left, right)

        legoBrick.escapeHandler {
            cameraServer?.down()
            controller.off()
            pilot.off()
            behaviours.forEach { it.stop() }
            Runtime.getRuntime().exit(0)
        }

        behaviours.forEach { it.run() }

        legoBrick.waitEscape();
    }
}

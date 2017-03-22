package me.chipnesh.hardware.command

import lejos.hardware.port.SensorPort
import lejos.hardware.sensor.EV3IRSensor

class IRReceiver : CommandReceiver {

    private val ev3IRSensor: EV3IRSensor = EV3IRSensor(SensorPort.S1)

    override fun isReceived(vararg commands: Command): Boolean {
        val receivedCommand = receivedCommand()
        return commands.any { it == receivedCommand };
    }

    override fun receivedCommand(): Command {
        val control = ev3IRSensor.getRemoteCommand(0)
        when (control) {
            1 -> return Command.TOP_LEFT
            2 -> return Command.BOTTOM_LEFT
            3 -> return Command.TOP_RIGHT
            4 -> return Command.BOTTOM_RIGHT
            5 -> return Command.TOP_LEFT_TOP_RIGHT
            6 -> return Command.TOP_LEFT_BOTTOM_RIGHT
            7 -> return Command.BOTTOM_LEFT_TOP_RIGHT
            8 -> return Command.BOTTOM_LEFT_BOTTOM_RIGHT
            9 -> return Command.CENTRE
            else -> return Command.IGNORED
        }
    }

    fun off() {
        ev3IRSensor.close()
    }
}

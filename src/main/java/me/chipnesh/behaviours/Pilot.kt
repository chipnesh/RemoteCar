package me.chipnesh.behaviours

import lejos.hardware.ev3.LocalEV3
import lejos.hardware.motor.EV3LargeRegulatedMotor
import lejos.hardware.motor.EV3MediumRegulatedMotor

class Pilot {

    private var leftMotor: EV3LargeRegulatedMotor = EV3LargeRegulatedMotor(LocalEV3.get().getPort("A"))
    private var rightMotor: EV3LargeRegulatedMotor = EV3LargeRegulatedMotor(LocalEV3.get().getPort("B"))
    private var steering: EV3MediumRegulatedMotor = EV3MediumRegulatedMotor(LocalEV3.get().getPort("C"))

    private var minRight: Int = 0
    private var minLeft: Int = 0
    private var center: Int = 0

    init {
        leftMotor.speed = leftMotor.maxSpeed.toInt()
        rightMotor.speed = rightMotor.maxSpeed.toInt()
        leftMotor.synchronizeWith(arrayOf(rightMotor))

        calcSteeringCenter()
    }

    private fun calcSteeringCenter() {
        steering.speed = steering.maxSpeed.toInt()
        steering.setStallThreshold(10, 100)

        steering.forward()
        while (!steering.isStalled) Thread.yield()
        var r = steering.tachoCount

        steering.backward()
        try {
            Thread.sleep(200)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        while (!steering.isStalled) Thread.yield()
        var l = steering.tachoCount

        center = (l + r) / 2

        r -= center
        l -= center

        minRight = r
        minLeft = l

        steering.rotateTo(center)
        steering.resetTachoCount()
        steering.setStallThreshold(50, 1000)
    }


    fun forward() {
        leftMotor.startSynchronization()
        leftMotor.forward()
        rightMotor.forward()
        leftMotor.endSynchronization()
    }

    fun isMoving() = leftMotor.isMoving

    fun backward() {
        leftMotor.startSynchronization()
        leftMotor.backward()
        rightMotor.backward()
        leftMotor.endSynchronization()
    }

    fun stop() {
        leftMotor.startSynchronization()
        leftMotor.stop()
        rightMotor.stop()
        leftMotor.endSynchronization()
    }

    fun left() {
        if (steering.tachoCount != minLeft) {
            steering.rotateTo(minLeft)
        }
    }

    fun right() {
        if (steering.tachoCount != minRight) {
            steering.rotateTo(minRight)
        }
    }

    fun neutral() {
        if (steering.tachoCount != center) {
            steering.rotateTo(center)
        }
    }

    fun off() {
        leftMotor.close()
        rightMotor.close()
        steering.close()
    }
}

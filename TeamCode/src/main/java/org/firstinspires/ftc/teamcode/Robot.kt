package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sin
import kotlin.math.sqrt

class Robot(hardwareMap: HardwareMap) {
    val frontLeftMotor = hardwareMap.dcMotor.get("front_left_drive")
    val frontRightMotor = hardwareMap.dcMotor.get("front_right_drive")
    val backLeftMotor = hardwareMap.dcMotor.get("back_left_drive")
    val backRightMotor = hardwareMap.dcMotor.get("back_right_drive")

    fun setDrive(translationAngle: Double, translationPower: Double, turnPower: Double) {
        // calculate motor power
        val ADPower =
            translationPower * sqrt(2.0) * 0.5 * (sin(translationAngle) + cos(translationAngle))
        val BCPower =
            translationPower * sqrt(2.0) * 0.5 * (sin(translationAngle) - cos(translationAngle))

        // check if turning power will interfere with normal translation
        // check ADPower to see if trying to apply turnPower would put motor power over 1.0 or under -1.0
        var turningScale = max(abs(ADPower + turnPower), abs(ADPower - turnPower))
        // check BCPower to see if trying to apply turnPower would put motor power over 1.0 or under -1.0
        turningScale = max(turningScale, max(abs(BCPower + turnPower), abs(BCPower - turnPower)))

        // adjust turn power scale correctly
        if (abs(turningScale) < 1.0) {
            turningScale = 1.0
        }

        // set the motors, and divide them by turningScale to make sure none of them go over the top, which would alter the translation angle
        frontLeftMotor.power = (ADPower - turningScale) / turningScale
        backLeftMotor.power = (BCPower - turningScale) / turningScale
        frontRightMotor.power = (BCPower + turningScale) / turningScale
        backRightMotor.power = (ADPower + turningScale) / turningScale
    }
}
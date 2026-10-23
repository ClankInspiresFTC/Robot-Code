package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sin

class Robot(hardwareMap: HardwareMap) {
    val frontLeftMotor = hardwareMap.dcMotor.get("front_left_drive")
    val frontRightMotor = hardwareMap.dcMotor.get("front_right_drive")
    val backLeftMotor = hardwareMap.dcMotor.get("back_left_drive")
    val backRightMotor = hardwareMap.dcMotor.get("back_right_drive")

    init {
        // motors on the left side are mounted mirrored, so reverse them so that positive power drives every wheel forward
        // if the robot spins or strafes when you push forward, flip which side is reversed here
        frontLeftMotor.direction = DcMotorSimple.Direction.REVERSE
        backLeftMotor.direction = DcMotorSimple.Direction.REVERSE
        frontRightMotor.direction = DcMotorSimple.Direction.FORWARD
        backRightMotor.direction = DcMotorSimple.Direction.FORWARD

        for (motor in listOf(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor)) {
            motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        }
    }

    /**
     * @param translationAngle direction to drive in radians, 0 = right, PI/2 = forward
     * @param translationPower how fast to drive, 0.0 to 1.0
     * @param turnPower how fast to turn, -1.0 to 1.0, positive = clockwise
     */
    fun setDrive(translationAngle: Double, translationPower: Double, turnPower: Double) {
        // calculate motor power for the diagonal wheel pairs
        // A/D = front left + back right, B/C = front right + back left
        var ADPower = sin(translationAngle) + cos(translationAngle)
        var BCPower = sin(translationAngle) - cos(translationAngle)

        // scale so the larger pair is at full power, otherwise driving straight forward only reaches ~70% power
        val translationScale = max(abs(ADPower), abs(BCPower))
        if (translationScale > 0.0) {
            ADPower = ADPower / translationScale * translationPower
            BCPower = BCPower / translationScale * translationPower
        }

        // check if turning power will interfere with normal translation
        // check ADPower to see if trying to apply turnPower would put motor power over 1.0 or under -1.0
        var turningScale = max(abs(ADPower + turnPower), abs(ADPower - turnPower))
        // check BCPower to see if trying to apply turnPower would put motor power over 1.0 or under -1.0
        turningScale = max(turningScale, max(abs(BCPower + turnPower), abs(BCPower - turnPower)))

        // adjust turn power scale correctly
        if (turningScale < 1.0) {
            turningScale = 1.0
        }

        // set the motors, and divide them by turningScale to make sure none of them go over the top, which would alter the translation angle
        frontLeftMotor.power = (ADPower + turnPower) / turningScale
        backLeftMotor.power = (BCPower + turnPower) / turningScale
        frontRightMotor.power = (BCPower - turnPower) / turningScale
        backRightMotor.power = (ADPower - turnPower) / turningScale
    }
}

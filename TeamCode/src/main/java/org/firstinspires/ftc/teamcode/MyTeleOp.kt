package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.atan2
import kotlin.math.sqrt
import kotlin.math.pow



@TeleOp(name = "MyTeleOp", group = "TeleOp")
class MyTeleOp : LinearOpMode() {

    override fun runOpMode() {
        // TODO: pull your hardware out of the hardwareMap here

        telemetry.addLine("Initialized - waiting for start")
        telemetry.update()

        waitForStart()

        val frontLeftMotor = hardwareMap.dcMotor.get("front_left_drive")
        val frontRightMotor = hardwareMap.dcMotor.get("front_right_drive")
        val backLeftMotor = hardwareMap.dcMotor.get("back_left_drive")
        val backRightMotor = hardwareMap.dcMotor.get("back_right_drive")

        val controlX = gamepad1.left_stick_x
        val controlY = gamepad1.left_stick_y
        val rcontrolX = gamepad1.right_stick_x
        val rcontrolY = gamepad1.right_stick_x



        val radians = atan2(controlY, controlX)





        while (opModeIsActive()) {










        }
    }
}

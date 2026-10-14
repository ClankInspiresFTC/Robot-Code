package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.pow


@TeleOp(name = "MyTeleOp", group = "TeleOp")
class MyTeleOp : LinearOpMode() {

    override fun runOpMode() {
        // TODO: pull your hardware out of the hardwareMap here

        telemetry.addLine("Initialized - waiting for start")
        telemetry.update()

        waitForStart()

        val robot = Robot(hardwareMap)

        while (opModeIsActive()) {

            val controlX = gamepad1.left_stick_x
            val controlY = -gamepad1.left_stick_y

            val radians = atan2(controlY, controlX)
            val degrees = radians * 180 / PI


            robot.setDrive(0.0, 1.0, degrees);
        }
    }
}

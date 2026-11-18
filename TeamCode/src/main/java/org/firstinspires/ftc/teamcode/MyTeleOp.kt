package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.robotcore.external.Telemetry
import kotlin.math.atan2
import kotlin.math.hypot
import kotlin.math.min
//import kotlin.math.PI
//import kotlin.math.atan2


@TeleOp(name = "ClankinspiresTeleOp", group = "TeleOp")
class MyTeleOp : LinearOpMode() {

    override fun runOpMode() {

        // send telemetry to both the driver station and FTC Dashboard (192.168.43.1:8080/dash on a Control Hub)
        // monospace so the padded rows below line up into a label column and a value column
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE)

        telemetry.addLine("Initialized - waiting for start")
        telemetry.update()

        val robot = Robot(hardwareMap)

        waitForStart()

        while (opModeIsActive()) {
            // left stick drives/strafes, right stick x turns
            // stick y is negative when pushed up, so flip it so forward is positive
            val controlX = -gamepad1.left_stick_x.toDouble()
            val controlY = gamepad1.left_stick_y.toDouble()
            val rBumper = gamepad1.left_bumper
            val rTrigger = gamepad1.right_trigger

            //angle the stick is pushed
            val leftStickAngle = atan2(controlY, controlX)

            //How far from center the stick is
            val leftStickPower = min(hypot(controlX, controlY), 1.0)

            robot.frontLeftMotor.power = controlX

            val rStickX = gamepad1.right_stick_x.toDouble()

            addRow("RStickX", "%.1f°".format(rStickX))
            addRow("LStickPower", "%.2f".format(leftStickPower))
            addRow("LStickAngle", "%.2f".format(Math.toDegrees(leftStickAngle)))

            if (rBumper) {
                robot.releaseBalls()
            }
            robot.intakeTrigger(rTrigger.toDouble())
            robot.setDrive(leftStickAngle, leftStickPower, rStickX);

        }

        robot.setDrive(0.0, 0.0, 0.0)
    }

    // label on the left, value pushed to the right, like a settings row
    private fun addRow(label: String, value: String) {
        telemetry.addLine(label.padEnd(ROW_WIDTH - value.length) + value)
    }

    companion object {
        private const val ROW_WIDTH = 24
    }
}

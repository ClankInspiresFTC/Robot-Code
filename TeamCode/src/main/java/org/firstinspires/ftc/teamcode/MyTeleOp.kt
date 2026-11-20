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
        val robot = Robot(hardwareMap)

        // send telemetry to both the driver station and FTC Dashboard (192.168.43.1:8080/dash on a Control Hub)
        // monospace so the padded rows below line up into a label column and a value column
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE)

        telemetry.addLine("Initialized - waiting for start")
        telemetry.update()

        waitForStart()

        while (opModeIsActive()) {
            // left stick drives/strafes, right stick x turns
            // stick y is negative when pushed up, so flip it so forward is positive
            val controlX = -gamepad1.left_stick_x.toDouble()
            val controlY = gamepad1.left_stick_y.toDouble()
            val rBumper = gamepad1.left_bumper
            val rTrigger = gamepad1.right_trigger
            val turnAngle = atan2(controlY, controlX)
            val power = min(hypot(controlX, controlY), 1.0)



            robot.frontLeftMotor.power = controlX

            addRow("Angle", "%.1f°".format(Math.toDegrees(gamepad1.right_stick_x.toDouble())))
            addRow("Power", "%.2f".format(power))
            addRow("Turn", "%.2f".format(turnAngle))

            if (rBumper) {
                robot.releaseBalls()
            }
            robot.intakeTrigger(rTrigger.toDouble())
            robot.setDrive(turnAngle, power, gamepad1.right_stick_x.toDouble());

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

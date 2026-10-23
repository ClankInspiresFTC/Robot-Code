package org.firstinspires.ftc.teamcode

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.robotcore.external.Telemetry
import kotlin.math.atan2
import kotlin.math.hypot
import kotlin.math.min


@TeleOp(name = "ClankinspiresTeleOp", group = "TeleOp")
class MyTeleOp : LinearOpMode() {

    override fun runOpMode() {
        val robot = Robot(hardwareMap)

        // send telemetry to both the driver station and FTC Dashboard (192.168.43.1:8080/dash on a Control Hub)
        telemetry = MultipleTelemetry(telemetry, FtcDashboard.getInstance().telemetry)
        // monospace so the padded rows below line up into a label column and a value column
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE)

        telemetry.addLine("Initialized - waiting for start")
        telemetry.update()

        waitForStart()

        while (opModeIsActive()) {
            // left stick drives/strafes, right stick x turns
            // stick y is negative when pushed up, so flip it so forward is positive
            val controlX = gamepad1.left_stick_x.toDouble()
            val controlY = -gamepad1.left_stick_y.toDouble()
            val turn = gamepad1.right_stick_x.toDouble()

            // angle in radians, 0 = right, PI/2 = forward (setDrive uses sin/cos, which expect radians)
            val angle = atan2(controlY, controlX)
            // how far the stick is pushed, capped at 1.0 since the corners can go slightly past it
            val power = min(hypot(controlX, controlY), 1.0)

            robot.setDrive(angle, power, turn)

            addRow("Angle", "%.1f°".format(Math.toDegrees(angle)))
            addRow("Power", "%.2f".format(power))
            addRow("Turn", "%.2f".format(turn))
            telemetry.update()
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

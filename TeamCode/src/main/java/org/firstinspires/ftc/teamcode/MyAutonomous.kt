package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime

@Autonomous(name = "MyAutonomous", group = "Autonomous")
class MyAutonomous : LinearOpMode() {

    private val runtime = ElapsedTime()

    override fun runOpMode() {
        // TODO: pull your hardware out of the hardwareMap here, for example
        // val leftDrive = hardwareMap.get(DcMotor::class.java, "leftDrive")

        telemetry.addLine("Initialized - waiting for start")
        telemetry.update()

        waitForStart()
        runtime.reset()

        if (isStopRequested) {
            return
        }

        // TODO: your autonomous routine goes here

        telemetry.addData("Runtime", "%.2f s", runtime.seconds())
        telemetry.update()
    }
}

package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime
import kotlin.math.PI

@Autonomous(name = "MyAutonomous", group = "Autonomous")
class MyAutonomous : LinearOpMode() {

    private val runtime = ElapsedTime()

    override fun runOpMode() {
        val robot = Robot(hardwareMap)
            // TODO: pull your hardware out of the hardwareMap here, for example
        // val leftDrive = hardwareMap.get(DcMotor::class.java, "leftDrive")

        telemetry.addLine("Initialized - waiting for start")
        telemetry.update()

        waitForStart()
        runtime.reset()

        if (isStopRequested) {
            return
        }

        robot.setDrive(1/2*PI, 1.0, 0.0)

        telemetry.addData("Runtime", "%.2f s", runtime.seconds())
        telemetry.update()
    }
}

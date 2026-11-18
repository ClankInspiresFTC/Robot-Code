package org.firstinspires.ftc.teamcode


import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.util.ElapsedTime
//import com.arcrobotics.ftclib.geometry.Pose2d
//import com.arcrobotics.ftclib.geometry.Pose2d

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
        while(opModeIsActive()) {
            for(time in 1..5000){
//                DcMotor.RunMode.STOP_AND_RESET_ENCODER
                robot.setDrive(0.0, 1.0, 0.0)


            }
            println(time/1000)
            if(time>30000){
                break
            }

//          robot.shootingMotor(on, ???)
        }
        telemetry.addData("Runtime", "%.2f s", runtime.seconds())
        telemetry.update()
    }
}

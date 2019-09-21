/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;




@Autonomous(name="AutoWithEncoders", group="Linear Opmode")
//@Disabled
public class AutonomousWithEncoders extends LinearOpMode {


    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor left1;
    private DcMotor left2;
    private DcMotor right1;
    private DcMotor right2;
    private Servo s1;
    private Servo s2;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        left1  = hardwareMap.get(DcMotor.class, "m1");
        left2  = hardwareMap.get(DcMotor.class, "m4");
        right1 = hardwareMap.get(DcMotor.class, "m2");
        right2 = hardwareMap.get(DcMotor.class, "m3");
        s1 = hardwareMap.get(Servo.class, "s1");
        s2 = hardwareMap.get(Servo.class, "s2");


        right1.setDirection(DcMotor.Direction.FORWARD);
        left1.setDirection(DcMotor.Direction.REVERSE);
        right2.setDirection(DcMotor.Direction.FORWARD);
        left2.setDirection(DcMotor.Direction.REVERSE);

        left1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left1.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        left2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right1.setMode((DcMotor.RunMode.RUN_WITHOUT_ENCODER));
        right2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        waitForStart();
        runtime.reset();

        telemetry.addData("Status", "Running");
        telemetry.update();

        left1.setTargetPosition(5000);

        left1.setPower(0.25);
        left2.setPower(0.25);
        right1.setPower(0.25);
        right2.setPower(0.25);

        while(opModeIsActive() && left1.isBusy())
        {
            telemetry.addData("encoder-fwd", left1.getCurrentPosition() + "  busy=" + left1.isBusy());
            telemetry.update();
            idle();
        }

        resetStartTime();

        while (opModeIsActive() && getRuntime() < 5)
        {
            telemetry.addData("encoder-fwd-end", left1.getCurrentPosition() + "  busy=" + left1.isBusy());
            telemetry.update();
            idle();
        }


    }
}

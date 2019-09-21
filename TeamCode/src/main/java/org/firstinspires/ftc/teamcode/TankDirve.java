/**
 ----------------------------------------------------------------------------------------------------
 Name: TankDrive
 Purpose: This program creates an tank drive for the robot


 Author: Baghbanbashi, Parham
 email: parhambagh@gmail.com

 Date: 09/19/2019
 Version: 1.1.1
 ----------------------------------------------------------------------------------------------------
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TankDirve extends OpMode {

    DcMotor m1;
    DcMotor m2;
    DcMotor m3;
    DcMotor m4;
    Servo s1;
    Servo s2;

    public void init()
    {
        m1 = hardwareMap.dcMotor.get("m1");
        m2 = hardwareMap.dcMotor.get("m2");
        m3 = hardwareMap.dcMotor.get("m3");
        m4 = hardwareMap.dcMotor.get("m4");

        s1 = hardwareMap.servo.get("s1");
        s2 = hardwareMap.servo.get("s2");

        telemetry.addData("Status:", "Initialized");

        m1.setDirection(DcMotor.Direction.REVERSE);
        m3.setDirection(DcMotor.Direction.REVERSE);


    }

    public void loop()
    {
        telemetry.addData("Status:", "Running");

        telemetry.addData("Rightmoter", m2.getPower());
        telemetry.addData("Leftmotor", m1.getPower());

        telemetry.addData("servo 1", s1.getPosition());
        telemetry.addData("servo2", s2.getPosition());

        double leftPower = -gamepad1.left_stick_y;
        double rightPower  =  gamepad1.right_stick_y;

        telemetry.addData("Yl", leftPower);
        telemetry.addData("YR", rightPower);

        m1.setPower(leftPower);
        m2.setPower(rightPower);
        m3.setPower(-rightPower);
        m4.setPower(-leftPower);

        if(gamepad1.y == true)
        {
            s1.setPosition(1.0);
            s2.setPosition(-0.5);
        }

        /** set servos positon to 0.5 if button a is presed
         */
        if(gamepad1.a){
            s1.setPosition(-0.5);
            s2.setPosition(1.0);
        }
    }
}

/**
----------------------------------------------------------------------------------------------------
Name: MecanumArcadeDrive
Purpose: This program creates an arcade drive(Mecanum) and adds servo control these servos control
 the robots platform locking mechanism


Author: Baghbanbashi, Parham
        email: parhambagh@gmail.com

Date: 09/21/2019
Version: 1.1.1
----------------------------------------------------------------------------------------------------
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class MecanumArcadeDrive extends OpMode {

    DcMotor m1;
    DcMotor m2;
    DcMotor m3;
    DcMotor m4;
    Servo s1;
    Servo s2;
    boolean arcadedrive = true;
    double xr = -gamepad1.right_stick_x;
    double yr = -gamepad1.right_stick_y;

    /**
     * runs on initilaization
     */
    public void init()
    {
        /**
         * define motors
         */
        m4 = hardwareMap.dcMotor.get("m1");
        m3 = hardwareMap.dcMotor.get("m2");
        m2 = hardwareMap.dcMotor.get("m3");
        m1 = hardwareMap.dcMotor.get("m4");

        /**
         * define servos
         */
        s1 = hardwareMap.servo.get("s1");
        s2 = hardwareMap.servo.get("s2");

        /**
         * print out status
         */
        telemetry.addData("Status:", "Initialized");

        /**
         * set left side motors to reverse
         */

        m4.setDirection(DcMotor.Direction.REVERSE);


    }


    public void arcadeDrive(){
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x;
        // define left and right powers
        double leftPower = Range.clip(drive + turn, -1.0, 1.0) ;
        double rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

        // print out gamepad status
        telemetry.addData("Y", drive);
        telemetry.addData("X", turn);

        /**
         * set power of motors to the gamepad imputs
         */
        m2.setDirection(DcMotor.Direction.REVERSE);
        //front
        m1.setPower(-leftPower);
        m2.setPower(-rightPower);
        //back
        m3.setPower(rightPower);
        m4.setPower(leftPower);

        if(strafe > 0){
            m2.setPower(-strafe);
            m4.setPower(-strafe);

            m1.setPower(strafe);
            m3.setPower(strafe);
        }
        if(strafe < 0 ){
            m2.setPower(strafe);
            m4.setPower(strafe);

            m1.setPower(-strafe);
            m3.setPower(-strafe);
        }
    }





    /**
     * runs repetedly after person hits play
     */
    public void loop()
    {
        // print out status
        telemetry.addData("Status:", "Running");

        /**
         * print out right and left motor power
         */
        telemetry.addData("Rightmoter", m2.getPower());
        telemetry.addData("Leftmotor", m1.getPower());

        //print out servo positons
        telemetry.addData("servo 1", s1.getPosition());
        telemetry.addData("servo2", s2.getPosition());

        telemetry.addData("Arcade drive",arcadedrive);

        if(arcadedrive){
            arcadeDrive();
        }



        /**
        * various difrent servo set position statment
         */

        /**
         * set posion of servos to 0.5 if button y is pressed
         */

        if(gamepad1.right_bumper == true)
        {
            s1.setPosition(1.0);
            s2.setPosition(-0.5);
        }

        /** set servos positon to 0.5 if button a is presed
         */
        if(gamepad1.right_trigger > 0){
            s1.setPosition(-0.5);
            s2.setPosition(1.0);
        }


    }
}

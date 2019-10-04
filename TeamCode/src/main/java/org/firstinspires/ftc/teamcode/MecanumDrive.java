/**
----------------------------------------------------------------------------------------------------
Name: MecanumDrive
Purpose: This program creates an arcade drive(Mecanum) and adds servo control these servos control
 the robots platform locking mechanism


Author: Baghbanbashi, Parham
        email: parhambagh@gmail.com

Date: 10/3/2019
Version: 2.0.0
----------------------------------------------------------------------------------------------------
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import team16488.motors.setpowers.api.driveDistribution;
@TeleOp
public class MecanumDrive extends OpMode {

    DcMotor FrontLeftMotor;
    DcMotor FrontRightMotor;
    DcMotor RearRightMotor;
    DcMotor RearLeftMotor;
    Servo RightPullerServo;
    Servo LeftPullerServo;
    public String Drivemode = "mode1";

    /**
     * runs on initilaization
     */

    public void setLeftSidePower(double power) {
            FrontLeftMotor.setPower(power);
            RearLeftMotor.setPower(power);
    }

    public void setRightSidePower(double power) {
        FrontRightMotor.setPower(power);
        RearRightMotor.setPower(power);
    }

    public void setFrontRightToBottomLeftDiagonalPower(double power){
        FrontRightMotor.setPower(power);
        RearLeftMotor.setPower(power);
    }

    public void setFrontLeftToBottomRightDiagonalPower(double power){
        FrontLeftMotor.setPower(power);
        RearRightMotor.setPower(power);
    }

    public void driveStriaitAndMakeWideTurns(double forwardAndBackValues, double rotationValue){
        double leftPower = Range.clip(forwardAndBackValues + rotationValue, -1.0, 1.0) ;
        double rightPower   = Range.clip(forwardAndBackValues - rotationValue, -1.0, 1.0) ;
        setLeftSidePower(leftPower);
        setRightSidePower(rightPower);
    }

    public void makeTightTurns(double forwardAndBackValues, double rotationValue){
        double rotationLeftPower = rotationValue;
        double rotationRightPower = -rotationValue;
        setRightSidePower(rotationRightPower);
        setLeftSidePower(rotationLeftPower);
    }

    public void driveStraight(double power){
        setLeftSidePower(power);
        setRightSidePower(power);
    }
    public void strafe(double strafeValue){
        setFrontRightToBottomLeftDiagonalPower(-strafeValue);
        setFrontLeftToBottomRightDiagonalPower(strafeValue);

    }




    public void diagonalRightstrafe(double power){
        setFrontLeftToBottomRightDiagonalPower(power);
    }

    public void diagonalLeftstrafe(double power){
        setFrontRightToBottomLeftDiagonalPower(power);
    }

    /**
     * This drive mode is with wide turns and strafing using the arcade drive formula
     */
    public void driveMode1(){
        double forwardAndBackValues = -gamepad1.right_stick_y;
        double rotationValue = -gamepad1.left_stick_x;
        double strafeValue = -gamepad1.right_stick_x;

        driveStriaitAndMakeWideTurns(forwardAndBackValues, rotationValue);
        strafe(strafeValue);

    }

    /**
     * This dirve mode is with tight turns and strafeing(left and right) without the arcade drive formula
     */
    public void driveMode2(){
        double forwardAndBackValues = -gamepad1.right_stick_y;
        double rotationValue = -gamepad1.left_stick_x;
        double strafeValue = -gamepad1.right_stick_x;

        driveStriaitAndMakeWideTurns(forwardAndBackValues, rotationValue);
        strafe(strafeValue);

    }

    /**
     * This Drivemode allows the drivers to select diagonal strafeing as a feature with wide turns
     */
    public void driveMode3(){
        double y = -gamepad1.right_stick_y;
        double rotationValue = -gamepad1.left_stick_x;
        double x = -gamepad1.right_stick_x;

        if(y > 0 || x > 0){
            diagonalRightstrafe(y);
        }
        else if(y < 0 || x < 0){
            diagonalRightstrafe(y);
        }
        else if(x < 0 || y < 0){
            diagonalLeftstrafe(y);
        }
        else if(x > 0 || y < 0){
            diagonalLeftstrafe(y);
        }
        else{
            driveStriaitAndMakeWideTurns(y, rotationValue);
            strafe(x);
        }

    }

    /**
     * This mode is with tight turns and diagonal strafing without the arcade formula
     */
    public void driveMode4(){
        double y = -gamepad1.right_stick_y;
        double rotationValue = -gamepad1.left_stick_x;
        double x = -gamepad1.right_stick_x;

        if(y > 0 || x > 0){
            diagonalRightstrafe(y);
        }
        else if(y < 0 || x < 0){
            diagonalRightstrafe(y);
        }
        else if(x < 0 || y < 0){
            diagonalLeftstrafe(y);
        }
        else if(x > 0 || y < 0){
            diagonalLeftstrafe(y);
        }
        else{
            makeTightTurns(y, rotationValue);
            strafe(x);
            driveStraight(y);
        }
    }

    /**
     * mode selector(drivemode)
     */
     public void setDrivemode(){
         if(gamepad1.y == true){
            Drivemode = "mode1";
            telemetry.addData("Drive Mode",Drivemode);
         }
         if(gamepad1.b == true){
             Drivemode = "mode2";
             telemetry.addData("Drive Mode",Drivemode);
         }
         if(gamepad1.a == true){
             Drivemode = "mode3";
             telemetry.addData("Drive Mode",Drivemode);
         }
         if(gamepad1.x == true){
             Drivemode = "mode4";
             telemetry.addData("Drive Mode",Drivemode);
         }
         else{
             telemetry.addData("Please select a Drive mode"," ");
         }
     }

     public void setpullerServos(){
         if(gamepad1.right_bumper == true)
         {
             RightPullerServo.setPosition(1.0);
             LeftPullerServo.setPosition(-0.5);
         }

         /** set servos positon to 0.5 if button a is presed
          */
         if(gamepad1.right_trigger > 0){
             RightPullerServo.setPosition(-0.5);
             LeftPullerServo.setPosition(1.0);
         }
     }

    /**
     * Initates drive mode
     */
    public void activateDrivemode(){
         if(Drivemode == "mode1"){
             driveMode1();
         }
         else if(Drivemode == "mode2"){
             driveMode2();
         }
         else if(Drivemode == "mode3"){
             driveMode3();
         }
         else if(Drivemode == "mode4"){
             driveMode4();
         }
         else{
             telemetry.addData("Please select a Drive mode"," ");
         }
     }

    /**
     * print out satus
     */
    public void printStatus(){
         telemetry.addData("Game pad 1 stick status:","-------------------------");
         telemetry.addData("Left stick x", gamepad1.left_stick_x);
         telemetry.addData("Right stick y", gamepad1.right_stick_y);
         telemetry.addData("Right stick x", gamepad1.right_stick_x);
         telemetry.addData("Drive Mode","----------------------------------------");
         telemetry.addData("Drive Mode",Drivemode);
     }
    public void init(){
        /**
         * define motors
         */
        FrontLeftMotor = hardwareMap.dcMotor.get("m4");
        FrontRightMotor = hardwareMap.dcMotor.get("m3");
        RearRightMotor = hardwareMap.dcMotor.get("m2");
        RearLeftMotor = hardwareMap.dcMotor.get("m1");

        /**
         * define servos
         */
        RightPullerServo = hardwareMap.servo.get("s1");
        LeftPullerServo = hardwareMap.servo.get("s2");

        /**
         * print out status
         */
        telemetry.addData("Status:", "Initialized");

        /**
         * Reverse Approprate Motors
         */
        FrontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        RearLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Robot Status", "Initalized");
        telemetry.addData("Drive Mode", Drivemode);

    }
    /**
     * runs repetedly after person hits play
     */
    public void loop() {
        telemetry.addData("Robot Status:","Running");
        printStatus();
        setDrivemode();
        activateDrivemode();
        setpullerServos();

    }
}

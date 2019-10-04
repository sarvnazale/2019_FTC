package team16488.motors.setpowers.api;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class driveDistribution {
    DcMotor FrontLeftMotor;
    DcMotor FrontRightMotor;
    DcMotor RearRightMotor;
    DcMotor RearLeftMotor;
    Servo RightPullerServo;
    Servo LeftPullerServo;

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
}

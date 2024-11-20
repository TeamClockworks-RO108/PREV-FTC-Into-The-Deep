package org.firstinspires.ftc.teamcode.movement;

import android.util.Log;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Arrays;
import java.util.List;

public class Movement {
    private static DcMotor leftFront;
    private static DcMotor rightFront;
    private static DcMotor leftRear;
    private static DcMotor rightRear;
    public static void init(HardwareMap hardwareMap){
        leftFront = hardwareMap.get(DcMotor.class, "Motor Front Left");
        rightFront = hardwareMap.get(DcMotor.class, "Motor Front Right");
        leftRear = hardwareMap.get(DcMotor.class, "Motor Rear Left");
        rightRear = hardwareMap.get(DcMotor.class, "Motor Rear Right");
        List<DcMotor> movementMotors = Arrays.asList(leftFront, rightFront, leftRear, rightRear);
        movementMotors.forEach(motor -> motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE));

        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public static void move(Gamepad driverGamepad){
        double power = -driverGamepad.left_stick_y;
        double rotation = driverGamepad.right_stick_x;
        double strafe = driverGamepad.left_stick_x;

        double frontLeftPower = power + rotation + strafe;
        double frontRightPower = power - rotation - strafe;
        double rearLeftPower = power + rotation - strafe;
        double rearRightPower = power - rotation + strafe;
        Log.d("movement","Position: " + rightRear.getCurrentPosition());
        leftFront.setPower(frontLeftPower);
        rightFront.setPower(frontRightPower);
        leftRear.setPower(rearLeftPower);
        rightRear.setPower(rearRightPower);
    }
}

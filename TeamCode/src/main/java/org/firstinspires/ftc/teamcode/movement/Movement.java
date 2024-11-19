package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movement {
    private static DcMotor leftFront;
    private static DcMotor rightFront;
    private static DcMotor leftRear;
    private static DcMotor rightRear;
    public static void init(HardwareMap hardwareMap){
        leftFront = hardwareMap.get(DcMotor.class, "Motor Left Front");
        rightFront = hardwareMap.get(DcMotor.class, "Motor Right Front");
        leftRear = hardwareMap.get(DcMotor.class, "Motor Left Rear");
        rightRear = hardwareMap.get(DcMotor.class, "Motor Right Rear");
        List<DcMotor> movementMotors = Arrays.asList(leftFront, rightFront, leftRear, rightRear);
        movementMotors.forEach(motor -> motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE));
    }
    public static void move(Gamepad driverGamepad){
        double power = -driverGamepad.left_stick_y;
        double rotation = driverGamepad.right_stick_x;
        double strafe = driverGamepad.left_stick_x;

        double frontLeftPower = power + rotation + strafe;
        double frontRightPower = power - rotation - strafe;
        double rearLeftPower = power + rotation - strafe;
        double rearRightPower = power - rotation + strafe;

        leftFront.setPower(frontLeftPower);
        rightFront.setPower(frontRightPower);
        leftRear.setPower(rearLeftPower);
        rightRear.setPower(rearRightPower);
    }
}

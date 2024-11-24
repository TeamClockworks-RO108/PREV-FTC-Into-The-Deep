package org.firstinspires.ftc.teamcode.arm;

import android.util.Log;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {

    private static DcMotor rotationMotor;
    private static DcMotor heightMotor;

    private final static String MOTOR_NAME_R = "Motor Arm Rotation";
    private final static String MOTOR_NAME_H = "Motor Arm Height";
    private static int MAX_POS_H = 4500;
    private static final int REAL_MAX = 4500;
    private static final int LIMITED_MAX = REAL_MAX / 2;

    private final static int MIN_POS_R = -1450;
    private final static int MAX_POS_R = 1350;

    private final static double rotationPower = 0.6;

    private static boolean running = false;
    private static boolean blocked = false;


    public static void init(HardwareMap hardwareMap) {
        rotationMotor = hardwareMap.get(DcMotor.class, MOTOR_NAME_R);
        heightMotor = hardwareMap.get(DcMotor.class, MOTOR_NAME_H);
        heightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        heightMotor.setTargetPosition(0);
        heightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotationMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        heightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rotationMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rotationMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        Log.d("arm", "Init finished");
        adjustPosition();
    }

    public static void extend() {
        Log.d("arm-height", "Current height pos is: " + heightMotor.getCurrentPosition());
        runToLocation(true);
    }

    public static void retract() {
        Log.d("arm-height", "Current height pos is: " + heightMotor.getCurrentPosition());
        runToLocation(false);
    }

    public static void changeMaxHeight(int height) {
        MAX_POS_H = height;
    }

    public static void adjustPosition() {
        if (rotationMotor.getCurrentPosition() < -1200) {
            changeMaxHeight(REAL_MAX);
        } else {
            changeMaxHeight(LIMITED_MAX);
        }
    }

    public static void rotateBwd() {
        Log.d("arm-rot", "Current rotation pos is: " + rotationMotor.getCurrentPosition());
        if (rotationMotor.getCurrentPosition() > MIN_POS_R) {
            rotationMotor.setPower(-rotationPower);
            adjustPosition();
        } else {
            stopRotate();
        }
    }

    public static void rotateFwd() {
        Log.d("arm-rot", "Current rotation pos is: " + rotationMotor.getCurrentPosition());
        if (rotationMotor.getCurrentPosition() < MAX_POS_R) {
            rotationMotor.setPower(rotationPower);
            adjustPosition();
        } else {
            stopRotate();
        }
    }

    public static void stopRotate() {
        rotationMotor.setPower(0);
    }

    public static void stopHeight() {
        if (running) {
            running = false;
            heightMotor.setTargetPosition(heightMotor.getCurrentPosition());
        }
    }

    public static void runToLocation(boolean up) {
        if (up) {
            heightMotor.setTargetPosition(MAX_POS_H);
        } else {
            heightMotor.setTargetPosition(0);
        }
        running = true;
    }

    public static void update() {
        double progress = heightMotor.getCurrentPosition() / (MAX_POS_H * 1.0);
        if (progress > 0.93) heightMotor.setPower(0.4);
        else heightMotor.setPower(1);
    }

    public static void block(){
        blocked = true;
    }
    public static void unblock(){
        blocked = false;
    }
    public static boolean isBlocked(){
        return blocked;
    }
    public static int getCurrentMax(){
        return MAX_POS_H;
    }
    public static void forcePositionHeight(int ticks){
        heightMotor.setTargetPosition(ticks);
    }
    public static DcMotor getHeightMotor(){
        return heightMotor;
    }
}

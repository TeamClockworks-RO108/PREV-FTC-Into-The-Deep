package org.firstinspires.ftc.teamcode.gripper;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Gripper {

    private static Servo gripperServo;
    private static Servo rotationServo;
    private static final int MIN_SERVO_GRIP = 0;
    private static final int MAX_SERVO_GRIP = 1;
    private static final int MIN_SERVO_ROTATION = 0;
    private static final int MAX_SERVO_ROTATION = 1;
    private static boolean isOpen;

    public static void init(HardwareMap hardwareMap){
        gripperServo = hardwareMap.get(Servo.class, "gripper");
        rotationServo = hardwareMap.get(Servo.class, "controlOfRotation");
        isOpen = true;
        gripperServo.setPosition(MIN_SERVO_GRIP);
    }
    public static void release (){
        gripperServo.setPosition(MIN_SERVO_GRIP);
    }
    public static void hold(){
        gripperServo.setPosition(MAX_SERVO_GRIP);
    }
    public static void toogleGripper(){
        if (isOpen){
            hold();
            isOpen = false;
        }else {
            release();
            isOpen = true;
        }
    }
    public static void rotateLeft(double val) {
        double rotPos = rotationServo.getPosition();
        if(rotPos - val < 0){
            rotationServo.setPosition(MIN_SERVO_ROTATION);
        }else{
            rotationServo.setPosition(rotPos - val);
        }
    }
    public static void rotateRight(double val){
        double rotPos = rotationServo.getPosition();
        if (val + rotPos > 1){
            rotationServo.setPosition(MAX_SERVO_ROTATION);
        }else{
            rotationServo.setPosition(rotPos + val);
        }
    }
}
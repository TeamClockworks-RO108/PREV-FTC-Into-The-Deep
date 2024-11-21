package org.firstinspires.ftc.teamcode.gripper;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Gripper {

    private static Servo gripperServo;
    private static Servo rotationServo;
    private static final double MIN_SERVO_GRIP = 0;
    private static final double MAX_SERVO_GRIP = 0.4;
    public static final double MIN_SERVO_ROTATION = 0.175;
    public static final double MAX_SERVO_ROTATION = 0.9;
    private static boolean isOpen;

    public static void init(HardwareMap hardwareMap){
        gripperServo = hardwareMap.get(Servo.class, "Servo Gripper");
        rotationServo = hardwareMap.get(Servo.class, "Servo Rotation");
        isOpen = true;
        gripperServo.setPosition(MIN_SERVO_GRIP);
        rotationServo.setPosition(MIN_SERVO_ROTATION); //tune
    }
    public static void release (){
        gripperServo.setPosition(MIN_SERVO_GRIP);
    }
    public static void hold(){
        gripperServo.setPosition(MAX_SERVO_GRIP);
    }

    public static void toggleGripper(){
        if (isOpen){
            hold();
            isOpen = false;
        }else {
            release();
            isOpen = true;
        }
    }
    public static void rotate(double val) {
        rotationServo.setPosition(val);
    }
}
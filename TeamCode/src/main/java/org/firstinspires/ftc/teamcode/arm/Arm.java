package org.firstinspires.ftc.teamcode.arm;

import android.util.Log;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {

    private static DcMotor rotationMotor;
    private static DcMotor heightMotor;

    private final static String MOTOR_NAME_R="Motor Arm Rotation";
    private final static String MOTOR_NAME_H="Motor Arm Height";
    private final static int MAX_POS_H = 4500;

    private final static int MIN_POS_R= -1450;
    private final static int MAX_POS_R = 1350;


    private final static double kD = 1.0;
    private final static double rotationPower = 0.6;

    private static boolean running= false;


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
    }

    public static void extend(){
       Log.d("arm-height", "Current height pos is: " + heightMotor.getCurrentPosition());
//        double error=MAX_POS_H-heightMotor.getCurrentPosition();
        runToLocation(true);
    }
    public static void retract(){
        Log.d("arm-height", "Current height pos is: " + heightMotor.getCurrentPosition());
//
        runToLocation(false);
    }
    public static void rotateBwd(){
        Log.d("arm-rot", "Current rotation pos is: " + rotationMotor.getCurrentPosition());
         if(rotationMotor.getCurrentPosition()>MIN_POS_R){
            rotationMotor.setPower(-rotationPower);
         }else{
             stopRotate();
         }





    }
    public static void rotateFwd(){
        Log.d("arm-rot", "Current rotation pos is: " + rotationMotor.getCurrentPosition());
          if(rotationMotor.getCurrentPosition()<MAX_POS_R){
            rotationMotor.setPower(rotationPower);
          }else{
              stopRotate();
          }






    }

    public static void stopRotate(){
        rotationMotor.setPower(0);

    }
    public static void stopHeight(){
        if(running) {
            running = false;
            heightMotor.setTargetPosition(heightMotor.getCurrentPosition());
        }

    }

    public static void runToLocation(boolean sus ) {

            if (sus) {

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


}

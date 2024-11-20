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
    private final static int MAX_POS_H = 3000;
    private final static int MAX_POS_R = 2500;
    private final static double kD = 1.0;
    private final static double rotationPower = 0.6;


    public static void init(HardwareMap hardwareMap) {
        rotationMotor = hardwareMap.get(DcMotor.class, MOTOR_NAME_R);
        heightMotor = hardwareMap.get(DcMotor.class, MOTOR_NAME_H);

        heightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rotationMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        heightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rotationMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rotationMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        Log.d("arm", "Init finished");
    }

    public static void extend(){
        Log.d("arm-height", "Current height pos is: " + heightMotor.getCurrentPosition());
//        double error=MAX_POS_H-heightMotor.getCurrentPosition();
        if(heightMotor.getCurrentPosition() < MAX_POS_H){
            heightMotor.setPower(kD);
//            error=MAX_POS_H-heightMotor.getCurrentPosition();
        }else{
            stopHeight();
        }
    }
    public static void retract(){
        Log.d("arm-height", "Current height pos is: " + heightMotor.getCurrentPosition());
//        double error=heightMotor.getCurrentPosition();
        if(heightMotor.getCurrentPosition() > 0){
           heightMotor.setPower(-kD);
//           error=heightMotor.getCurrentPosition();
        }else{
            stopHeight();
        }
    }
    public static void rotateBwd(){
        Log.d("arm-rot", "Current rotation pos is: " + rotationMotor.getCurrentPosition());
         if(rotationMotor.getCurrentPosition()>0){
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
        heightMotor.setPower(0);
    }
}

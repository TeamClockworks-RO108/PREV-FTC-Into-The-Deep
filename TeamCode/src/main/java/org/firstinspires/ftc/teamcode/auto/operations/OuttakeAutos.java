package org.firstinspires.ftc.teamcode.auto.operations;

import android.util.Log;

import org.firstinspires.ftc.teamcode.arm.Arm;
import org.firstinspires.ftc.teamcode.gripper.Gripper;

public class OuttakeAutos {
    public static void basketAuto(){
        try {
            Log.d("auto", "basketAuto() op started");
            Arm.block();
            Arm.forcePositionHeight(Arm.getCurrentMax());
            while (Arm.getHeightMotor().isBusy()) {
                Log.d("auto", "Height motor is busy ascending, current pos: " + Arm.getHeightMotor().getCurrentPosition());
            }
            Gripper.rotate(Gripper.MIN_SERVO_ROTATION);
            Thread.sleep(500);
            Gripper.toggleGripper();
            Thread.sleep(100);
            Gripper.rotate(Gripper.MAX_SERVO_ROTATION);
            Thread.sleep(500);
            Arm.forcePositionHeight(0);
            while (Arm.getHeightMotor().isBusy()) {
                Log.i("auto", "Height motor is busy descending, current pos: " + Arm.getHeightMotor().getCurrentPosition());
            }
            Arm.unblock();
            Log.d("auto", "basketAuto() op finished successfully");
        }catch(InterruptedException e){
            Log.e("auto", "Interrupted exception in basketAuto(), exiting auto operation.");
        }
    }
}

package org.firstinspires.ftc.teamcode.gripper;

import android.util.Log;

public class GripperState {

    private static long gripperState;
    private static long startTime;

    enum State{
        ROTATE_SERVO,
        RELEASE_GRIPPER,
        ROTATE_BACK,
        FINISHED
    }
    private static State stage;


    public static void init(){
        stage = State.ROTATE_SERVO;
        startTime=System.nanoTime();
        Log.d("gripperstate","state init");
    }
    public static long getCurrentTime(){
        return System.nanoTime();
    }
    public static long getDiff(){
        return getCurrentTime() - startTime;
    }
    public static void checkAndUpdate(){
        if(stage == State.ROTATE_SERVO){
            Gripper.rotate(Gripper.MAX_SERVO_ROTATION);
            stage = State.RELEASE_GRIPPER;
            Log.d("gripperstate","state cahnges, servo max rotation");
        }
        if(getDiff() > 5000 && stage == State.RELEASE_GRIPPER){
            Gripper.toggleGripper();
            stage = State.ROTATE_BACK;
            Log.d("gripperstate","state cahnges, servo released");
        }
        if(getDiff() > 6000 && stage == State.ROTATE_BACK){
            Gripper.rotate(Gripper.MIN_SERVO_ROTATION);
            stage = State.FINISHED;
            Log.d("gripperstate","state cahnges, servo min rotation");
        }
    }
    public static boolean startNextState(long delay) {
        if (delay>=getCurrentTime()- startTime){
            return true;
    }
        return false;
    }


}

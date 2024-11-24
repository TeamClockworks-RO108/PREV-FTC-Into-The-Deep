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
        startTime=getCurrentTime();
        Log.d("gripperstate","state init");
    }
    public static long getCurrentTime(){
        return System.nanoTime() / 1000000;
    }
    public static long getDiff(){
        Log.d("time","current time:"+getCurrentTime());
        Log.d("time","start time:"+startTime);
        return getCurrentTime() - startTime;
    }
    public static void checkAndUpdate(){
        if(stage == State.ROTATE_SERVO){
            Gripper.rotate(Gripper.MIN_SERVO_ROTATION);
            stage = State.RELEASE_GRIPPER;
            Log.d("gripperstate","state changes, servo max rotation");
        }
        if(getDiff() > 500 && stage == State.RELEASE_GRIPPER){
            Gripper.toggleGripper();
            stage = State.ROTATE_BACK;
            Log.d("gripperstate","state changes, servo released");
        }
        if(getDiff() > 600 && stage == State.ROTATE_BACK){
            Gripper.rotate(Gripper.MAX_SERVO_ROTATION);
            stage = State.FINISHED;
            Log.d("gripperstate","state changes, servo min rotation");
        }
    }
    public static boolean startNextState(long delay) {
        if (delay>=getCurrentTime()- startTime){
            return true;
    }
        return false;
    }


}

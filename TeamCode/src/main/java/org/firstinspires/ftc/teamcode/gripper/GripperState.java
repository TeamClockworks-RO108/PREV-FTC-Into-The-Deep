package org.firstinspires.ftc.teamcode.gripper;

public class GripperState {

    private static long gripperState;
    private static long startTime;


    public static void init(){
       long startTime=System.nanoTime();

    }
    public static long getCurrentTime(){
        return System.nanoTime();
    }
    public static boolean startNextState(long delay) {
        if (delay>=getCurrentTime()- startTime){
            return true;
    }
        return false;
    }


}

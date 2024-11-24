package org.firstinspires.ftc.teamcode.controller;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.arm.Arm;
import org.firstinspires.ftc.teamcode.gripper.Gripper;
import org.firstinspires.ftc.teamcode.gripper.GripperState;
import org.firstinspires.ftc.teamcode.movement.Movement;

@TeleOp(name = "TeleOp - Main")
public class Controller extends OpMode {
    private boolean holdSquare = false;
    public boolean holdingCircle = false;
    public boolean holdingTriangle=false;
    private boolean gripperState = false;


    @Override
    public void init() {
        Gripper.init(hardwareMap);
        Movement.init(hardwareMap);
        Arm.init(hardwareMap);

    }

    @Override
    public void loop() {
        if(!Arm.isBlocked()) {
            controlGripper(gamepad1);
        }
        controlArm(gamepad1);
        controlMovement(gamepad1);
    }

    public void controlGripper(Gamepad gamepad) {
        if (gamepad.circle && !holdingCircle) {
            holdingCircle = true;
            if (gripperState) {
                Gripper.rotate(Gripper.MAX_SERVO_ROTATION);
            } else {
                Gripper.rotate((Gripper.MIN_SERVO_ROTATION));
            }
            gripperState = !gripperState;
        } else if (!gamepad.circle) {
            holdingCircle = false;
        }

        if (gamepad.cross && !holdSquare) {
            holdSquare = true;
            Gripper.toggleGripper();
        } else if (!gamepad1.cross) {
            holdSquare = false;
        }
        if (gamepad.triangle && !holdingTriangle) {
            GripperState.init();
            holdingTriangle = true;
        } else if (!gamepad.triangle){
            holdingTriangle = false;
        }
        GripperState.checkAndUpdate();
    }

    public void controlMovement(Gamepad gamepad){
        Movement.move(gamepad);
    }
    public void controlArm(Gamepad gamepad){
        if(gamepad.right_trigger > 0){
            Log.d("controls", "Pressed right trigger");
            Arm.extend();
        }else if(gamepad.left_trigger > 0){
            Log.d("controls", "Pressed left trigger");
            Arm.retract();
        }else{
            Arm.stopHeight();
        }
        if(gamepad.right_bumper){
            Log.d("controls", "Pressed right bumper");
            Arm.rotateFwd();
        } else if(gamepad.left_bumper){
            Log.d("controls", "Pressed left bumper");
            Arm.rotateBwd();
        }else{
            Arm.stopRotate();
        }
        Arm.update();
    }
}


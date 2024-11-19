package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.gripper.Gripper;
import org.firstinspires.ftc.teamcode.movement.Movement;

@TeleOp(name = "TeleOp - Main")
public class Controller extends OpMode {
    private boolean holdSquare = false;
    /**
     * <h3>Boolean Array container for DPad holding variables.</h3><br>
     * <b>Idx 0: DPad Left</b><br>
     * <b>Idx 1: DPad Right</b><br>
     * <b>Idx 2: DPad Up</b><br>
     * <b>Idx 3: DPad Down</b>
     */
    private final Boolean[] holdingDPads = {false, false};
    @Override
    public void init() {
        Gripper.init(hardwareMap);
        Movement.init(hardwareMap);
    }

    @Override
    public void loop() {
        controlGripper(gamepad1);
        controlMovement();
    }

    public void controlGripper(Gamepad gamepad){
        if(gamepad.square && !holdSquare){
            holdSquare = true;
            Gripper.toggleGripper();
        }else if(!gamepad1.square){
            holdSquare = false;
        }

        if(gamepad.dpad_left && !holdingDPads[0]){
            holdingDPads[0] = true;
            Gripper.rotateLeft(0.05);
        }else if(gamepad.dpad_left){
            holdingDPads[0] = false;
        }
        if(gamepad.dpad_right && !holdingDPads[1]){
            holdingDPads[1] = true;
            Gripper.rotateRight(0.05);
        }else if(gamepad.dpad_right){
            holdingDPads[1] = false;
        }
    }
    public void controlMovement(){
        Movement.move(gamepad1);
    }
}

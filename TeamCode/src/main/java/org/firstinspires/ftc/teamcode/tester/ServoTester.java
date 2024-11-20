package org.firstinspires.ftc.teamcode.tester;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.function.Supplier;

@TeleOp(name = "Simple Servo Tester")
public class ServoTester extends OpMode {
    private final double LOWER_BOUND = 0.0;
    private final double HIGHER_BOUND = 1.0;
    private final String SERVO_NAME = "_s";
    private Servo servoObj;
    private boolean holdingBtn = false;
    private final Supplier<Boolean> buttonLambda = () -> gamepad1.cross;
    @Override
    public void init() {
        servoObj = hardwareMap.get(Servo.class, SERVO_NAME);
        servoObj.setPosition(LOWER_BOUND);
        Log.d("servo", "Servo init at " + servoObj.getPosition());
    }
    @Override
    public void loop() {
        if(buttonLambda.get() && !holdingBtn){
            holdingBtn = true;
            Log.d("servo", "Servo is now at " + servoObj.getPosition());
            servoObj.setPosition(servoObj.getPosition() == LOWER_BOUND ? HIGHER_BOUND : LOWER_BOUND);
        }else if(!buttonLambda.get()){
            holdingBtn = false;
        }
    }
}

package org.firstinspires.ftc.teamcode.tester;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.function.Supplier;

@TeleOp(name = "Simple Servo Tester")
public class ServoTester extends OpMode {
    private final double LOWER_BOUND = 0.0;
    private final double HIGHER_BOUND = 1.0;
    private final String SERVO_NAME = "_s";
    private final Servo servoObj = hardwareMap.get(Servo.class, SERVO_NAME);
    private final Supplier<Boolean> buttonLambda = () -> gamepad1.cross;
    @Override
    public void init() {
        servoObj.setPosition(LOWER_BOUND);
    }
    @Override
    public void loop() {
        if(buttonLambda.get()){
            servoObj.setPosition(servoObj.getPosition() == LOWER_BOUND ? HIGHER_BOUND : LOWER_BOUND);
        }
    }
}

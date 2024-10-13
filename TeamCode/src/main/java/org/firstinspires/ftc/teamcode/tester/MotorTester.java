package org.firstinspires.ftc.teamcode.tester;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.function.Supplier;

@TeleOp(name = "Simple Motor Tester")
public class MotorTester extends OpMode {
    private final Supplier<Float> powerLambda = () -> gamepad1.left_stick_y;
    private final String MOTOR_NAME = "_m";
    private final DcMotor motorObj = hardwareMap.get(DcMotor.class, MOTOR_NAME);
    private final boolean tickLogs = false;
    @Override
    public void init() {
        motorObj.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {
        if(tickLogs){
            Log.d("motor-tester", String.format("Current tick for %s is %d", MOTOR_NAME,
                    motorObj.getCurrentPosition()));
        }
        motorObj.setPower(powerLambda.get());
    }
}

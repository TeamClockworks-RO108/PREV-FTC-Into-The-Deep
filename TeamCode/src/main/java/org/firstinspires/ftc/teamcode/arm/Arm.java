ublic class Arm {

    private DcMotor rotationMotor;
    private DcMotor heightMotor;

    private final String MOTOR_NAME_R="ARM_rotationMotor";
    private final String MOTOR_NAME_H="ARM_heightMotor";
    private final int MAX_POS_H;
    private final int MAX_POS_R;
    private final double kD= 0.5;
    private final double rotationPower=0.6;


    public Arm(DcMotor rotationMotor, DcMotor heightMotor, String MOTOR_NAME_R, String MOTOR_NAME_H){
        this.rotationMotor=rotationMotor;
        this.heightMotor=heightMotor;
        this.MOTOR_NAME_R=MOTOR_NAME_R;
        this.MOTOR_NAME_H=MOTOR_NAME_H;
    }




    @Override
    public void init(hardwareMap h) {
        rotationMotor = hardwareMap.get(DcMotor.class, MOTOR_NAME_R);
        rotationMotor.setMode(DcMotor.RunMode.RUN_WITH_ENCODER);

        heightMotor = hardwareMap.get(DcMotor.class, MOTOR_NAME_H);
        heightMotor.setMode(DcMotor.RunMode.RUN_WITH_ENCODER);
    }

    public static void extend(){
        double error=MAX_POS_H-heightMotor.getCurrentPosition();
        while(height.getCurrentPosition()<MAX_POS_H){
            heightMotor.setPower(error*kD);
            error=MAX_POS_H-heightMotor.getCurrentPosition();

        }
        
    }
    public static void retract(){
        double error=heightMotor.getCurrentPosition();
        while(heightMotor.getCurrentPosition()>0){
           heightMotor.setPower(-error*kD);
           error=heightMotor.getCurrentPosition();

        }
    }
    public static void rotateBwd(){
         if(rotationMotor.getCurrentPosition()>0){
            rotationMotor.setPower(-rotationPower);
         }

    }
    public static void rotateFwd(){
          if(rotationMotor.getCurrentPosition()<MAX_POS_R){
            rotationMotor.setPower(rotationPower);
          }
    }

  

     
}

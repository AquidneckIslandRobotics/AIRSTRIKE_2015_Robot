package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Chassis extends Subsystem {

//___________________________________________________________________________________________________________________________________________________
//setup
	
	//Motors
	
	Victor leftDrive1 = new Victor(RobotMap.LEFT_DRIVE_1);
	Victor leftDrive2 = new Victor(RobotMap.LEFT_DRIVE_2);
	Victor rightDrive1 = new Victor(RobotMap.RIGHT_DRIVE_1);
	Victor rightDrive2 = new Victor(RobotMap.RIGHT_DRIVE_2);
	Victor hDrive1 = new Victor(RobotMap.H_DRIVE_1);
	Victor hDrive2 = new Victor(RobotMap.H_DRIVE_2);
	
	RobotDrive drive = new RobotDrive(leftDrive1, leftDrive2, rightDrive1, rightDrive2);
	
	//Sensors
	Gyro gyro = new Gyro(RobotMap.GYRO);
	Accelerometer accel = new BuiltInAccelerometer(Accelerometer.Range.k4G);
	PowerDistributionPanel PDP = new PowerDistributionPanel();
	
	
	//Variables
	double steeringCorrectionConst = (0.01);
	
//_____________________________________________________________________________________________________________________________________________________
//methods

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoysticks());
    }
    
 //_____________________________________________________________________________________________________________________________________________________  
 //drive methods
    
    public void driveWithJoysticks(){
    	double left = Robot.oi.getDriverLeftStick();
    	double right = Robot.oi.getDriverRightStick();
    	drive.setSafetyEnabled(false);
    	
    	if(Robot.oi.strafeLeftFull.get()){
    		hDrive1.set(1);
    		hDrive2.set(1);
    	}
    	else if(Robot.oi.strafeLeftHalf.get()){
    		hDrive1.set(.5);
    		hDrive2.set(.5);
    	}
    	else if(Robot.oi.strafeRightFull.get()){
    		hDrive1.set(-1);
    		hDrive2.set(-1);
    	}
    	else if(Robot.oi.strafeRightHalf.get()){
    		hDrive1.set(-.5);
    		hDrive2.set(-.5);
    	}
    	else{
    		hDrive1.set(0);
    		hDrive2.set(0);
    	}
    	
    	drive.tankDrive(left, right);
    	SmartDashboard.putNumber("accelY", accel.getY());
    	SmartDashboard.putNumber("accelX", accel.getX());
    	SmartDashboard.putNumber("accelZ", accel.getZ());
    	SmartDashboard.putNumber("test", PDP.getCurrent(0));
   
    }//end driveWithJoysticks
    
    public void driveStraight(){
    	double power = Robot.oi.getDriverLeftStick();
    	double error = getGyro();
    	drive.tankDrive(power+((steeringCorrectionConst)*error), power-((steeringCorrectionConst)*error));
    	SmartDashboard.putNumber("accelY", accel.getY());
    	SmartDashboard.putNumber("accelX", accel.getX());
    	SmartDashboard.putNumber("accelZ", accel.getZ());
    	
    }//end driveStraight
    
    public void setSpeed(double left, double right){
    	drive.tankDrive(left, right);
    }//end setSpeed
    
    
    public void stopDrive()
    {
    	drive.tankDrive(0, 0);
    }//end stopDrive
   
    
//___________________________________________________________________________________________________________________________________________________   
//turn methods
    
 
//____________________________________________________________________________________________________________________________________________________
//sensor methods
    
   public double getGyro()
   {
   	return gyro.getAngle();
   }//end get gyro
   
   public void resetGyro (){
   	gyro.reset();
   }//end reset gyro
    

    


}


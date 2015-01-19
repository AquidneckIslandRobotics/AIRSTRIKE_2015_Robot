package org.usfirst.frc.team78.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team78.robot.commands.DriveStraightWithJoysticks;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */


public class OI {
	
	public JoystickButton turnLeftButton;
	public JoystickButton DriveStraightCorrectionBtn;
	public Joystick driverStick;
	public Button straight;
	public Button strafeLeftFull;
	public Button strafeLeftHalf;
	public Button strafeRightFull;
	public Button strafeRightHalf;
	
		
    
public OI(){
		driverStick = new Joystick(1);
		
		straight = new JoystickButton(driverStick, 2);
		straight.whileHeld(new DriveStraightWithJoysticks());
		
		strafeLeftFull = new JoystickButton(driverStick, 5);
		strafeLeftHalf = new JoystickButton(driverStick, 7);
		strafeRightFull = new JoystickButton(driverStick, 6);
		strafeRightHalf = new JoystickButton(driverStick, 8);
		

		
	}
	

	
	public double getDriverLeftStick(){
		return driverStick.getY();
	}
	public double getDriverRightStick(){
		return driverStick.getThrottle();
	}
	
	
}


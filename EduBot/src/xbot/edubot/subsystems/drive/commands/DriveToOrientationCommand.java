package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class DriveToOrientationCommand extends BaseCommand{
	
	DriveSubsystem drive;
	double currentDegree;
	double goalRotation;
	double currentError;
	double oldDegree;
	double oldError;
	double velocity;
	double errorVelocity;
	double error1;
	double error2;
	
	@Inject
	public DriveToOrientationCommand(DriveSubsystem driveSubsystem) {
		this.drive = driveSubsystem;
	}
	
	public void setTargetHeading(double heading) {
		// This method will be called by the test, and will give you a goal heading.
		// You'll need to remember this target position and use it in your calculations.
		
		goalRotation= heading;
		
	}
	
	@Override
	public void initialize() {
		// If you have some one-time setup, do it here.		
	
	}

	@Override
	public void execute() {
		// Here you'll need to figure out a technique that:
		// - Gets the robot to turn to the target orientation
		// - Gets the robot stop (or at least be moving really really slowly) at the target position
		
		// How you do this is up to you. If you get stuck, ask a mentor or student for some hints!
		
		
		if (Math.abs(goalRotation)>360){
			goalRotation=goalRotation % 360;
		}
		
			currentError=goalRotation-currentDegree;
			
	if (currentError<-180){
		currentError=360-currentError;
	}
	if(currentError>180){
		currentError=currentError-360;
	}
		
		//velocity=drive.gyro.getYaw()-oldDegree;
		errorVelocity=oldError-currentError;
		
		double power=currentError/13-errorVelocity/2;
		/*if(Math.abs(power)>1){
			power=1;
		}*/
	
		drive.tankDrive(-power, power);
		
		
		/*if (currentError);
		{
			
		}*/
		//oldDegree=drive.gyro.getYaw();
		oldError=currentError;
		currentDegree=drive.gyro.getYaw();
	}
		


	@Override
	public boolean isFinished() {
		// Modify this to return true once you have met your goal, 
		// and you're moving fairly slowly (ideally stopped)
		return (Math.abs(currentError)<0.1 && Math.abs(errorVelocity)<0.1);	

	}
}

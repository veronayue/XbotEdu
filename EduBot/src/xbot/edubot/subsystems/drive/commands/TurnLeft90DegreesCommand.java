package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.rotation.MockHeadingSensor;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class TurnLeft90DegreesCommand extends BaseCommand {
	
	DriveSubsystem drive;
	double startDegree;
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
	public TurnLeft90DegreesCommand(DriveSubsystem driveSubsystem) {
		this.drive = driveSubsystem;
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		startDegree=drive.gyro.getYaw();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		/*error1=goalRotation-drive.gyro.getYaw();
		error2=360-error1;
		if (Math.abs(error1)>Math.abs(error2)){
			currentError=Math.abs(error2);
		} else{
			currentError=Math.abs(error1);
		}*/
		
		goalRotation=startDegree+90;
		if (Math.abs(goalRotation)>180){
			goalRotation=goalRotation-360;
		}
		
			currentError=Math.abs(goalRotation-currentDegree);
		
		
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



}

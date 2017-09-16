package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.common.controls.sensors.DistanceSensor;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class DriveToPositionCommand extends BaseCommand {

	DriveSubsystem drive;
	double goal;
	double error;
	boolean isErrorZero;
	double oldPosition;
	double velocity;
	double errorVelocity;
	double oldError;
	
	
	@Inject
	public DriveToPositionCommand(DriveSubsystem driveSubsystem) {
		this.drive = driveSubsystem;
	}
	
	public void setTargetPosition(double position) {
		// This method will be called by the test, and will give you a goal distance.
		// You'll need to remember this target position and use it in your calculations.
		goal= position;
	}
	
	@Override
	public void initialize() {
		// If you have some one-time setup, do it here.

		
		//error
	}

	@Override
	public void execute() {
		// Here you'll need to figure out a technique that:
		// - Gets the robot to move to the target position 
		// - Hint: use drive.distanceSensor.get() to find out where you are
		// - Gets the robot stop (or at least be moving really really slowly) at the target position
		
		//drive.tankDrive(1, 1);
		error=goal-drive.distanceSensor.getDistance();
		
		/*if (error<0){
			isErrorZero=true;
			}
			else{
			isErrorZero=false;
			}*/
		
		/*
		if (error>0.28){
				drive.tankDrive(1,1);	
			}
		if(error>0.0784 && error<=0.28){	
		drive.tankDrive(0.3, 0.3);
		}
		if(error>0.0003 && error<=0.0784){	
			drive.tankDrive(0.1, 0.1);
			}
			
		if(error<0){
		drive.tankDrive(-0.3, -0.3);
		}*/
	velocity=drive.distanceSensor.getDistance()-oldPosition;

	errorVelocity=error-oldError;
	
		
	double power=error*1+errorVelocity*5;
	drive.tankDrive(power, power);
	
		
		
		/*while(error>0){
			while (error>0.0784){
				while (error>0.28){
					drive.tankDrive(1, 1);
				}
				drive.tankDrive(0.5, 0.5);
			}
			drive.tankDrive(0, 0);
		}*/
			
		//for(error=0.28;error>=0;error--);
			
			oldPosition=drive.distanceSensor.getDistance();
			oldError=error;
		}
		
				
		// How you do this is up to you. If you get stuck, ask a mentor or student for some hints!
	
	@Override
	public boolean isFinished() {
		// Modify this to return true once you have met your goal, 
		// and you're moving fairly slowly (ideally stopped)
		return (Math.abs(error)<0.1 && Math.abs(velocity)<0.1);	
		
	}

}

package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.operator_interface.OperatorInterface;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class ArcadeDriveWithJoysticksCommand extends BaseCommand {

	DriveSubsystem drive;
	OperatorInterface operate; 
	
	@Inject
	public ArcadeDriveWithJoysticksCommand(DriveSubsystem driveSubsystem,OperatorInterface oi) {
		drive = driveSubsystem;
		operate = oi;
		
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		double leftValue = operate.leftJoystick.getVector().y+operate.leftJoystick.getVector().x;

		double rightValue =operate.leftJoystick.getVector().y-operate.leftJoystick.getVector().x;
	
		drive.tankDrive(leftValue, rightValue);
			
		if(Math.abs(leftValue)>1){
			rightValue=rightValue/Math.abs(leftValue);
			leftValue= leftValue/Math.abs(leftValue);
		}
		if(Math.abs(rightValue)>1){
			leftValue=leftValue/Math.abs(rightValue);
			rightValue=rightValue/Math.abs(rightValue);
			
		}
		
	/*	if(leftValue>1)	{
			rightValue=rightValue/leftValue;
			leftValue=1;
		}
		if(leftValue<-1) {
			rightValue=rightValue/-leftValue;
			leftValue=-1;
		} */
	}

	private int abs(double leftValue) {
		// TODO Auto-generated method stub
		return 0;
	}

}

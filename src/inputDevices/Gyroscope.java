package inputDevices;

import auto.AutoOutputs;
import auto.GyroControl;
import auto.SuperClass;

public class Gyroscope extends SuperClass{
	private double targetAngle;
	
	GyroControl myGyro;
	
	public Gyroscope(double value, int channel){
		targetAngle = value;
		myGyro = new GyroControl(channel);
	}
	
	@Override
	public void start(){
		myGyro.calibrate();
	}
	
	@Override 
	public boolean check(){
		System.out.println("Gyro Angle: " + myGyro.getAngle());
		
//		if (targetAngle/2<=myGyro.getAngle()){
		AutoOutputs.rampTurn(targetAngle-myGyro.getAngle(), targetAngle);
//		}
		
		//stop once it hits the target angle and its not moving fast
		if(targetAngle>0 && myGyro.getAngle() >= targetAngle && myGyro.getRate() < 10){return true;}
		else if(targetAngle<0 && myGyro.getAngle() <= targetAngle){return true;}
		return false;
	}
}

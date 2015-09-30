package busInterface;

public interface DriverInput_Out {
	
	public void setSteeringWheelAngle(int steeringWheelAngle);
	public void setSteeringWheelMaxAngle(int steeringWheelMaxAngle);
	public void setBrakePedalAngle(int breakPedalAngle);
	public void setBrakePedalMaxAngle(int breakPedalMaxAngle);
	public void setGasPedalAngle(int gasPedalAngle);
	public void setGasPedalMaxAngle(int gasPedalMaxAngle);
	public void setCurrentGear(int currentGear);
	public void setMaxGear(int maxGear);
	public void setShiftLeverPosition(int shiftLeverPosition);
	
}

package busInterface;

public interface Wheels_Out {
	void setWheelTorqueInNewton(double newTorque);
	void setMaximumWheelTorqueInNewton(double newTorque);
	
	void setMaximumBrakeTorqueInNewton(double newTorque);
	void setFrictionalCoefficientOfBrakes(double coefficient);
	
	void setDiameterOfDriveAxesInMeters(double diameter);
	void setLengthOfAxesInMeters(double length);
	void setDistanceBetweenAxesInMeters(double distance);
	
	void setDiameterOfWheelsInMeters(double diameter);
	void setWidthOfWheelsInMeters(double width);
	
	void setDriveWheelStateZeroBasedDegree(double degree);
	void setMaximumDriveWheelStateZeroBasedDegree(double degree);
	void setMaximumWheelsTurnDegree(double degree);

	void setTotalMassInKg(double newMass);
	void setInnerFrictionalCoefficientInNewton(double coefficient);
}

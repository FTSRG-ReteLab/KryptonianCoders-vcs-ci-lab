package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

	private int step = 5;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	public Tachograph tachograph = new Tachograph();
	

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
			referenceSpeed += step;
		}

		enforceSpeedLimit();
	}

	@Override
	public boolean isEmpty() {
		return tachograph.table.isEmpty();
	}
	
	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;
		tachograph.table.put(System.currentTimeMillis() , joystickPosition, referenceSpeed);
	}

	@Override
	public void breaking() {
		setSpeedLimit(0);
	}

}

package dev.ap;

import java.util.Random;

public class IntegrationCalculator extends Thread {
	private final double fMax;
	private final double xMax;
	private final int iterCount;
	private final IntegrationFunction integrationFunc;
	public boolean isFinished = false;
	
	public IntegrationCalculator(double fMax, double xMax, int iterCount,
			IntegrationFunction integrationFunc) {
		this.fMax = fMax;
		this.xMax = xMax;
		this.iterCount = iterCount;
		this.integrationFunc = integrationFunc;
	}
	
	@Override
	public void run() {
		Random randGen = new Random(Double.doubleToLongBits(System.currentTimeMillis()));
		for(int i=0; i<this.iterCount; i++){
			double xR = randGen.nextDouble() * xMax;
			double fR = randGen.nextDouble() * fMax;
			
			if(this.integrationFunc.isPointAccepted(fR, xR))
				IntegrationMain.incrementAccepted();
		}
		
		System.out.println("thread finished");
		isFinished = true;
	}

}

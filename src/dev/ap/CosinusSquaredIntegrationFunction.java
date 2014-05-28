package dev.ap;

public class CosinusSquaredIntegrationFunction implements IntegrationFunction {

	public CosinusSquaredIntegrationFunction() {
	}
	
	//Cos^2 x
	@Override
	public boolean isPointAccepted(double fR, double xR) {
		//System.out.println("cos res:"+(Math.cos(xR) * Math.cos(xR)));
		return fR <= (Math.cos(xR) * Math.cos(xR));
	}

}

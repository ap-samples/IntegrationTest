package dev.ap;

public class IntegrationMain {
	private static final int THREAD_COUNT = 5;
	private static int nAccepted = 0;
	
	public static final int TRIAL_COUNT = 1000000;
	
	public static synchronized void incrementAccepted(){
		nAccepted++;
	}
	
	public IntegrationMain() {	
	}

	public static void main(String[] args) throws InterruptedException {
		double fMax = 1;
		double xMax = Math.PI;
		
		IntegrationCalculator[] calcThreads = new IntegrationCalculator[THREAD_COUNT];
		
		int iterCountPerWorker = TRIAL_COUNT/THREAD_COUNT;
		for(int i=0; i<THREAD_COUNT; i++){
			calcThreads[i] = new IntegrationCalculator(fMax, xMax, iterCountPerWorker,
					new CosinusSquaredIntegrationFunction());
			calcThreads[i].start();
		}
		
		boolean isFinished = false;
		while(!isFinished){
			boolean tmpFinished = true;
			for(int j=0; j<THREAD_COUNT; j++){
				tmpFinished &= calcThreads[j].isFinished;
			}
			
			if(tmpFinished)
				isFinished = true;
			Thread.sleep(1);
		}
		
		System.out.println("acc:"+nAccepted+" fMax:"+fMax+" xMax:"+xMax);
		
		double res = (nAccepted/(double)TRIAL_COUNT)*(fMax*xMax);
		System.out.println("res:"+res);
	}
}

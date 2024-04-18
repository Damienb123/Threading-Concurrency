//import necessary classess for the using thread pools and executors
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
 * the Future interface represents the result of an asynchronous computation
 * it provides methods to check if the computation is complete,
 * retrieve the result of the computation, and cancel the computation if desired
 * */
import java.util.concurrent.Future;

/*
 * the TimeUnit enum provides units of time for specifying timeouts and
 * durations in concurrent programming
 * */
import java.util.concurrent.TimeUnit;


//class Box
public class Box {
	//instance variable of value
	int value = 5;
	
	//creating a thread pool with Executor.newCachedThreaPool()
	//creates new threads as needed, but reuse previously created threads when available
	ExecutorService executor = Executors.newCachedThreadPool();
	
	//delayedIncrement simulates an operation that increments the 'value' after a delay
	//ensuring that only one thread can access these methods at a time
	//preventing concurrent modifications to the 'value' variable
	public synchronized void delayedIncrement() {
		//prints indicating the beginning of the incrementation operation
		System.out.println("[Box.increment] begin increment");
		//storing the current value of 'value' in a local variable currentValue
		int currentValue = value;
		// wait for current value instruction
		
		//enters the try-catch block for exception handling
		//submitting a task for execution using submit()
		Future <?> future = executor.submit(() -> {
		try {
			
			Thread.sleep((long) Math.round(50));
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		});	
			try {
				
				//waiting for the task to complete using get()
				future.get();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			//synchronize the access and update of 'value'
			synchronized (this) {
				value = currentValue + 35;
			}
			System.out.println("[Box.increment] end increment");
			
		
		
	
		
	}
	//simply returns the current value of 'value' instance variable
	public synchronized int getValue() {
		return value;
	}
	
	public void executeTask(Runnable task) {
		executor.execute(task);
	}
	
	public void shutdownExecutor() {
		executor.shutdown();
	}
	
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException{
		return executor.awaitTermination(timeout, unit);
	}
	
	public boolean isExcutedTerminated() {
		return executor.isTerminated();
	}
	
	
	//main body of execution
	public static void main(String []args) {
		//create the instance of box
		Box myBox = new Box();
		
		//get the initial value
		System.out.println("Initial value: " + myBox.getValue());
		
		//perform the delayed incrementation
		myBox.delayedIncrement();
		
		//after the delay, update the value for the Box
		System.out.println("Value after delayed increment: " + myBox.getValue());
		
		//shutdown executor
		myBox.shutdownExecutor();
		
		try {
			//wait for executor termination
			if(myBox.awaitTermination(5,  TimeUnit.MILLISECONDS)) {
				System.out.println("Executor terminated successfully.");
				
			}else {
				System.out.println("Excutor termination timeout occurred.");
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
							
}

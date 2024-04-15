/*
 * The thread class and its methods:
 * - Thread(String name) - creates a Thread instance with a given name
 * - Thread(Runnable target) - creates a Thread from an instance of a
 * class that implements the Runnable interface
 * - getName() - returns the name of the thread
 * - run() - method to be runned concurrently. Override this method
 * with the work that you want performed
 * - start() - instructs the JVM to execute the run method concurrently.
 * This method often returns before the run method has terminated
 * */

/*
 * the Runnable interface -
 * A class implements the Runnable interface if it has a run method
 * that takes no arguments and returns type void*/

//extends the thread class -  public class ThreadExample1 extends Thread{}
//implements the Runnable interface class - public class ThreadExample1 implements Runnable{}
public class ThreadExample1 extends Thread {
	
	//constructor takes the name
	public ThreadExample1(String name) {
		//used for the Thread class constructor
		super(name);
		
		//used for the Runnable interface add bottom code for to the constructor
		// -- this.name = name; --
	}
		// Put your code in the run method
		//@Override is needed for the thread class
		@Override
		public void run() {
		for (int i = 0; i < 10; i++) {
		System.out.println(getName() + ": " + i); //getName() - used for the Thread class
		// -- System.out.println(name + ": " + i); -- used for the Runnable Interface
		}
	}
		public static void main(String[] args) {
		ThreadExample1 t1 = new ThreadExample1("first");
		ThreadExample1 t2 = new ThreadExample1("second");
		ThreadExample1 t3 = new ThreadExample1("third");
		ThreadExample1 t4 = new ThreadExample1("fourth");
		//threads to be ran 
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		//for the Runnable interface
		//runs the threads in the needed order
		
		// -- new Thread(t1).start(); --
		// -- new Thread(t2).start(); --
		// -- new Thread(t3).start(); --
		// -- new Thread(t4).start(); --
	
		}
	}



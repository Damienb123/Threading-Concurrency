

public class ThreadExample2 implements Runnable{
	String name;
	
	public ThreadExample2(String name) {
		this.name = name;
	}
	
	//put code into the run method
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(name + ": " + i);
		}
	}
	
	public static void main(String[] args) {
		ThreadExample2 t1 = new ThreadExample2("first");
		ThreadExample2 t2 = new ThreadExample2("second");
		ThreadExample2 t3 = new ThreadExample2("third");
		ThreadExample2 t4 = new ThreadExample2("fourth");
		new Thread(t1).start();
		new Thread(t2).start();
		new Thread(t3).start();
		new Thread(t4).start();
	}
}

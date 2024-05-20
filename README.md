# Threading-Concurrency

## Table of Contents

1. [Overview](#Overview)
2. [Project Structure](#Project-Structure)
3. [Purpose and Functionality](#Purpose-and-Functionality)
    - Box
    - ThreadExample1
    - ThreadExample2
4. [Usage Instructions](#Usage-Instructions)
5. [Compilation and Execution](#Compilation-and-Execution)
6. [Example Output](#Example-Output)

## Overview

This project demonstrates the use of threading and concurrency in Java by leveraging thread pools, the **ExecutorService**, and the **Runnable** interface. It includes examples of creating and managing threads, performing delayed operations, and handling concurrent tasks safely.

## Project Structure

- Box.java: Demonstrates the use of an ExecutorService for managing threads and performing delayed operations.
- ThreadExample1.java: Demonstrates extending the Thread class to create and manage threads.
- ThreadExample2.java: Demonstrates implementing the Runnable interface to create and manage threads.

## Purpose and Functionality

### Box

The **Box** class demonstrates the use of a thread pool and synchronized methods to manage a shared resource (**value**) safely.
```
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Box {
    int value = 5;
    ExecutorService executor = Executors.newCachedThreadPool();

    public synchronized void delayedIncrement() {
        System.out.println("[Box.increment] begin increment");
        int currentValue = value;

        Future<?> future = executor.submit(() -> {
            try {
                Thread.sleep((long) Math.round(50));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        synchronized (this) {
            value = currentValue + 35;
        }
        System.out.println("[Box.increment] end increment");
    }

    public synchronized int getValue() {
        return value;
    }

    public void executeTask(Runnable task) {
        executor.execute(task);
    }

    public void shutdownExecutor() {
        executor.shutdown();
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return executor.awaitTermination(timeout, unit);
    }

    public boolean isExecutorTerminated() {
        return executor.isTerminated();
    }

    public static void main(String[] args) {
        Box myBox = new Box();

        System.out.println("Initial value: " + myBox.getValue());

        myBox.delayedIncrement();

        System.out.println("Value after delayed increment: " + myBox.getValue());

        myBox.shutdownExecutor();

        try {
            if (myBox.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("Executor terminated successfully.");
            } else {
                System.out.println("Executor termination timeout occurred.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

### ThreadExample1
The **ThreadExample1** class demonstrates how to create and run multiple threads by extending the **Thread** class.
```
public class ThreadExample1 extends Thread {
    public ThreadExample1(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + ": " + i);
        }
    }

    public static void main(String[] args) {
        ThreadExample1 t1 = new ThreadExample1("first");
        ThreadExample1 t2 = new ThreadExample1("second");
        ThreadExample1 t3 = new ThreadExample1("third");
        ThreadExample1 t4 = new ThreadExample1("fourth");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
```

### ThreadExample2
The **ThreadExample2** class demonstrates how to create and run multiple threads by implementing the Runnable interface.
```
public class ThreadExample2 implements Runnable {
    String name;

    public ThreadExample2(String name) {
        this.name = name;
    }

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
```

## Usage Instructions

1. **Compile the Java files:** Ensure you have the Java Development Kit (JDK) installed.

2. **Run the main methods:**  Each class (**Box**, **ThreadExample1**, **ThreadExample2**) has a **main** method that demonstrates its functionality.

## Compilation and Execution

To compile and run the Java files, follow these steps:

1. **Compile the classes:**
```
javac Box.java
javac ThreadExample1.java
javac ThreadExample2.java
```

2. **Run the main methods:**
```
java Box
java ThreadExample1
java ThreadExample2
```

## Example Output

### Box
```
Initial value: 5
[Box.increment] begin increment
[Box.increment] end increment
Value after delayed increment: 40
Executor terminated successfully.
```

### ThreadExample1
```
Initial value: 5
[Box.increment] begin increment
[Box.increment] end increment
Value after delayed increment: 40
Executor terminated successfully.
```

### TheadExample2
```
first: 0
second: 0
third: 0
fourth: 0
first: 1
second: 1
third: 1
fourth: 1
...
```

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import static java.lang.Integer.parseInt;

class generatorThread extends Thread{
    public void start(){
    }

    public int generateNumber(int interval){
        Random rando = new Random();
        int  n = rando.nextInt();

        System.out.println("Thread " + Thread.currentThread().getId() + ": Value " + n);
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return n;
    }
}

public class JavaThreads {

    public static void main(String[] args) {

        Queue<Integer> numberQueue = new LinkedList<>();
        //Queue<Thread> threadQueue = new LinkedList<>();

        // Adds elements to queue
        for (int i=0; i<args.length; i++)
            numberQueue.add(parseInt(args[i]));

        generatorThread myThread = new generatorThread();
        myThread.start();

        for(int i=0; i<5; i++)
            numberQueue.add(myThread.generateNumber(parseInt(args[0])));

        // Display contents of the queue.
        System.out.println("Elements of queue: " + numberQueue);
        //System.out.println(q);

        //int removedele = q.remove();
        //System.out.println("removed element: " + removedele);

        // To view the head of queue
        int head = numberQueue.peek();
        System.out.println("head of queue: " + head);

        int size = numberQueue.size();
        System.out.println("Size of queue: " + size);

    }
}
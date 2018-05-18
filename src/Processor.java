import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Processor {
    private Queue<Integer> numberQueue = new LinkedList<>();
    private final int LIMIT = 1;
    private Object lock = new Object();

    public void produce( int interval) throws InterruptedException{

        //generate the first random number
        Random rando = new Random();
        int  value = rando.nextInt();

        while(true){
            synchronized (lock){

                while(numberQueue.size() == LIMIT){
                    lock.wait();
                }

                numberQueue.add(value);
                value = rando.nextInt();
                lock.notify();
            }

            // produce numbers at this rate
            Thread.sleep(interval);
        }
    }
    public void consume() throws InterruptedException{
        while(true){
            synchronized (lock){

                while(numberQueue.size() == 0){
                    lock.wait();
                }

                System.out.print("Thread #"+ Thread.currentThread().getId());
                int value = numberQueue.remove();
                System.out.println(" removed: " + value);
                Thread.sleep(20);
                lock.notify();
            }
        }
    }
}



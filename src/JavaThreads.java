import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class JavaThreads {

    public static void main(String[] args) {

        //Check arguments
        numArgs(args.length);
        checkArgs(args[0]);
        checkArgs(args[1]);

        int MAX_THREADS = parseInt(args[0]);
        int interval = parseInt(args[1]);

        Queue<Thread> threadQueue = new LinkedList<>();
        final Processor processor = new Processor();

         Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

         //Create pool of threads and add them to queue

         for(int i=0; i<MAX_THREADS;i++){
             threadQueue.add( new Thread(new Runnable() {
                 @Override
                 public void run() {
                     try {
                         processor.consume();
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
             }));
             ((LinkedList<Thread>) threadQueue).getLast().start();
         }

         System.out.println("Thread Pool: " + MAX_THREADS);
         System.out.println("Interval   : " + interval);

         producer.start();

    }

    private static void numArgs(int size){
        if(size!=2){
            System.out.println("Invalid number of arguments!");
            System.out.println("Please use <numThreads> <interval> as inputs");
            System.exit(0);
        }
    }

    private static void checkArgs(String input) {

        try{
            parseInt(input);
        } catch (NumberFormatException e){
            System.out.println(input + " is an invalid input");
            System.out.println("Please use <numThreads> <interval> as inputs");
            System.exit(0);
        }

        if(!(parseInt(input)>0)) {
            System.out.println("Inputs must be greater than 0");
            System.exit(0);
        }


    }
}

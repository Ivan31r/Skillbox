package Transactions;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CreateThread {
    public static void initAndStartThreads(Runnable runnable){
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        for (int i =0;i<1000;i++){
            executorService.submit(runnable);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

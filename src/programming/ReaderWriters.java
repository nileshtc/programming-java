package programming;

import java.io.*;
import java.util.concurrent.Semaphore;

public class ReaderWriters {

    static int RC = 0;                                              //Read counter
    static Semaphore ReadLock = new Semaphore(1);                   //Semaphore to obtain read load
    static Semaphore WriteLock = new Semaphore(1);                  //Semaphore to obtain write lock

    public static void main(String[] args) throws Exception 
    {
        Read read = new Read();
        Write write = new Write();
        
        //Create threads 
        Thread t1 = new Thread(read);
        Thread t2 = new Thread(read);
        Thread t3 = new Thread(write);
        Thread t4 = new Thread(read);
        
        //Name threads
        t1.setName("#1");
        t2.setName("#2");
        t3.setName("#3");
        t4.setName("#4");
        
        //Begin threads
        t1.start();
        t3.start();
        t2.start();
        t4.start();

    }
    
    static class Read implements Runnable 
    {
        public void run() 
        {
            try 
            {
                //Acquire lock
                ReadLock.acquire();
                RC++;
                if (RC == 1) 
                {
                    WriteLock.acquire();
                }
                ReadLock.release();

                //Read 
                System.out.println("Thread number "+Thread.currentThread().getName() + " has started reading");
                Thread.sleep(1500);
                System.out.println("Thread number "+Thread.currentThread().getName() + " is done reading");

                //Release lock
                ReadLock.acquire();
                RC--;
                if(RC-- == 0) 
                {
                    WriteLock.release();
                }
                ReadLock.release();
            } 
            catch (InterruptedException e) 
            {
                System.out.println(e.getMessage());
            }
        }
    }

    static class Write implements Runnable 
    {
        public void run() 
        {
            try 
            {
                WriteLock.acquire();
                System.out.println("Thread number "+Thread.currentThread().getName() + " has started writing");
                Thread.sleep(2500);
                System.out.println("Thread number "+Thread.currentThread().getName() + " is done writing");
                WriteLock.release();
            } 
            catch (InterruptedException e) 
            {
                System.out.println(e.getMessage());
            }
        }
    }
}
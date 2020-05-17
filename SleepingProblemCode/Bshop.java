package SleepingProblem;




import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

class Bshop
{

	int noOfChair;
	Scanner sn = new Scanner(System.in);
	ArrayBlockingQueue<Object> abq;
	
 
    public Bshop()
    {

    	System.out.println("Enter number of chairs: ");
    	noOfChair = sn.nextInt();
    	abq = new ArrayBlockingQueue<Object>(noOfChair);
    }
 
    public void cutHair() throws InterruptedException
    {
        Customer customer;
        
        System.out.println("Barber "+Thread.currentThread().getId() +" waiting for lock.");
        synchronized (abq)
        {
 
            while(abq.size()==0)
            {
                System.out.println("Barber "+Thread.currentThread().getId() +"  is waiting for customer.");
                try
                {
                    abq.wait();
                }
                catch(InterruptedException iex)
                {
                    iex.printStackTrace();
                }
            }
           
            customer = (Customer)(abq).poll();
            System.out.println("Barber "+Thread.currentThread().getId() +"  found a "+ customer.getName() +" in the queue.");
        }
        

        long duration=0;
        String nameOfCust = customer.getName();
        double mean = 15, std = 3;
    	Random ran = new Random();
    	long c = Thread.currentThread().getId();
        System.out.println("Cuting hair of  "+nameOfCust+" by barber "+Thread.currentThread().getId());
        duration = Math.round((mean + std*ran.nextGaussian())*100.0/100.0);
        TimeUnit.SECONDS.sleep(duration);

        
        
        System.out.println("Completed Cuting hair of Customer : "+ nameOfCust + " in "+duration+ " seconds by Barber "+c);
        
    }
    public void add(Customer customer)
    {
        System.out.println("Customer : "+customer.getName()+ " entering the shop at "+customer.getInTime());
 
        synchronized (abq)
        {
            if(abq.size() == noOfChair)
            {
                System.out.println("No chair available for customer "+customer.getName());
                System.out.println("Customer "+customer.getName()+"Exists...");
                return ;
            }
 
            (abq).offer(customer);
            System.out.println("Customer : "+customer.getName()+ " got the chair.");
             
            if(abq.size()==1)
                abq.notify();
        }
    }
}


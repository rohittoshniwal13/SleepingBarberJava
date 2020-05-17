package SleepingProblem;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SleepingBarber {
	
	
 
    public static void main(String a[])
    {	
    	Bshop shop = new Bshop();
    	Scanner sn = new Scanner(System.in);
    	
    	System.out.println("Enter number of barbers: ");
        int noOfBarbers = sn.nextInt();
        Barber barber = new Barber(shop);
        ExecutorService executor = Executors.newFixedThreadPool(noOfBarbers);
        for(int i=0; i<noOfBarbers;i++) 
        {
        	executor.submit(barber);
        }
        
        System.out.println("Enter number of customers: ");
        int noOfCust = sn.nextInt();
        CustomerGenerator cg = new CustomerGenerator(shop,noOfCust);
        
       
        
       
        Thread thcg = new Thread(cg);
        thcg.start();
        
        
        
        
   
        sn.close();

        
    }
}


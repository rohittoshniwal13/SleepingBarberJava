package SleepingProblem;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

class CustomerGenerator implements Runnable
{
    Bshop shop;
    int noOfCust;
    
 
    public CustomerGenerator(Bshop shop, int noOfCust)
    {
        this.shop = shop;
        this.noOfCust=noOfCust;
        
       
    }
    
    
 
    public void run()
    {
    	double[] listCustomer = new double[noOfCust];
    	double mean = 15, std = 3;
    	Random ran = new Random();
    	Customer customer = new Customer(shop);
        for(int i=0;i<noOfCust;i++)
        {
 
        	
            customer.setInTime(new Date());
            Thread thcustomer = new Thread(customer);
            listCustomer[i] = Math.round((mean + std*ran.nextGaussian())*100.0/100.0);
            customer.setName("Customer Thread "+thcustomer.getId());
            thcustomer.start();
            
 
            try
            {
                TimeUnit.SECONDS.sleep((long)listCustomer[i]);
            }
            catch(InterruptedException iex)
            {
                iex.printStackTrace();
            }
        }
    }
 
}




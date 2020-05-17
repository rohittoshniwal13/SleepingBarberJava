package SleepingProblem;


class Barber implements Runnable
{
    Bshop shop;
    
    
 
    public Barber(Bshop shop)
    {
        this.shop = shop;
        
    }
    public void run()
    
    {
    	
        try
        {
        	
            Thread.sleep(10000);
        }
        catch(InterruptedException iex)
        {
            iex.printStackTrace();
        }
        System.out.println("Barber "+ Thread.currentThread().getId() +" started..");
        while(true)
        {
            try {
				shop.cutHair();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}

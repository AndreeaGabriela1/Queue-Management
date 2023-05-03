package Model;

import Model.Client;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queues implements Runnable
{
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;
    public Queues()
    {
        ///initializeaza coasa si waitingPeriod
        clients = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);
    }
    public void addClient(Client newClient)
    {
        ///adauga clientul la coada
        ///incrementeaza waitingPeriod
        clients.add(newClient);
        waitingPeriod.addAndGet(newClient.getServiceTime());
    }
    public void run()
    {
        while (true)
        {
            synchronized (clients)
            {
                if(!clients.isEmpty())
                {
                    try
                    {
                        Client currentClient = clients.peek();
                        Thread.sleep(1000);
                        waitingPeriod.addAndGet(-1);
                        currentClient.setServiceTime(currentClient.getServiceTime()-1);
                        if(currentClient.getServiceTime()==0)
                        {
                            clients.take();
                        }

                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
    public int getSize()
    {
        int cnt = 0;
        for(Client c:clients)
        {
            if(!clients.isEmpty())
                cnt++;
        }
        return cnt;
    }
    public BlockingQueue<Client> getClients()
    {
        return clients;
    }
    public int getWaitingTime()
    {
        return waitingPeriod.get();
    }
    public double getExpectedWaitingTime()
    {
        int waitingTime = this.getWaitingTime();
        int queueSize = clients.size();
        if (queueSize == 0) {
            return 0.0;
        } else {
            return (double) waitingTime / queueSize;
        }
    }
}

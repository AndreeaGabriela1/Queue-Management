package BussinessLogic;

import Model.Client;
import Model.Queues;

import java.util.List;

public class ConcreteStrategyTime implements Strategy
{
    @Override
    public synchronized void addClient(List<Queues> queues, Client c)
    {
        Queues shortestQueue = null;
        int shortestTime = Integer.MAX_VALUE;
        for (Queues queue : queues)
        {
            int expectedTime = (int)queue.getExpectedWaitingTime();
            if (expectedTime < shortestTime)
            {
                shortestQueue = queue;
                shortestTime = expectedTime;
            }
        }
        if (shortestQueue != null)
        {
            shortestQueue.addClient(c);
        }
    }
}

package BussinessLogic;

import Model.Client;
import Model.Queues;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy
{
    @Override
    public void addClient(List<Queues> queues, Client c)
    {
        int minSize = Integer.MAX_VALUE;
        Queues selectedQueue = null;
        for (Queues q : queues)
        {
            if (q.getSize() < minSize)
            {
                minSize = q.getSize();
                selectedQueue = q;
            }
        }
        if (selectedQueue != null)
        {
            selectedQueue.addClient(c);
        }
    }
}

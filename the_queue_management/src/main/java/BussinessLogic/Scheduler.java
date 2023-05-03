package BussinessLogic;

import Model.Client;
import Model.Queues;

import java.util.ArrayList;
import java.util.List;

public class Scheduler
{
    private List<Queues> queues;
    private int MaximumNumberOfQueues;
    private Strategy strategy;
    public Scheduler(int maximumNumberOfQueues)
    {
        this.MaximumNumberOfQueues = maximumNumberOfQueues;
        this.queues = new ArrayList<>(MaximumNumberOfQueues);
        for (int i = 0; i < MaximumNumberOfQueues; i++)
        {
            Queues queue = new Queues();
            Thread t = new Thread(queue);
            t.start();
            queues.add(queue);
        }
        strategy = new ConcreteStrategyTime();
    }
    public void changeStrategy(SelectionPolicy policy)
    {
        if(policy == SelectionPolicy.SHORTEST_QUEUE)
            strategy = new ConcreteStrategyQueue();
        if(policy == SelectionPolicy.SHORTEST_TIME)
            strategy = new ConcreteStrategyTime();
    }
    public void dispatchClient(Client c)
    {
        strategy.addClient(queues, c);
    }
    public List<Queues> getQueues()
    {
        return queues;
    }
}

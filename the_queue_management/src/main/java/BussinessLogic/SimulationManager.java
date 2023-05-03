package BussinessLogic;

import Model.Client;
import GUI.SimulationFrame;

import java.io.*;
import java.util.*;
import java.util.List;

public class SimulationManager implements Runnable
{
    public int TimeLimit = 20;
    public int MaximumServingTime = 4;
    public int MinimumServingTime = 2;
    public int MaximumArrivalTime = 6;
    public int MinimumArrivalTime = 2;
    public int NumbersOfQueues = 2;
    public int NumberOfClients = 4;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    private Scheduler scheduler;
    private SimulationFrame frame;
    private List<Client> generatedClients;
    public SimulationManager(int numClients, int numQueues, int timeLimit, int minArrivalTime, int maxArrivalTime, int minProcessingTime, int maxProcessingTime, SimulationFrame a)
    {
        NumberOfClients=numClients;
        NumbersOfQueues=numQueues;
        TimeLimit=timeLimit;
        MinimumArrivalTime=minArrivalTime;
        MaximumArrivalTime=maxArrivalTime;
        MinimumServingTime=minProcessingTime;
        MaximumServingTime=maxProcessingTime;
        scheduler = new Scheduler(NumbersOfQueues);
        scheduler.changeStrategy(selectionPolicy);
        frame = a;
        frame.setVisible(true);
        generateNRandomClients();
    }
    private void generateNRandomClients()
    {
        List<Client> clients = new ArrayList<Client>(NumberOfClients);
        Random random = new Random();
        for(int i=0; i < NumberOfClients; i++)
        {
            int client_id = i+1;
            int arrival_time = MinimumArrivalTime + random.nextInt(MaximumArrivalTime - MinimumArrivalTime);
            int servingTime = MinimumServingTime + random.nextInt(MaximumServingTime - MinimumServingTime);
            Client cl = new Client(client_id,arrival_time,servingTime);
            clients.add(cl);
        }
        clients.sort(Comparator.comparingInt(Client::getArrivalTime));
        for(int i = 0; i < clients.size(); i++)
            clients.get(i).setClientId(i + 1);
        generatedClients = clients;
    }
    @Override
    public void run()
    {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter("Output.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int currentTime = 0;
        while(currentTime < TimeLimit)
        {
            Iterator<Client> iterator = generatedClients.iterator();
            while (iterator.hasNext())
            {
                Client client = iterator.next();
                if (client.getArrivalTime() == currentTime)
                {
                    scheduler.dispatchClient(client);
                    iterator.remove();
                }
            }
            writer.println(frame.refresh(currentTime,generatedClients.toString(),scheduler.getQueues()));
            currentTime++;
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        writer.close();
    }
    public static void main(String[] args)
    {
        SimulationFrame f = new SimulationFrame();
        f.setVisible(true);
    }
}

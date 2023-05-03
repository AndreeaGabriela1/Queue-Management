package BussinessLogic;

import Model.Client;
import Model.Queues;

import java.util.List;

public interface Strategy
{
    public void addClient(List<Queues> queues, Client c);
}


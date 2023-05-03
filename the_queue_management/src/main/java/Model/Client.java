package Model;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


import java.util.Timer;

public class Client {
    private int id;
    private int arrivalTime;
    private int serviceTime;

    public Client(int id, int arrivalTime, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }
    public int getClientId() {
        return id;
    }
    public void setClientId(int id)
    {
        this.id = id;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }
    @Override
    public String toString() {
        return "(" + id + "," + arrivalTime + "," + serviceTime + ")";
    }

    public void setServiceTime(int i) {
        this.serviceTime = i;
    }
}

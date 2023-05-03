package GUI;

import BussinessLogic.SimulationManager;
import Model.Queues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SimulationFrame extends JFrame
{
    private JTextArea textArea;
    private JTextField numClientsField;
    private JTextField numQueuesField;
    private JTextField timeLimitField;
    private JTextField minArrivalTimeField;
    private JTextField maxArrivalTimeField;
    private JTextField minProcessingTimeField;
    private JTextField maxProcessingTimeField;
    public SimulationFrame()
    {
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane);

        numClientsField = new JTextField(10);
        numQueuesField = new JTextField(10);
        timeLimitField = new JTextField(10);
        minArrivalTimeField = new JTextField(10);
        maxArrivalTimeField = new JTextField(10);
        minProcessingTimeField = new JTextField(10);
        maxProcessingTimeField = new JTextField(10);

        JLabel numClientsLabel = new JLabel("Number of clients:");
        JLabel numQueuesLabel = new JLabel("Number of queues:");
        JLabel timeLimitLabel = new JLabel("Simulation interval:");
        JLabel minArrivalTimeLabel = new JLabel("Minimum arrival time:");
        JLabel maxArrivalTimeLabel = new JLabel("Maximum arrival time:");
        JLabel minProcessingTimeLabel = new JLabel("Minimum processing time:");
        JLabel maxProcessingTimeLabel = new JLabel("Maximum processing time:");

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.PINK);
        inputPanel.setLayout(new GridLayout(7, 2));
        inputPanel.add(numClientsLabel);
        inputPanel.add(numClientsField);
        inputPanel.add(numQueuesLabel);
        inputPanel.add(numQueuesField);
        inputPanel.add(timeLimitLabel);
        inputPanel.add(timeLimitField);
        inputPanel.add(minArrivalTimeLabel);
        inputPanel.add(minArrivalTimeField);
        inputPanel.add(maxArrivalTimeLabel);
        inputPanel.add(maxArrivalTimeField);
        inputPanel.add(minProcessingTimeLabel);
        inputPanel.add(minProcessingTimeField);
        inputPanel.add(maxProcessingTimeLabel);
        inputPanel.add(maxProcessingTimeField);
        JButton startButton = new JButton("Start");
        SimulationFrame a = this;
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int numClients = Integer.parseInt(numClientsField.getText());
                int numQueues = Integer.parseInt(numQueuesField.getText());
                int timeLimit = Integer.parseInt(timeLimitField.getText());
                int minArrivalTime = Integer.parseInt(minArrivalTimeField.getText());
                int maxArrivalTime = Integer.parseInt(maxArrivalTimeField.getText());
                int minProcessingTime = Integer.parseInt(minProcessingTimeField.getText());
                int maxProcessingTime = Integer.parseInt(maxProcessingTimeField.getText());
                SimulationManager gen = new SimulationManager(numClients,numQueues,timeLimit,minArrivalTime,maxArrivalTime,minProcessingTime,maxProcessingTime,a);
                Thread  t = new Thread(gen);
                t.start();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(startButton, BorderLayout.SOUTH);

        setTitle("Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        setVisible(true);
    }

    public String refresh(int currentTime, String waitingClients, List<Queues> queues)
    {
        String t = "";
        textArea.setText("");
        textArea.append("Time " + currentTime + "\n");
        t = t + "Time " + currentTime + "\n";
        textArea.append("Waiting clients: " + waitingClients + "\n");
        t = t + "Waiting clients: " + waitingClients + "\n";
        for (int i = 0; i < queues.size(); i++) {
            textArea.append("Queue " + (i + 1) + ": ");
            t = t + "Queue " + (i + 1) + ": ";
            if (queues.get(i).getClients().isEmpty())
            {
                textArea.append("closed");
                t = t + "closed";
            }
            else
            {
                textArea.append(queues.get(i).getClients().toString());
                t = t + queues.get(i).getClients().toString();
            }
            textArea.append("\n");
            t = t + "\n";
        }
        return t;
    }
}

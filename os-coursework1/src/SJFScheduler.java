
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Properties;

/**
 * Shortest Job First Scheduler
 *
 * @version 2017
 */
public class SJFScheduler extends AbstractScheduler 
{

    PriorityQueue<Process> ready;
    private double initialBurstEstimate;
    private double alphaBurstEstimate;
    private double burstEstimation;

    public SJFScheduler() 
    {
        Comparator<Process> burstEstimationComparator = new Comparator<Process>() 
        {
            @Override
            public int compare(Process p1, Process p2) 
            {
                return (int) Math.ceil(burstEstimation(p1) - burstEstimation(p2));
            };
        };
        
        ready = new PriorityQueue<>(burstEstimationComparator);
    }

    public double burstEstimation(Process process) 
    {
        burstEstimation = initialBurstEstimate * process.getRecentBurst() + (1 - alphaBurstEstimate);
        return burstEstimation;
    }

    @Override
    public void initialize(Properties parameters) 
    {
        initialBurstEstimate = Double.parseDouble(parameters.getProperty("initialBurstEstimate"));
        alphaBurstEstimate = Double.parseDouble(parameters.getProperty("alphaBurstEstimate"));
    }

    public void ready(Process process, boolean usedFullTimeQuantum) 
    {
        ready.offer(process);
    }

    public Process schedule() 
     {

        
        if (!ready.isEmpty()) 
        {
            System.out.print("Scheduler selects process " + ready.peek());
            return ready.remove();
        } 
        else 
        {
            System.out.print("The ready queue is empty");
            return null;
        }

    }
}

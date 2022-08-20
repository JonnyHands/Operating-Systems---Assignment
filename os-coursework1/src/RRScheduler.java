
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.Queue;

/**
 * Round Robin Scheduler
 *
 * @version 2017
 */
public class RRScheduler extends AbstractScheduler 
{

    // TODO
    private Queue<Process> ready;
    private int timeQuantum;

    public RRScheduler() 
    {
        ready = new PriorityQueue<Process>();
    }

    @Override
    public void initialize(Properties parameters) 
    {
        timeQuantum = Integer.parseInt(parameters.getProperty("timeQuantum"));
    }
    
    @Override
    public void ready(Process process, boolean usedFullTimeQuantum) 
    {
        if (process.getRecentBurst() > timeQuantum) 
        {
            usedFullTimeQuantum = true;
            process.setPriority((process.getPriority() + 1));
        }
        ready.offer(process);

    }

    
    @Override
    public Process schedule() 
    {

        // TODO
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

    @Override
    public int getTimeQuantum()
    {
        return timeQuantum;
    }

}

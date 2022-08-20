
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Properties;

/**
 * Feedback Round Robin Scheduler
 *
 * @version 2017
 */
public class FeedbackRRScheduler extends AbstractScheduler 
{

    PriorityQueue<Process> ready;
    private int timeQuantum;

    public FeedbackRRScheduler() 
    {
        ready = new PriorityQueue<>();
        Comparator<Process> burstTimeComparator = new Comparator<Process>() 
        {
            @Override
            public int compare(Process p1, Process p2) 
            {
                return p1.getNextBurst() - p2.getNextBurst();
            };
        };
        
        ready = new PriorityQueue<>(burstTimeComparator);
    }

    @Override
    public void initialize(Properties parameters) 
    {
        timeQuantum = Integer.parseInt(parameters.getProperty("timeQuantum"));
    }

    
    public void ready(Process process, boolean usedFullTimeQuantum) 
    {

        if (process.getRecentBurst() > timeQuantum) 
        {
            usedFullTimeQuantum = true;
            process.setPriority((process.getPriority() + 1));
        }
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

    @Override
    public int getTimeQuantum() 
    {      
        return timeQuantum;
    }

    @Override
    public boolean isPreemptive() 
    {
        return true;
    }

}

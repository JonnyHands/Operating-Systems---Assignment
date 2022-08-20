
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.Queue;

/**
 * Ideal Shortest Job First Scheduler
 *
 * @version 2017
 */
public class IdealSJFScheduler extends AbstractScheduler 
{

    PriorityQueue<Process> ready;

    public IdealSJFScheduler() 
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

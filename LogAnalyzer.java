/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String fileName)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(fileName);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    //7.14
    /**
     * Return the number of accesses recorded in the log file
     */
    public int numberOfAccesses()
    {
        int total = 0;
        for(int hour = 0;hour < hourCounts.length; hour++){
            total+=hourCounts[hour];
        }
        return total;
    }
    
    /**
     * Returns the Busiest Hour
     */
    public int busiestHour()
    {
        int i=0;
        int maximumCounts=hourCounts[i];
        int busiestHour=0;
         
        for(i=0; i < hourCounts.length; i++)
        {
             
            if(maximumCounts <  hourCounts[i])
            {
                maximumCounts=hourCounts[i];
                busiestHour=i;
            }
        }
        return busiestHour;
    }
    
    /**
     * Returns the hour which had the
     * least number of counts
     * 
     */
    public int quitestHour()
    {
        int i=0;
        int minimumCounts=hourCounts[i];
        int quitestHour=0;
        for(i=0; i < hourCounts.length; i++)
        {
            if(minimumCounts > hourCounts[i])
            {
                   minimumCounts=hourCounts[i];
                   quitestHour=i;
            }
        }
         
        return quitestHour;
    }
    
    /**
      * Return the two-hour period which is busiest.
     */
    public int busiestTwoHourPeriod()
    {
     int busiestPeriod = 0;
     int busiestPeriodCount = 0;
     for(int hour = 0; hour < hourCounts.length - 1; hour++) {
            int periodCount = hourCounts[hour] + hourCounts[hour+1];
            if(periodCount > busiestPeriodCount) {
                busiestPeriod = hour;
                busiestPeriodCount = periodCount;
            }
     }
     return busiestPeriod;
    }
}

package AnimalMonitoring;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Monitor counts of different types of animal.
 * Sightings are recorded by spotters.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.03.01 (functional)
 */
public class AnimalMonitor
{
    private ArrayList<Sighting> sightings;

    /**
     * Create an AnimalMonitor.
     */
    public AnimalMonitor()
    {
        this.sightings = new ArrayList<>();
        addSightings("sightings.csv");
    }

    /**
     * Test the AnimalMonitor
     */
    public static void main(String[] args)
    {
        System.out.println("Creating the Animal Monitor");
        AnimalMonitor monitor = new AnimalMonitor();
        System.out.println("Printing the list of sightings");
        monitor.printList();
        System.out.println();
        System.out.println("Test exercise_5_11:"+ 
            "print details of all the sightings recorded on day id 1");
        monitor.exercise_5_11(1);
        System.out.println("Test exercise_5_12:"+ 
            "print details of all the sightings  of Mountain Gorilla made on day 0.");  
        monitor.exercise_5_12("Mountain Gorilla", 0);
        System.out.println("Test exercise_5_15:"+ 
            "Print the counts of all the sightings of Buffalo.");  
        monitor.exercise_5_15("Buffalo");       
        System.out.println("Test exercise_5_20:"+ 
            "Return a count of the number of sightings of Mountain Gorilla were made by the spotter 0 on day 0.");  
        System.out.println(monitor.exercise_5_20("Mountain Gorilla", 0, 0));         
        System.out.println("Test exercise_5_21:"+ 
            " names of the animals seen by the spotter 3 on day 2");  
        System.out.println(monitor.exercise_5_21(3, 2));           
        System.out.println("Test exercise_5_26:"+ 
            "returns the largest count for Elephant in a single Sighting record");  
        System.out.println(monitor.exercise_5_26("Elephant"));           
        // add your test code here
    }

    // add your methods below here
    /**
     * Print details of all the sightings recorded on a particular dayID.
     * @param dayID the day the sightings were recorded.
     */
    public void  exercise_5_11(int dayID)
    {
        sightings.stream()
        .filter(sighting -> dayID == sighting.getPeriod())
        .forEach(sighting -> System.out.println(sighting.getDetails()));        
    }

    /**
     * Print details of all the sightings  of a particular animal made on a particular day.
     * @param dayID the day the sightings were recorded.
     * @param animal the animal name.
     */
    public void  exercise_5_12(String animal, int dayID)
    {
        sightings.stream()
        .filter(sighting -> animal.equals(sighting.getAnimal()))
        .filter(sighting -> dayID == sighting.getPeriod())
        .forEach(sighting -> System.out.println(sighting.getDetails()));        
    }    

    /**
     * Print the counts of all the sightings of a given animal.
     * @param animal animal name.
     */
    public void exercise_5_15(String animal)
    {
        sightings.stream()
        .filter(sighting -> animal.equals(sighting.getAnimal()))
        .map(sighting -> sighting.getCount())
        .forEach(counts -> System.out.println(counts));        
    }

    /**
     * Return a count of the number of sightings of the given animal were made by the
    spotter on a particular day.
     * @param animal The type of animal
     * @param spotterID The spotter
     * @param dayID The day
     * @return The count of sightings 
     */
    public int exercise_5_20(String animal, int spotterID, int dayID )
    {
        return sightings.stream()
        .filter(sighting -> animal.equals(sighting.getAnimal()))
        .filter(sighting -> spotterID == sighting.getSpotter())
        .filter(sighting -> dayID == sighting.getPeriod())
        .map(sighting -> sighting.getCount())
        .reduce(0, (runningSum, count) -> runningSum + count);
    }

    public String exercise_5_21(int spotterID, int dayID )
    {
        return sightings.stream()
        .filter(sighting -> spotterID == sighting.getSpotter())
        .filter(sighting -> dayID == sighting.getPeriod())
        .map(sighting -> sighting.getAnimal())
        .reduce("", (animalNames, name) -> animalNames + " " + name);
    }   

    public int exercise_5_26(String animal)
    {
        return sightings.stream()
        .filter(sighting -> animal.equals(sighting.getAnimal()))
        .map(sighting -> sighting.getCount())
        .max(Integer::compare)
        .get();
    }
    // add your methods above here

    /**
     * Add the sightings recorded in the given filename to the current list.
     * @param filename A CSV file of Sighting records.
     */
    public void addSightings(String filename)
    {
        SightingReader reader = new SightingReader();
        sightings.addAll(reader.getSightings(filename));
    }

    /**
     * Print details of all the sightings.
     */
    public void printList()
    {
        sightings.forEach(sighting -> System.out.println(sighting.getDetails()));
    }

    /**
     * Print details of all the sightings of the given animal.
     * @param animal The type of animal.
     */
    public void printSightingsOf(String animal)
    {
        sightings.stream()
        .filter(sighting -> animal.equals(sighting.getAnimal()))
        .forEach(sighting -> System.out.println(sighting.getDetails()));        
    }

    /**
     * Print all the sightings by the given spotter.
     * @param spotter The ID of the spotter.
     */
    public void printSightingsBy(int spotter)
    {
        sightings.stream()
        .filter(sighting -> sighting.getSpotter() == spotter)
        .map(sighting -> sighting.getDetails())
        .forEach(details -> System.out.println(details));        
    }

    /**
     * Return a count of the number of sightings of the given animal.
     * @param animal The type of animal.
     * @return The count of sightings of the given animal.
     */
    public int getCount(String animal)
    {
        return sightings.stream()
        .filter(sighting -> animal.equals(sighting.getAnimal()))
        .map(sighting -> sighting.getCount())
        .reduce(0, (runningSum, count) -> runningSum + count);
    }
}

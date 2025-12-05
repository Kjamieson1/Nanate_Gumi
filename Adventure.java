import java.util.Arrays;
import java.util.Scanner;

public class Adventure 
{
    static Enemy sharedBoss = new Enemy("Shizugatake", 100, 50, 30);
    static Object bossLock = new Object();  // For synchroniztion 
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
 
        System.out.println("In this game we will focus on three of the samurai of the Nanate gumi.\n"
                + "Hayami Morihisa (Known as the learder).\n"
                + "Nakagawa Kiyohide (Known as the strategist).\n"
                + "Akashi Takenori (Known as the fierce warrior). \n"
                + "We will follow through their lives seeing the progression of the Nanate gumi.\n"
                + "1. Hayami Morihisa\n"
                + "2. Nakagawa Kiyohide\n"
                + "3. Akashi Takenori\n"
                + "Choose a samurai to learn more about them (1-3): ");

        // Creating instances of each character
        HaMo HaMo = new HaMo(120, 15, "Hayami Morihisa");
        NaKi NaKi = new NaKi( 100, 15, "Nakagawa Kiyohide");
        AkTo AkTo = new AkTo(100, 10, "Akashi Takenori");

        GameCharacter[] samurai  = {HaMo, NaKi, AkTo};
        
        switch (sc.nextInt())
        {
            case 1:
                System.out.println("You have chosen Hayami Morihisa!");
                HaMo.run();  // Play HaMo's backstory
                break;
            case 2:
                System.out.println("You have chosen Nakagawa Kiyohide!");
                NaKi.run();  // Play NaKi's backstory
                break;
            case 3:
                System.out.println("You have chosen Akashi Takenori!");
                AkTo.run();  // Play AkTo's backstory
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Hayami Morihisa.");
                HaMo.run();
                break;
        }

        //LAMBDA EXPRESSIONS
        Arrays.sort(samurai , (s1, s2) -> s2.health() - s1.health());

        long survivors = Arrays.stream(samurai)
            .filter(s -> s.health() > 0)
            .count();

        System.out.println("Number of surviving samurai: " + survivors);
        Arrays.stream(samurai).forEach(s -> 
        System.out.println(s.health() > 0 ? "Alive" : "Fallen"));

        //Second part of the adventure
        //Showing Parrallelism

        System.out.println("All samurai have completed their own missions! \n"
                + "I'll revive any fallen samurai for the next part of the adventure. \n"
                + "Now, we have some background on each of them. \n"
                + "Let's see how they worked together as a team.\n"
                + "In the Nanate gumi. In the battle of Shizugatake.\n");

        GameCharacter[] samuraiTeam  = {HaMo, NaKi, AkTo};
        Thread[] samuraiThreadsTeam = {HaMo, NaKi, AkTo};

        for (Thread samuraiThreadTeam : samuraiThreadsTeam) 
        {
            // Runs all three in parallel
            samuraiThreadTeam.start();
        }
        
        try 
        {
            for (Thread samuraiThreadTeam : samuraiThreadsTeam) 
            {
                // Wait for each to complete
                samuraiThreadTeam.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Mission was interrupted!");
        }

    }
}

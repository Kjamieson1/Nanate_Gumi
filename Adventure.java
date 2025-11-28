import java.util.Arrays;
import java.util.Scanner;

public class Adventure 
{
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
                + "3. Akashi Takenori\n");

        // Creating instances of each character
        HaMo HaMo = new HaMo(120, 15, "Hayami Morihisa");
        NaKi NaKi = new NaKi( 100, 15, "Nakagawa Kiyohide");
        AkTo AkTo = new AkTo(100, 10, "Akashi Takenori");

        GameCharacter[] samurai  = {HaMo, NaKi, AkTo};
        Thread[] samuraiThreads = {HaMo, NaKi, AkTo};

        for (Thread samuraiThread : samuraiThreads) 
        {
            // Runs all three in parallel
            samuraiThread.start();
        }
        
        try 
        {
            for (Thread samuraiThread : samuraiThreads) 
            {
                // Wait for each to complete
                samuraiThread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Mission was interrupted!");
        }

        Arrays.sort(samurai , (s1, s2) -> s2.health() - s1.health());

        long survivors = Arrays.stream(samurai)
            .filter(s -> s.health() > 0)
            .count();

        System.out.println("Number of surviving samurai: " + survivors);
        Arrays.stream(samurai).forEach(s -> 
            System.out.println(s.health() > 0 ? "Alive" : "Fallen")
        );
        
        for (GameCharacter samuraiThread : samurai) 
        {
            if (samuraiThread.health() > 0)
            {
                System.out.println(samuraiThread.health());
            }
        }

        System.out.println("All samurai have completed their missions!");
    }
}

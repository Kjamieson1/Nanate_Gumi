package Main;
import java.util.Arrays;
import java.util.Scanner;

import Characters.AkTo;
import Characters.Enemy;
import Characters.GameCharacter;
import Characters.HaMo;
import Characters.NaKi;

public class Adventure 
{
    public static Enemy sharedBoss = new Enemy("Shizugatake", 100, 50, 30);
    public static Object bossLock = new Object();  // For synchroniztion 
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
 
        System.out.println("========== Nanate Gumi ===========\n"
                +"\n"
                +"In this game we will focus on three of the samurai of the Nanate gumi.\n"
                + "Hayami Morihisa (Known as the learder).\n"
                + "Nakagawa Kiyohide (Known as the strategist).\n"
                + "Akashi Takenori (Known as the fierce warrior). \n"
                + "We will follow through their lives seeing the progression of the Nanate gumi.\n"
                + "_____________________________");

        // Creating instances of each character
        HaMo HaMo = new HaMo(120, 15, "Hayami Morihisa");
        NaKi NaKi = new NaKi( 100, 15, "Nakagawa Kiyohide");
        AkTo AkTo = new AkTo(100, 10, "Akashi Takenori");

        GameCharacter[] samurai  = {HaMo, NaKi, AkTo};

        for(int i = 0; i <= 2; i++)
        {
            System.out.println("1. Hayami Morihisa\n"
                + "2. Nakagawa Kiyohide\n"
                + "3. Akashi Takenori\n"
                + "4. Skip to Boss battle \n"
                + "Choose a samurai to learn more about them (1-3):");
            switch (sc.nextInt())
            {
            case 1:
                if(HaMo.health() <= 0 || HaMo.playedCheck() == true)
                {
                    System.out.println("You can not try again in the same game. \n");
                    i -= 1;
                    break;
                }
                System.out.println("You have chosen Hayami Morihisa! \n");
                HaMo.run();  // Play HaMo's backstory
                break;
            case 2:
                if(NaKi.health() <= 0 || NaKi.playedCheck() == true)
                {
                    System.out.println("You can not try again in the same game. \n");
                    i -= 1;
                    break;
                }
                System.out.println("You have chosen Nakagawa Kiyohide!");
                NaKi.run();  // Play NaKi's backstory
                break;
            case 3:
                if(AkTo.health() <= 0 || AkTo.playedCheck() == true)
                {
                    System.out.println("You can not try again in the same game. \n");
                    i -= 1;
                    break;
                }
                System.out.println("You have chosen Akashi Takenori!");
                AkTo.run();  // Play AkTo's backstory
                break;
            case 4: 
                i += 3;
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Hayami Morihisa.");
                HaMo.run();
                break;
            }
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
                + "I Hope you kept everyone alive! \n"
                + "Let's see how they worked together as a team.\n"
                + "The Nanate gumi. In the battle of Shizugatake.\n");

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
            // Delay to ensure all console output and final attacks complete
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Mission was interrupted!");
        }

        // Synchronized to prevent interleaving with any remaining thread output
        synchronized (bossLock) {
            System.out.println("\n========== Battle Complete! ===========");
            
            // Check if boss is defeated
            if (sharedBoss.health() <= 0) {
                System.out.println("The Shizugatake boss has been defeated!");
                System.out.println("The Nanate gumi fought bravely together.");
                System.out.println("\nThe battle is won! The samurai have triumphed!");
            } else {
                System.out.println("The battle continues...");
                System.out.println("\nThe samurai retreat to fight another day...");
            }
        }

        // Final survivor count
        long finalSurvivors = Arrays.stream(samuraiTeam)
            .filter(s -> s.health() > 0)
            .count();
        System.out.println("\nSurviving warriors: " + finalSurvivors + "/3");
        System.out.println("\n===== End of Adventure =====");

    }
}

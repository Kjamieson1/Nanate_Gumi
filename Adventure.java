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
                + "Who would you like to play as?\n"
                + "1. Hayami Morihisa\n"
                + "2. Nakagawa Kiyohide\n"
                + "3. Akashi Takenori\n"
                + "Please enter the number of your choice:");

        // Creating instances of each character
        HaMo HaMo = new HaMo(120, 25, 15, "Hayami Morihisa");
        NaKi NaKi = new NaKi( 100, 30, 15, "Nakagawa Kiyohide");
        AkTo AkTo = new AkTo(100, 20, 10, "Akashi Takenori");

        // Getting user input and starting the chosen character's adventure
        try {
                switch (sc.nextInt()) 
                {
                case 1:
                    HaMo.start();
                    break;
                case 2:
                    NaKi.start();
                    break;
                case 3:
                    AkTo.start();
                    break;
                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 3.");}
        } catch (Exception e)
        {
            System.out.println("Invalid input. Please enter a number between 1 and 3.");
        }

        // Ensuring the main thread waits for the chosen character to finish
        try {
            AkTo.join();
        } catch (InterruptedException e) {
            System.out.println("The samurai was interrupted!");
        }

        System.out.println("All samurai have completed their missions!");
    }
}

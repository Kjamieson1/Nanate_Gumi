import java.util.Random;
import java.util.Scanner;

public class HaMo extends Thread implements GameCharacter
{
    // Attributes
    private String name;
    private int hp;
    private int atk;
    private int def;
    private Scanner sc = new Scanner(System.in);

    // Constructor
    public HaMo(int hp, int atk, int def, String name)
    {
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.name = name;
    }

    // Methods
    @Override
    public void start()
    {
        System.out.println("Born in the 1550's Edo period Japan, located in the Omi province. \n"
                + name +" is a simi wealthy samurai whos family owns lands in the area. \n"
                + "Serving under the Azai clan. \n"
                + "The Azai clan's leader, Asai Nagamasa recently allied with a powerful daimyo, Asakura. \n"
                + "In 1573 Oda Nobunaga invades the Omi province with the intention of conquering. \n"
                + name + " must now choose to defend his clan and province or flee to safety. \n");

        System.out.println("What will " + name + " do? \n"
                + "1. Defend the province \n"
                + "2. Flee to safety \n");

        int choice = sc.nextInt();

        switch(choice)
        {
            case 1:
                System.out.println("Choosing to defend the province! \n"
                        + name + " heads to the Azai castle to prepare for battle! \n"
                        + "Arrving at the castle, he readies his katana and heads to the front of the lines! \n");
                
                System.out.println("A samurai from the opposing Oda forces approaches");
                Enemy OdaEnemy = new Enemy("Oda Samurai", 25, 1, 3);
                // Going to add a fitgh here between player and enemy.

                System.out.println("The battle is fierce \n"
                        + "Azai castle falls to the Oda forces. \n"
                        + name + " fought bravely buy was ultimataly defeated"); 
                break;
            case 2:
                System.out.println(name + " chose to flee to safety! \n");
                run();
                System.out.println(name + " escaped the battle but lost his home!");
                break;
        }

    }

    @Override
    public int health()
    {
        return hp;
    }

    @Override
    public void attack()
    {

    }

    @Override
    public void defense()
    {
    
    }

    @Override
    public void run()
    {
        Random rand = new Random();
        try{
            for (int i = 1; i <= 2; i++)
            {
                System.out.println(name + ": Step " + i);
                Thread.sleep(rand.nextInt(1000) + 500);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Your mission was interrupted!");
        }
    }
    
    @Override
    public void act()
    {

    }
    
}
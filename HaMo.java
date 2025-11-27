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
    private Random ra = new Random();

    // Constructor
    public HaMo(int hp, int def, String name)
    {
        this.hp = hp;
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

        switch(sc.nextInt())
        {
            case 1:
                System.out.println("Choosing to defend the province! \n"
                        + name + " heads to the Azai castle to prepare for battle! \n"
                        + "Arrving at the castle, he readies his katana and heads to the front of the lines! \n");
                
                System.out.println("A samurai from the opposing Oda forces approaches");
                Enemy OdaEnemy = new Enemy("Oda Samurai", 50, 50, 50);
                Enemy OdaEnemy2 = new Enemy("Oda Samurai", 50, 50, 50);
                Enemy OdaEnemyBoss = new Enemy("Oda Nobunaga", 1000, 1000, 1000);

                while(OdaEnemy.health() > 0 && hp > 0)
                {
                    int HaMoDam = ra.nextInt(15, 20);
                    System.out.println( "1. Attack \n"
                        + "2. Defend");

                    switch(sc.nextInt())
                    {
                        case 1:
                            attack();
                            OdaEnemy.hp -= HaMoDam;
                            System.out.println("You dealt " + HaMoDam + " damage to the enemy! \n"
                                    + OdaEnemy.name + " stricks back!");
                        
                            hp -= OdaEnemy.attack();

                            System.out.println("You received " + OdaEnemy.attack() + " damage! \n"
                                    + "Your current health is: " + hp + "\n"
                                    + OdaEnemy.name + " current health is: " + OdaEnemy.health() + "\n");
                            break;
                        case 2:
                            defense();
                            hp -= (OdaEnemy.attack() - def);
                            System.out.println("You defended against the enemy's attack! \n"
                                    + "You received " + (OdaEnemy.attack() - def) + " damage! \n"
                                    + "Your current health is: " + hp + "\n"
                                    + OdaEnemy.name + " current health is: " + OdaEnemy.health() + "\n");
                            break;
                    }   
                }

                System.out.println("The battle rages on! \n"
                        + name + " has defeated the first wave of Oda samurai! \n"
                        + "But more enemies approach! \n");

                while(OdaEnemy2.health() > 0 && hp > 0)
                {
                    int HaMoDam = ra.nextInt(15000, 20000);
                    System.out.println( "1. Attack \n"
                        + "2. Defend");

                    switch(sc.nextInt())
                    {
                        case 1:
                            attack();
                            OdaEnemy2.hp -= HaMoDam;
                            System.out.println("You dealt " + HaMoDam + " damage to the enemy! \n"
                                    + OdaEnemy2.name + " stricks back!");
                        
                            hp -= OdaEnemy2.attack();
                            System.out.println("You received " + OdaEnemy2.attack() + " damage! \n"
                                    + "Your current health is: " + hp + "\n"
                                    + OdaEnemy2.name + " current health is: " + OdaEnemy2.health() + "\n");
                            break;
                        case 2:
                            defense();
                            hp -= (OdaEnemy2.attack() - def);
                            System.out.println("You defended against the enemy's attack! \n"
                                    + "You received " + (OdaEnemy2.attack() - def) + " damage! \n"
                                    + "Your current health is: " + hp + "\n"
                                    + OdaEnemy2.name + " current health is: " + OdaEnemy2.health() + "\n");
                            break;
                    }   
                }

                System.out.println("The battle intensifies! \n"
                        + name + " has defeated the second wave of Oda samurai! \n"
                        + "But their leader, Oda Nobunaga himself approaches! \n");

                while(OdaEnemyBoss.health() > 0 && hp > 0)
                {
                    int HaMoDam = ra.nextInt(15000, 20000);
                    System.out.println( "1. Attack \n"
                        + "2. Defend");

                    switch(sc.nextInt())
                    {
                        case 1:
                            attack();
                            OdaEnemyBoss.hp -= HaMoDam;
                            System.out.println("You dealt " + HaMoDam + " damage to the enemy! \n"
                                    + OdaEnemyBoss.name + " stricks back!");
                        
                            hp -= OdaEnemyBoss.attack();
                            System.out.println("You received " + OdaEnemyBoss.attack() + " damage! \n"
                                    + "Your current health is: " + hp + "\n"
                                    + OdaEnemyBoss.name + " current health is: " + OdaEnemyBoss.health() + "\n");
                            break;
                        case 2:
                            defense();
                            hp -= (OdaEnemyBoss.attack() - def);
                            System.out.println("You defended against the enemy's attack! \n"
                                    + "You received " + (OdaEnemyBoss.attack() - def) + " damage! \n"
                                    + "Your current health is: " + hp + "\n"
                                    + OdaEnemyBoss.name + " current health is: " + OdaEnemyBoss.health() + "\n");
                            break;
                    }   
                }

                System.out.println("The battle is fierce \n"
                        + "Azai castle falls to the Oda forces. \n"
                        + name + " fought bravely buy was ultimataly defeated"); 
                System.exit(0);

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
         if (hp <= 0)
        {
           return 0;
        }
        return hp;
    }

    //Want to expand by adding different attack moves
    @Override
    public void attack()
    {
        int x = ra.nextInt(5);
        try{
            for (int i = 1; i <= x; i++)
            {
                System.out.println(name + " swings his katana \n");
                Thread.sleep(ra.nextInt(1000) + 500);
            }
        } catch (InterruptedException e) {
        }
    }

    //Want to expand by adding different defense moves
    @Override
    public void defense()
    {
        int x = ra.nextInt(3);
        try{
            for (int i = 1; i <= x; i++)
            {
                System.out.println(name + " gets in standing position");
                Thread.sleep(ra.nextInt(1000) + 500);
            }
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void run()
    {
        try{
            for (int i = 1; i <= 2; i++)
            {
                System.out.println(name + ": Step " + i);
                Thread.sleep(ra.nextInt(1000) + 500);
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
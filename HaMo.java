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
                + "The Azai clan's leader, Azai Nagamasa recently allied with a powerful daimyo, Asakura. \n"
                + "In 1573 Oda Nobunaga invades the Omi province with the intention of conquering. \n"
                + name + " must now choose to defend his clan and province or flee to safety. \n");

        System.out.println("What will " + name + " do? \n"
                + "1. Defend the province \n"
                + "2. Flee to safety \n");

        switch(sc.nextInt())
        {
            //Chosing to defend the province
            case 1:
                System.out.println("Choosing to defend the province! \n"
                        + name + " heads to the Azai castle to prepare for battle! \n"
                        + "Arrving at the castle, he readies his katana and heads to the front of the lines! \n");
                
                System.out.println("A samurai from the opposing Oda forces approaches");

                Enemy OdaEnemy = new Enemy("Oda Samurai", 50, 50, 50);
                Enemy OdaEnemy2 = new Enemy("Oda Samurai", 50, 50, 50);
                Enemy OdaEnemyBoss = new Enemy("Oda Nobunaga", 100, 100, 100);

                Enemy[] wave1 = {OdaEnemy, OdaEnemy2, OdaEnemyBoss};

                //First wave of enemies
                for(Enemy enemy : wave1)
                {
                    int HaMoDam = ra.nextInt(15, 20);

                    while(enemy.health() > 0 && hp > 0)
                    {
                        System.out.println( "1. Attack \n"
                                + "2. Defend");

                            switch(sc.nextInt())
                            {
                            case 1:
                                attack();
                                enemy.hp -= HaMoDam;
                                System.out.println("You dealt " + HaMoDam + " damage to the enemy! \n"
                                        + enemy.name + " stricks back!");
                        
                                hp -= enemy.attack();

                                System.out.println("You received " + enemy.attack() + " damage! \n"
                                        + "Your current health is: " + hp + "\n"
                                        + enemy.name + " current health is: " + enemy.health() + "\n");
                                break;
                            case 2:
                                defense();
                                hp -= (enemy.attack() - def);
                                System.out.println("You defended against the enemy's attack! \n"
                                        + "You received " + (enemy.attack() - def) + " damage! \n"
                                        + "Your current health is: " + hp + "\n"
                                        + enemy.name + " current health is: " + enemy.health() + "\n");
                                break;
                            }

                            if(enemy.health() <= 0)
                            {
                                System.out.println(name + " has defeated an enemy samurai! \n"
                                        + "Another enemy approaches!"
                                );
                            }
                    }
                }
                // Suppose to lose fight Ending scenario
                // in real life the castle falls ending the Azai clan
                System.out.println("The battle is fierce \n"
                        + "Azai castle falls to the Oda forces. \n"
                        + name + " fought bravely buy was ultimataly defeated"); 
                return;

            // Choosing to flee to safety
            case 2:
                System.out.println(name + " chose to flee to safety! \n");
                run();
                System.out.println(name + " escaped the battle but lost his home! \n"
                        + "Not sure where to go" + name + " wanders aimlessly around the Omi province. \n"
                        + "After a few days of wondering as a ronin, he runs into Toyotomi Hideyoshi. \n"
                        + "Impressed by " + name + "'s skills, Hideyoshi offers him a position in his growing forces. \n"
                        + "Accepting the offer, he joins Hideyoshi's army and begins a new chapter in his life. \n");
                break;
        }

        System.out.println("14 Years after the fall of the Azai clan" + name + " has risen through the ranks of Hideyoshi's forces. \n"
                + "He has become one of Hideyoshi's close retainers and a trusted samurai in his army. \n"
                + "Participating in many battles and campaigns, he has proven his loyalty and skill on the battlefield. \n"
                + name + " next mission is to compain with Hideyoshi to unify Japan under his rule. \n"
                + "He is to set out to conquer Odawara"
                + "");

        // Boosting stats for next battle
        hp += 100;
        def += 100;

        System.out.println("When arriving at the Odawara castle, " + name + " is met with a strong force ahead of him. \n"
                + "Being reminded of his past losses and the fall of his previous clan. \n"
                + "And a chance to redeem his clans honor. \n"
                + ""
                + "Unbothered He decides to press forward and engage the forces head on. \n");

        Enemy OdaEnemy3 = new Enemy("Oda Samurai", 50, 50, 50);
        Enemy OdaEnemy4 = new Enemy("Oda Samurai", 50, 50, 50);
        Enemy OdaEnemyBoss = new Enemy("Oda Nobunaga", 100, 100, 100);

        Enemy[] wave2 = {OdaEnemy3, OdaEnemy4, OdaEnemyBoss};

        //Second wave of enemies
        for(Enemy enemy : wave2)
        {
            int HaMoDam = ra.nextInt(100, 150);

            while(enemy.health() > 0 && hp > 0)
            {
                System.out.println( "1. Attack \n"
                        + "2. Defend");

                    switch(sc.nextInt())
                    {
                    case 1:
                        attack();
                        enemy.hp -= HaMoDam;
                        System.out.println("You dealt " + HaMoDam + " damage to the enemy! \n"
                                + enemy.name + " stricks back!");
                        
                        hp -= enemy.attack();

                        System.out.println("You received " + enemy.attack() + " damage! \n"
                                + "Your current health is: " + hp + "\n"
                                + enemy.name + " current health is: " + enemy.health() + "\n");
                        break;
                    case 2:
                        defense();
                        hp -= (enemy.attack() - def);
                        System.out.println("You defended against the enemy's attack! \n"
                                + "You received " + (enemy.attack() - def) + " damage! \n"
                                + "Your current health is: " + hp + "\n"
                                + enemy.name + " current health is: " + enemy.health() + "\n");
                        break;
                    }

                    if(enemy.health() <= 0)
                    {
                        System.out.println(name + " has defeated an enemy samurai! \n"
                                + "Another enemy approaches!"
                        );
                    }    
            }
            if (hp <= 0)
            {
                System.out.println(name + " has fallen in battle. \n"
                        + "His journey ends here.");
                return;
            }
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

    @Override
    public void attack()
    {
        int x = ra.nextInt(5);
        try{
            for (int i = 1; i <= x; i++)
            {
                switch (i) 
                {
                    case 1:
                        System.out.println(name + " swings his katana \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    case 2:
                        System.out.println(name + " performs a quick slash \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    case 3:
                        System.out.println(name + " lunges forward with a thrust \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    case 4:
                        System.out.println(name + " spins and attacks with a wide arc \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    default:
                        System.out.println(name + " leaps into the air and brings his katana down with force \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                }
            }
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void defense()
    {
        int x = ra.nextInt(3);
        try{
            for (int i = 1; i <= x; i++)
            {
                switch (i) 
                {
                    case 1:
                        System.out.println(name + " raises his katana to block the attack \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    case 2:
                        System.out.println(name + " sidesteps the attack \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    default:
                        System.out.println(name + " gets in standing position");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                }
            }
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void run()
    {
        int x = ra.nextInt(3);
        try{
            for (int i = 1; i <= x; i++)
            {
                switch (i) 
                {
                    case 1:
                        System.out.println(name + " starts running \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    case 2:
                        System.out.println(name + " is halfway to safety \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    default:
                        System.out.println(name + " has reached safety! \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                }
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Your mission was interrupted!");
        }
    }
}
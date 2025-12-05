import java.util.Random;
import java.util.Scanner;

public class NaKi extends Thread implements GameCharacter
{
    // Attributes
    private String name;
    private int hp;
    private int atk;
    private int def;
    private Random ra = new Random();
    private Scanner sc = new Scanner(System.in);


    // Constructor
    public NaKi(int hp, int def, String name)
    {
        this.hp = hp;
        this.def = def;
        this.name = name;
    }

    // Methods
    @Override
    public void run()
    {

        System.out.println("Born in 1542's Edo, Harima Province, Nakagawa Kiyohide was a strategic samurai known for his intellect and tactical prowess. \n"
                + "He served under the powerful of Oda Nabunaga in his early life, showcasing his skills in various battles. \n"
                + name + " commanded troops with precision and was instrumental in several key victories. \n"
                + "One of the most notable moments in his career was during the Chigoku Campaign. \n" 
                + "The Chigoku Campaign was a series of military operations led by Oda Nabunaga to consolidate power in the Chigoku region of Japan. \n"
                + " "
                + name + " mustered his forces and executed a brilliant strategy that led to a decisive victory against the Mori clan. \n"
                + "arriving at the battlefield, " + name + " quickly assessed the situation and devised a plan to outflank the enemy. \n"
                + "Using the terrain to his advantage, he positioned his troops in a way that allowed them to launch a surprise attack. \n"
                + " "
                + "Getting closer to the enemy " + name + " leds a charge inoder to brake through the enemy lines. \n"
                + "What is " + name + " next move? \n");

                Enemy MoriEnemy = new Enemy("Mori Samurai", 25, 25, 10);
                Enemy MoriEnemy2 = new Enemy("Mori Samurai", 25, 25, 10);
                Enemy MoriEnemyBoss = new Enemy("Mori Terumoto", 50, 50, 30);

                Enemy[] wave1 = {MoriEnemy, MoriEnemy2, MoriEnemyBoss};

                //First wave of enemies
                for(Enemy enemy : wave1)
                {
                    int NakiDam = ra.nextInt(15, 20);

                    while(enemy.health() > 0 && hp > 0)
                    {
                        System.out.println( "1. Attack \n"
                                + "2. Defend");

                            switch(sc.nextInt())
                            {
                            case 1:
                                attack();
                                enemy.hp -= NakiDam;
                                System.out.println("You dealt " + NakiDam + " damage to the enemy! \n"
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
                            default:
                                System.out.println("Invalid choice. Defaulting to Hayami Morihisa.");
                                break;
                            }

                            if(enemy.health() <= 0)
                            {
                                System.out.println(name + " has defeated an enemy samurai! \n"
                                        + "Another enemy approaches!"
                                );
                            }
                    }

                    if(hp <= 0)
                    {
                        System.out.println(name + " has fallen in battle... \n"
                                + "The mission ends here.");
                        return; // Exit if the character has fallen
                    }
                }

                
        
        System.out.println(name + " and his forces have emerged victorious from the Chigoku Campaign! \n"
                + "Their strategic brilliance and unwavering determination have solidified their place in history as one of the most formidable samurai of their time. \n"
                + "With the Chigoku region under their control, " + name + " looks forward to new challenges and adventures that lie ahead in the ever-changing landscape of feudal Japan. \n"
                + "Years later, in 1582, Oda Nabunaga was betrayed by one of his own generals, Akechi Mitsuhide, leading to Nabunaga's death at Honno-ji Temple. \n"
                + "Leading " + name + " to become a roinin samurai, wandering the lands without a master. \n"
                + "Despite the loss of his lord, " + name + " continued to uphold the samurai code of honor and loyalty, seeking new opportunities to serve a worthy master. \n"
                + "His journey as a roinin samurai would eventually lead him to cross paths with another worthy master. \n"
                + "Toyotomi Hideyoshi! \n"
                + " "
                + name + " joined Hideyoshi's forces and played a crucial role in several key battles, including the Battle of Yamazaki and the Siege of Odawara. \n"
                + "The battle of Yamazaki was a decisive engagement fought in 1582 between the forces of Toyotomi Hideyoshi and Akechi Mitsuhide. \n"
                + name + " showcased his tactical brilliance once again, leading a flanking maneuver that caught Mitsuhide's forces off guard. \n"
                + "Arriving at the battlefield, he quickly assessed the situation and devised a plan to outflank the enemy. \n"
                + "Using the terrain to his advantage, he positioned his troops in a way that allowed them to launch a surprise attack. \n"
                + " "
                + "Getting closer to the enemy " + name + " leds a charge inoder to brake through the enemy lines. \n"
                + "What is " + name + " next move? \n");

                // Boosting health and defense for the next battle
                hp += 100;
                def += 100;

                Enemy MitsuEnemy = new Enemy("Mitsu Samurai", 30, 30, 10);
                Enemy MitsuEnemy2 = new Enemy("Mitsu Samurai", 30, 30, 10);
                Enemy MitsuEnemyBoss = new Enemy("Akechi Mitsuhide", 50, 50, 30);

                Enemy[] wave2 = {MitsuEnemy, MitsuEnemy2, MitsuEnemyBoss};

                //First wave of enemies
                for(Enemy enemy : wave2)
                {
                    int NakiDam = ra.nextInt(40, 50);

                    while(enemy.health() > 0 && hp > 0)
                    {
                        System.out.println( "1. Attack \n"
                                + "2. Defend");

                            switch(sc.nextInt())
                            {
                            case 1:
                                attack();
                                enemy.hp -= NakiDam;
                                System.out.println("You dealt " + NakiDam + " damage to the enemy! \n"
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
                            default:
                                System.out.println("Invalid choice. Defaulting to Hayami Morihisa.");
                                break;
                            }

                            if(enemy.health() <= 0)
                            {
                                System.out.println(name + " has defeated an enemy samurai! \n"
                                        + "Another enemy approaches!"
                                );
                            }
                    }

                    if(hp <= 0)
                    {
                        System.out.println(name + " has fallen in battle... \n"
                                + "The mission ends here."
                        );
                        return; // Exit if the character has fallen
                    }
                }

        System.out.println("With this victory, " + name + " has solidified his position as a trusted and valuable samurai under Toyotomi Hideyoshi's command. \n"
                + "His tactical brilliance and unwavering loyalty continue to make him a formidable force on the battlefield. \n"
                + "As Hideyoshi's power continues to grow, " + name + " looks forward to new challenges and opportunities to prove his worth as a samurai in the ever-changing landscape of feudal Japan. \n"
                + "Next up is the Battle of Shizugatake. We'll be looking a little closer in.\n");
    }

    public void start()
    {
       new Thread(() -> {
            try {
                for (int i = 1; i == 1; i++)
                {
                    System.out.println(name + " stands ready for the battle! \n");
                    while(Adventure.sharedBoss.health() > 0)
                    {
                        synchronized (Adventure.bossLock) {
                            if (Adventure.sharedBoss.health() <= 0) {
                                break; // Exit if boss is already defeated
                            }
                            attack();
                            int damage = ra.nextInt(20, 30);
                            Adventure.sharedBoss.hp -= damage;
                            System.out.println(name + " dealt " + damage + " damage to " + Adventure.sharedBoss.getName() + "! \n"
                                    + Adventure.sharedBoss.getName() + " current health is: " + Adventure.sharedBoss.health() + "\n");
                        }
                        // Simulate time between attacks
                        Thread.sleep(500);
                    } 
                    //Dies after boss is defeated
                    hp = 0;
                }
        } catch (InterruptedException e) {
        }
            }).start();
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
                        System.out.println(name + " readies his katana \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    case 2:
                        System.out.println(name + " swings his katana \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    case 3:
                        System.out.println(name + " thrusts forward to strike \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    case 4:
                        System.out.println(name + " backflips and strikes with precision \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    default:
                        System.out.println(name + " spins like a whirlwind slicing through the air \n");
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
                        System.out.println(name + " raises his body to block the attack \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    case 2:
                        System.out.println(name + " readies his hand to block the attack \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    default:
                        System.out.println(name + " swings his sword to deflect the attack \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                }
            }
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void running()
    {
        int x = ra.nextInt(3);
        try{
            for (int i = 1; i <= x; i++)
            {
                switch (i) 
                {
                    case 1:
                        System.out.println(name + " starts dashing \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    case 2:
                        System.out.println(name + " is almost to safety \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    default:
                        System.out.println(name + " safety is in reach! \n");
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

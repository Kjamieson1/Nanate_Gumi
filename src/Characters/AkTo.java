package Characters;

import java.util.Random;
import java.util.Scanner;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

import Main.Adventure;

public class AkTo extends Thread implements GameCharacter
{
    // Attributes
    private String name;
    private int hp;
    private int atk;
    private int def;
    private Random ra = new Random();
    private Scanner sc = new Scanner(System.in);
    private HttpClient client = HttpClient.newHttpClient();
    //Limit to 1 question per request, filtered by history category
    private String apiUrl = "https://the-trivia-api.com/v2/questions?limit=1&categories=history&difficulties=medium";
    HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(apiUrl))
                .GET()
                .build();


    // Constructor
    public AkTo(int hp, int def, String name)
    {
        this.hp = hp;
        this.def = def;
        this.name = name;
    }

    // Methods
    @Override
    public void run()
    {

        System.out.println("Born in the 1560's Edo period Japan in the Harima Province, Akashi Takenori was known for his fierce warrior spirit and unmatched combat skills. \n"
                + "From a young age, Akashi was trained in the art of swordsmanship and martial arts. \n"
                + "His bravery and tenacity on the battlefield earned him the respect of his peers and the fear of his enemies. \n"
                + "Sevring under Okita Nobunaga, A close allied with Toyoyomi Hideyoshi. \n"
                + name + " played a crucial role in many battles, often leading charges and inspiring his fellow samurai with his unwavering courage. \n"
                +"The battle of Sekigahara was one of his most notable engagements, where his strategic prowess and combat skills were on full display. \n"
                + " "
                + name + " Is faced with a group of enemy samurai blocking his path. \n"
                + "What should " + name + " do next? \n");

        Enemy SekiEnemy = new Enemy("Sekigahara Samurai", 10, 5, 2);
        Enemy SekiEnemy2 = new Enemy("Sekigahara Samurai", 10, 5, 2);
        Enemy SekiEnemyBoss = new Enemy("Ishida Mitsunari", 25, 20, 15);
        Enemy SekiEnemyBoss2 = new Enemy("Tokugawa Ieyasu", 25, 20, 15);

        Enemy[] wave1 = {SekiEnemy, SekiEnemy2, SekiEnemyBoss, SekiEnemyBoss2};

        //First wave of enemies
        for(Enemy enemy : wave1)
        {
            int AkToDam = ra.nextInt(30, 40);

            while(enemy.health() > 0 && hp > 0)
            {
                System.out.println( "1. Attack \n"
                        + "2. Defend\n"
                        + "3. Trivia Break");

                    switch(sc.nextInt())
                    {
                    case 1:
                        attack();
                        enemy.hp -= AkToDam;
                        System.out.println("You dealt " + AkToDam + " damage to the enemy! \n"
                                + enemy.name + " stricks back!");
                        
                        hp -= enemy.attack();

                        System.out.println("You received " + enemy.attack() + " damage! \n"
                                + "Your current health is: " + hp + "\n"
                                + enemy.name + " current health is: " + enemy.health() + "\n");
                        break;
                    case 2:
                        defense();
                        if (enemy.attack() - def < 0) {
                            System.out.println("Your defense was too high! You received no damage!\n"
                                    + "Your current health is: " + hp + "\n"
                                    + enemy.name + " current health is: " + enemy.health() + "\n");
                            break;
                        }
                        else
                        {
                            System.out.println("You took some damage despite your defense!\n");
                            hp -= (enemy.attack() - def);
                            System.out.println("Your current health is: " + hp + "\n"
                                    + enemy.name + " current health is: " + enemy.health() + "\n");
                            break;
                        }
                    case 3:
                        getTriviaQuestion();
                        System.out.println("Back to battle!\n");
                        break;
                    default:
                        System.out.println("Invalid choice. Defaulting to Hayami Morihisa.");
                        break;
                    }

                    if(enemy.health() <= 0)
                    {
                        System.out.println(name + " has defeated an enemy samurai! \n"
                                + "Another enemy approaches!");
                    }
            }

            if(hp <= 20)
            {
                System.out.println("The battle is lost we most retreat! \n"
                        + "The mission ends here.");
                break; // Exit if the character has fallen
            }
        }

        System.out.println("Heading back to camp, there is no sign of Okita Nobunaga. \n"
                + "After a few days of searching there is no more hope to find gim. \n"
                + "We go back home as ronins. Not sure where their paths will lead us next.\n"
                + ""
                + "Toyoyomi Hideyoshi offers " + name + " a position in his army. \n"
                + "Do you accept the position? \n"
                + "1. Yes \n"
                + "2. No \n");
        
        switch(sc.nextInt())
        {
            case 1:
                System.out.println(name + " has accepted the position under Toyoyomi Hideyoshi. \n"
                        + "He fights bravely in many battles and earns a reputation as one of Hideyoshi's most trusted samurai. \n");
                break;
            case 2:
                System.out.println(name + " has declined the position and chooses to remain a ronin. \n"
                        + "He wanders the countryside, taking on various jobs and missions to survive. \n"
                        + "Despite the hardships he faces, " + name + " remains true to his samurai code and continues to uphold his honor. \n"
                        + "The end.");
                return;
            default:
                System.out.println("Invalid choice. Defaulting to declining the position.");
                System.out.println(name + " has declined the position and chooses to remain a ronin. \n"
                        + "He wanders the countryside, taking on various jobs and missions to survive. \n"
                        + "Despite the hardships he faces, " + name + " remains true to his samurai code and continues to uphold his honor. \n"
                        + "The end.");
                return;
        }

        // Boosting health and defense for the next battle
        hp += 100;
        def += 100;

        System.out.println("Being a part of Toyoyomi Hideyoshi's army, " + name + " is sent out to fight in the Battle of Torinejima. \n"
                + "Facing off against the forces of the Shimazu clan, " + name + " stands ready for battle. \n"
                + " What should " + name + " do next? \n");

        Enemy ShimaEnemy = new Enemy("Shimazu Samurai", 10, 5, 2);
        Enemy ShimaEnemy2 = new Enemy("Shimazu Samurai", 10, 5, 2);
        Enemy ShimaEnemyBoss = new Enemy("Shimazu Yoshihiro", 25, 20, 15);
        

        Enemy[] wave2 = {ShimaEnemy, ShimaEnemy2, ShimaEnemyBoss};

        //Second wave of enemies
        for(Enemy enemy : wave2)
        {
            int AkToDam = ra.nextInt(40, 50);

            while(enemy.health() > 0 && hp > 0)
            {
                System.out.println( "1. Attack \n"
                        + "2. Defend");

                    switch(sc.nextInt())
                    {
                    case 1:
                        attack();
                        enemy.hp -= AkToDam;
                        System.out.println("You dealt " + AkToDam + " damage to the enemy! \n"
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
                                + "Another enemy approaches!");
                    }
            }

            if(hp <= 20)
            {
                System.out.println("The battle is lost we most retreat! \n"
                        + "The mission ends here.");
                return; // Exit if the character has fallen
            }
        }

        System.out.println(name + " has successfully helped Toyotomi Hideyoshi in unifying Japan! \n"
                + "Through his loyalty and skill, he has risen from a ronin to a respected samurai in one of the most powerful armies in Japan. \n"
                + "His journey is a testament to the resilience and determination of the samurai spirit. \n"
                + "Though this is not the end of " + name + "'s story, it marks a significant chapter in his life as a samurai. \n"
                + "Other chapter which is hard to look on is the battle of Shizugatake. \n"
                + "Let's take a closer look at what happened there...\n");
        
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
                        System.out.println(name + " jumps into the air swinging the katana \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    case 2:
                        System.out.println(name + " dashes down creating a powerful strike \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    case 3:
                        System.out.println(name + " thrusts forward to strike \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    case 4:
                        System.out.println(name + " slides under opponent slicing his back \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    default:
                        System.out.println(name + " stabbing through the abdomen \n");
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
                        System.out.println(name + " looks his opponent in the eye \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    case 2:
                        System.out.println(name + " readys his katana \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    default:
                        System.out.println(name + " blocks the attack firmly \n");
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
                        System.out.println(name + " wonders in confusion \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    case 2:
                        System.out.println(name + " runs with all his might \n");
                        Thread.sleep(ra.nextInt(1000) + 500);
                        break;
                    default:
                        System.out.println(name + " reaches his location \n");
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
    
    // Method to fetch and display trivia question from API
    private void getTriviaQuestion()
    {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() >= 200 && response.statusCode() < 300)
            {
                String responseBody = response.body();
                
                // Extract question
                int questionStart = responseBody.indexOf("\"text\":\"") + 8;
                int questionEnd = responseBody.indexOf("\"", questionStart);
                
                // Extract correct answer
                int answerStart = responseBody.indexOf("\"correctAnswer\":\"") + 17;
                int answerEnd = responseBody.indexOf("\"", answerStart);
                
                if (questionStart > 7 && questionEnd > questionStart && answerStart > 16 && answerEnd > answerStart)
                {
                    String question = responseBody.substring(questionStart, questionEnd);
                    String correctAnswer = responseBody.substring(answerStart, answerEnd);
                    
                    System.out.println("\n=== Trivia Break ===");
                    System.out.println("Question: " + question);
                    System.out.print("Your answer: ");
                    
                    sc.nextLine(); // Clear the buffer from previous nextInt()
                    String userAnswer = sc.nextLine();
                    
                    if (userAnswer.trim().equalsIgnoreCase(correctAnswer))
                    {
                        System.out.println("Correct! You gain 10 HP!");
                        hp += 10;
                    }
                    else
                    {
                        System.out.println("Wrong! The correct answer was: " + correctAnswer);
                        System.out.println("You lose 5 HP!");
                        hp -= 5;
                    }
                    System.out.println("Current HP: " + hp);
                    System.out.println("==================\n");
                }
                else
                {
                    System.out.println("\nCould not parse trivia question.\n");
                }
            }
        }
        catch (IOException | InterruptedException e)
        {
            System.out.println("Failed to fetch trivia: " + e.getMessage());
        }
    }
}

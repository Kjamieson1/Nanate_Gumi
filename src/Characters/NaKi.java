package Characters;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import java.util.Scanner;

import Main.Adventure;

public class NaKi extends Thread implements GameCharacter
{
    // Attributes
    private String name;
    private int hp;
    private int atk;
    private int def;
    private int played = 0;
    private boolean isAlive = true;
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

        System.out.println("========== Nakagawa Kiyohide =========== \n"
                + "Born in 1542's Edo, Harima Province, Nakagawa Kiyohide was a strategic samurai known for his intellect and tactical prowess. \n"
                + "He served under the powerful of Oda Nabunaga in his early life, showcasing his skills in various battles. \n"
                + name + " commanded troops with precision and was instrumental in several key victories. \n"
                + "One of the most notable moments in his career was during the Chigoku Campaign. \n" 
                + "The Chigoku Campaign was a series of military operations led by Oda Nabunaga to consolidate power in the Chigoku region of Japan. \n"
                + " \n"
                + name + " mustered his forces and executed a brilliant strategy that led to a decisive victory against the Mori clan. \n"
                + "arriving at the battlefield, " + name + " quickly assessed the situation and devised a plan to outflank the enemy. \n"
                + "Using the terrain to his advantage, he positioned his troops in a way that allowed them to launch a surprise attack. \n"
                + " \n"
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
                        System.out.println( "_____________________________ \n"
                                + "1. Attack \n"
                                + "2. Defend \n"
                                + "3. Trivia Break \n"
                                + "_____________________________ \n");

                            switch(sc.nextInt())
                            {
                            case 1:
                                attack();
                                enemy.hp -= NakiDam;
                                System.out.println("_____________________________ \n"
                                        + "You dealt " + NakiDam + " damage to the enemy! \n"
                                        + enemy.name + " stricks back!");
                        
                                hp -= enemy.attack();

                                System.out.println("You received " + enemy.attack() + " damage! \n"
                                        + "\n"
                                        + "Your current health is: " + hp + "\n"
                                        + enemy.name + " current health is: " + enemy.health() + "\n");
                                break;
                            case 2:
                                defense();
                                if (enemy.attack() - def < 0) {
                                    System.out.println("_____________________________ \n"
                                            +"Your defense was too high! You received no damage!\n"
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
                                System.out.println("_____________________________ \n"
                                        + "Back to battle!\n");
                                break;
                            default:
                                System.out.println("Invalid choice.");
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
                        isAlive = false;
                        return; // Exit if the character has fallen
                    }
                }

                
        
        System.out.println("========== Nakagawa Kiyohide =========== \n"
                + name + " and his forces have emerged victorious from the Chigoku Campaign! \n"
                + "Their strategic brilliance and unwavering determination have solidified their place in history as one of the most formidable samurai of their time. \n"
                + "With the Chigoku region under their control, " + name + " looks forward to new challenges and adventures that lie ahead in the ever-changing landscape of feudal Japan. \n"
                + "Years later, in 1582, Oda Nabunaga was betrayed by one of his own generals, Akechi Mitsuhide, leading to Nabunaga's death at Honno-ji Temple. \n"
                + "Leading " + name + " to become a roinin samurai, wandering the lands without a master. \n"
                + "Despite the loss of his lord, " + name + " continued to uphold the samurai code of honor and loyalty, seeking new opportunities to serve a worthy master. \n"
                + "His journey as a roinin samurai would eventually lead him to cross paths with another worthy master. \n"
                + "Toyotomi Hideyoshi! \n"
                + " \n"
                + name + " joined Hideyoshi's forces and played a crucial role in several key battles, including the Battle of Yamazaki and the Siege of Odawara. \n"
                + "The battle of Yamazaki was a decisive engagement fought in 1582 between the forces of Toyotomi Hideyoshi and Akechi Mitsuhide. \n"
                + name + " showcased his tactical brilliance once again, leading a flanking maneuver that caught Mitsuhide's forces off guard. \n"
                + "Arriving at the battlefield, he quickly assessed the situation and devised a plan to outflank the enemy. \n"
                + "Using the terrain to his advantage, he positioned his troops in a way that allowed them to launch a surprise attack. \n"
                + " \n"
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
                        System.out.println( "_____________________________ \n"
                                + "1. Attack \n"
                                + "2. Defend \n"
                                + "3. Trivia Break \n"
                                + "_____________________________ \n");

                            switch(sc.nextInt())
                            {
                            case 1:
                                attack();
                                enemy.hp -= NakiDam;
                                System.out.println("_____________________________ \n"
                                        + "You dealt " + NakiDam + " damage to the enemy! \n"
                                        + enemy.name + " stricks back!");
                        
                                hp -= enemy.attack();

                                System.out.println("You received " + enemy.attack() + " damage! \n"
                                        + "\n"
                                        + "Your current health is: " + hp + "\n"
                                        + enemy.name + " current health is: " + enemy.health() + "\n");
                                break;
                            case 2:
                                defense();
                                if (enemy.attack() - def < 0) {
                                    System.out.println("_____________________________ \n"
                                            + "Your defense was too high! You received no damage!\n"
                                            + "Your current health is: " + hp + "\n"
                                            + enemy.name + " current health is: " + enemy.health() + "\n");
                                    break;
                                }
                                else
                                {
                                    System.out.println("_____________________________ \n"
                                            + "You took some damage despite your defense!\n");
                                    hp -= (enemy.attack() - def);
                                    System.out.println("Your current health is: " + hp + "\n"
                                            + enemy.name + " current health is: " + enemy.health() + "\n");
                                    break;
                                }
                            case 3:
                                getTriviaQuestion();
                                System.out.println("_____________________________ \n"
                                        + "Back to battle!\n");
                                break;
                            default:
                                System.out.println("Invalid choice.");
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
                        isAlive = false;
                        return; // Exit if the character has fallen
                    }
                }

        System.out.println("========== Nakagawa Kiyohide =========== \n"
                + "With this victory, " + name + " has solidified his position as a trusted and valuable samurai under Toyotomi Hideyoshi's command. \n"
                + "His tactical brilliance and unwavering loyalty continue to make him a formidable force on the battlefield. \n"
                + "As Hideyoshi's power continues to grow, " + name + " looks forward to new challenges and opportunities to prove his worth as a samurai in the ever-changing landscape of feudal Japan. \n"
                + "Next up is the Battle of Shizugatake. We'll be looking a little closer in.\n");

        played = 1;
    }

    public void start()
    {
        if (!isAlive) {
            System.out.println(name + " has fallen and cannot continue the mission.");
            return; // Exit if the character has fallen
        }
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
                
                // Extract incorrect answers array
                int incorrectStart = responseBody.indexOf("\"incorrectAnswers\":[") + 20;
                int incorrectEnd = responseBody.indexOf("]", incorrectStart);
                
                if (questionStart > 7 && questionEnd > questionStart && answerStart > 16 && answerEnd > answerStart)
                {
                    String question = responseBody.substring(questionStart, questionEnd);
                    String correctAnswer = responseBody.substring(answerStart, answerEnd);
                    
                    // Parse incorrect answers
                    String incorrectAnswersStr = responseBody.substring(incorrectStart, incorrectEnd);
                    String[] incorrectAnswers = incorrectAnswersStr.replace("\"", "").split(",");
                    
                    // Create choices array and shuffle
                    String[] choices = new String[4];
                    choices[0] = correctAnswer;
                    for (int i = 0; i < 3 && i < incorrectAnswers.length; i++) {
                        choices[i + 1] = incorrectAnswers[i];
                    }
                    
                    // Shuffle choices
                    for (int i = choices.length - 1; i > 0; i--) {
                        int j = ra.nextInt(i + 1);
                        String temp = choices[i];
                        choices[i] = choices[j];
                        choices[j] = temp;
                    }
                    
                    System.out.println("\n=== Trivia Break ===");
                    System.out.println("Question: " + question);
                    for (int i = 0; i < choices.length; i++) {
                        System.out.println((i + 1) + ". " + choices[i]);
                    }
                    System.out.print("Your answer (1-4): ");
                    
                    sc.nextLine(); // Clear the buffer from previous nextInt()
                    int choice = sc.nextInt();
                    
                    if (choice >= 1 && choice <= 4 && choices[choice - 1].equalsIgnoreCase(correctAnswer))
                    {
                        System.out.println("Correct! You gain 20 HP!");
                        hp += 20;
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

    public boolean playedCheck()
    {
        if(played == 1)
            return true;
        else
            return false;
    }  
   
}

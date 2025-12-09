package Characters;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import java.util.Scanner;

import Main.Adventure;

public class HaMo extends Thread implements GameCharacter
{
    // Attributes
    private String name;
    private int hp;
    private int atk;
    private int def;
    private int played = 0;
    private boolean isAlive = true;
    private Scanner sc = new Scanner(System.in);
    private Random ra = new Random();
    private HttpClient client = HttpClient.newHttpClient();
    //Limit to 1 question per request, filtered by history category
    private String apiUrl = "https://the-trivia-api.com/v2/questions?limit=1&categories=history&difficulties=medium";
    HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(apiUrl))
                .GET()
                .build();


    // Constructor
    public HaMo(int hp, int def, String name)
    {
        this.hp = hp;
        this.def = def;
        this.name = name;
    }

    // Methods
    @Override
    public void run()
    {

        System.out.println("========== Hayami Morihisa =========== \n"
                + "Born in the 1550's Edo period Japan, located in the Omi province. \n"
                + name +" is a simi wealthy samurai whos family owns lands in the area. \n"
                + "Serving under the Azai clan. \n"
                + "The Azai clan's leader, Azai Nagamasa recently allied with a powerful daimyo, Asakura. \n"
                + "In 1573 Oda Nobunaga invades the Omi province with the intention of conquering. \n"
                + name + " must now choose to defend his clan and province or flee to safety. \n");

        System.out.println("_____________________________ \n"
                + "1. Defend the province \n"
                + "2. Flee to safety \n"
                + "_____________________________\n");

        switch(sc.nextInt())
        {
            //Chosing to defend the province
            case 1:
                System.out.println(" \n"
                        +"========== Hayami Morihisa =========== \n"
                        + "Choosing to defend the province! \n"
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
                        System.out.println("_____________________________ \n"
                                +"1. Attack \n"
                                + "2. Defend \n"
                                + "3. Trivia Break\n"
                                + "_____________________________ \n");

                            switch(sc.nextInt())
                            {
                            case 1:
                                attack();
                                enemy.hp -= HaMoDam;
                                System.out.println("_____________________________ \n"
                                        +"You dealt " + HaMoDam + " damage to the enemy! \n"
                                        + enemy.name + " stricks back!");
                        
                                hp -= enemy.attack();

                                System.out.println("You received " + enemy.attack() + " damage! \n"
                                        +"\n"
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
                                        + "Another enemy approaches!");
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
                }
                // Suppose to lose fight Ending scenario
                // in real life the castle falls ending the Azai clan
                System.out.println("========== Hayami Morihisa =========== \n"
                        +"The battle is fierce \n"
                        + "Azai castle falls to the Oda forces. \n"
                        + name + " fought bravely buy was ultimataly defeated"); 
                return;

            // Choosing to flee to safety
            case 2:
                System.out.println(name + " chose to flee to safety! \n");
                running();
                System.out.println("========== Hayami Morihisa =========== \n"
                        + name + " escaped the battle but lost his home! \n"
                        + "Not sure where to go " + name + " wanders aimlessly around the Omi province. \n"
                        + "After a few days of wondering as a ronin, he runs into Toyotomi Hideyoshi. \n"
                        + "Impressed by " + name + "'s skills, Hideyoshi offers him a position in his growing forces. \n"
                        + "Accepting the offer, he joins Hideyoshi's army and begins a new chapter in his life. \n");
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }

        System.out.println("========== Hayami Morihisa =========== \n"
                +"14 Years after the fall of the Azai clan " + name + " has risen through the ranks of Hideyoshi's forces. \n"
                + "He has become one of Hideyoshi's close retainers and a trusted samurai in his army. \n"
                + "Participating in many battles and campaigns, he has proven his loyalty and skill on the battlefield. \n"
                + name + " next mission is to compain with Hideyoshi to unify Japan under his rule. \n"
                + "He is to set out to conquer Odawara"
                + "\n");

        // Boosting stats for next battle
        hp += 100;
        def += 100;

        System.out.println("When arriving at the Odawara castle, " + name + " is met with a strong force ahead of him. \n"
                + "Being reminded of his past losses and the fall of his previous clan. \n"
                + "And a chance to redeem his clans honor. \n"
                + " \n"
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
                System.out.println("_____________________________ \n"
                        + "1. Attack \n"
                        + "2. Defend \n"
                        + "3. Trivia Break \n"
                        + "_____________________________ \n");

                    switch(sc.nextInt())
                    {
                    case 1:
                        attack();
                        enemy.hp -= HaMoDam;
                        System.out.println("_____________________________ \n"
                                +"You dealt " + HaMoDam + " damage to the enemy! \n"
                                + enemy.name + " stricks back!");
                        
                        hp -= enemy.attack();

                        System.out.println("You received " + enemy.attack() + " damage! \n"
                                +"\n"
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
                                + "There could be another one!"
                        );
                    }    
            }
            if (hp <= 0)
            {
                System.out.println(name + " has fallen in battle. \n"
                        + "His journey ends here.");
                isAlive = false;
                return;
            }
        }

        System.out.println("========== Hayami Morihisa =========== \n"
                + name + " has successfully helped Toyotomi Hideyoshi in unifying Japan! \n"
                + "Through his loyalty and skill, he has risen from a ronin to a respected samurai in one of the most powerful armies in Japan. \n"
                + "His journey is a testament to the resilience and determination of the samurai spirit. \n"
                + "Though this is not the end of " + name + "'s story, it marks a significant chapter in his life as a samurai. \n"
                + "Other chapter which is hard to look on is the battle of Shizugatake. \n"
                + "Let's take a closer look at what happened there...\n");

    
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
                        System.out.println("_____________________________ \n"
                                + name + " swings his katana \n");
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
                        System.out.println("_____________________________ \n"
                                + name + " raises his katana to block the attack \n");
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
    public void running()
    {
        int x = ra.nextInt(3);
        try{
            for (int i = 1; i <= x; i++)
            {
                switch (i) 
                {
                    case 1:
                        System.out.println("_____________________________ \n"
                                + name + " starts running \n");
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
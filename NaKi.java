import java.util.Random;

public class NaKi extends Thread implements GameCharacter
{
    // Attributes
    private String name;
    private int hp;
    private int atk;
    private int def;

    // Constructor
    public NaKi(int hp, int atk, int def, String name)
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
        System.out.println("The brave knight sets out on a quest to slay the dragon!");
        run();
        System.out.println("The knight returns victorious!");
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
            for (int i = 1; i <= 5; i++)
            {
                System.out.println(name + ": Step " + i);
                Thread.sleep(rand.nextInt(1000) + 500);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("The knight's adventure was interrupted!");
        }
    }
    
    @Override
    public void act()
    {

    }
    
}

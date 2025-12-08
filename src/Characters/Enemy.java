package Characters;

public class Enemy 
{
    // Attributes
    protected String name;
    protected  int hp;
    protected int atk;
    protected int def;
    
    // Constructor

    public Enemy(String name, int hp, int atk, int def)
    {
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.name = name;
    }

    // Methods

    public String getName()
    {
        return name;
    }

    public int attack()
    {
        return atk;
    }

    public int defense()
    {
        return def;
    }

    public int health()
    {
        if (hp <= 0)
        {
           return 0;
        }
        return hp;
    }
    
}

package Characters;

// GameCharacter interface defining the common behaviors for all the game characters
public interface GameCharacter 
{
    public int health();
    public void attack();
    public void defense();
    public void run();
    public void running();
    private void getTriviaQuestion();
}

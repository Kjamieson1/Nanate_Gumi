### Nanate-gumi: A Samurai Adventure
Name: Kenneth Jamieson
Course: CSC325
Game title: Nanate-gumi
CSC325-FA25

## Summary
Nanate-gumi is a text based, multithreading adventure in order to show advenced OOP and concurrency in Java. There are three different characters Hayami Norihasa, Nakagawa Kiyohide or Akashi Takenori which are operating concurrently to fight the final boss. Each are within its own threads with there own unique goals, abilities, and story events. This world has shared event logs and final boss. 

## Technologies used

# Languages
JavaSE-25

# Libraries
java.util
java.net.http
java.io
java.lang.Thread

# Frameworks: 
None

## Setup Instructions

### Requirements
- Java Development Kit (JDK) 11 or higher
- VS Code with Extension Pack for Java (or any Java IDE)

### Running the Program
1. **Clone the Repository**
   ```bash
   git clone https://github.com/Kjamieson1/Nanate_Gumi.git
   cd Nanate_Gumi
   ```

2. **Compile the Code**
   ```bash
   javac -d bin src/Main/*.java src/Characters/*.java
   ```

3. **Run the Game**
   ```bash
   java -cp bin Main.Adventure
   ```

4. **Using VS Code Run Button**
   - Open the project folder in VS Code
   - Open `Adventure.java`
   - Press F5 or click the Run button

### How to Play
- Choose one of three samurai to experience their backstory (1-3)
- Make combat decisions during battles (Attack/Defend/Triva(to get health))
- After all backstories complete, the three samurai fight the shared boss together
- Watch the threads run in parallel as they attack simultaneously

## Thread Synchronization Approach

This program demonstrates multithreading and synchronization using the following approach:

### Thread Architecture
- Each samurai character extends `Thread` and implements `GameCharacter` interface
- The `run()` method contains individual backstory (runs sequentially when selected)
- The `start()` method creates a new lambda thread for the shared boss battle (runs in parallel)

### Synchronization Strategy
**Shared Resources:**
- `Adventure.sharedBoss` - A single Enemy object representing the final boss at Shizugatake
- `Adventure.bossLock` - An Object used as a synchronization lock

**Critical Section Protection:**
```java
synchronized (Adventure.bossLock) {
    if (Adventure.sharedBoss.health() <= 0) {
        break; // Prevent attacking dead boss
    }
    // Calculate and apply damage
    Adventure.sharedBoss.hp -= damage;
    // Display results
}
```

### Why This Approach?
1. **Prevents Race Conditions** - Multiple threads accessing `sharedBoss.hp` simultaneously could cause incorrect health values
2. **Ensures Thread Safety** - Only one samurai can modify boss health at a time
3. **Demonstrates Concurrency** - Three threads run in parallel but coordinate access to shared resource

## API
https://the‑trivia‑api.com/v2/questions
Filter for history content.
Pick and midify ones to fit game.

## Method Level Documentation

| Class | Method | Description | Usage |


| GameCharacter | `health()` | Returns current health points | Used to check if character is alive |
| GameCharacter | `attack()` | Performs attack animation | Called during combat |
| GameCharacter | `defense()` | Performs defense animation | Called when defending |
| GameCharacter | `run()` | Thread entry point for backstory | Plays character's backstory in main thread |
| GameCharacter | `start()` | Thread entry point for team mission | Executes shared boss battle in parallel |
| GameCharacter | `running()` | Movement animation | Shows character movement |
| HaMo/NaKi/AkTo | `run()` | Character backstory execution | Tells each samurai's individual story |
| HaMo/NaKi/AkTo | `start()` | Team battle execution | Synchronized attack on shared boss |
| Adventure | `main()` | Program entry point | Handles user selection and orchestrates game flow |
| Enemy | `health()` | Returns enemy health | Used to check enemy status |
| Enemy | `attack()` | Returns enemy attack value | Used in combat calculations |
| Enemy | `defense()` | Returns enemy defense value | Used in damage reduction |
| AkTo | `getTriviaQuestion()` | Fetches trivia from API | Called during battle for bonus HP | 
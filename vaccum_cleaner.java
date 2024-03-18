import java.util.*;

class VacuumEnvironment {

    public boolean[] roomState;
    public int agentLocation;
    public int cleanCount;
    public Random random;

    public VacuumEnvironment(int roomSize) {
        roomState = new boolean[roomSize];
        random = new Random();
        reset();
    }

    public void reset() {
        for (int i = 0; i < roomState.length; i++) {
            roomState[i] = random.nextBoolean(); // Randomly set dirt in rooms
        }
        agentLocation = random.nextInt(roomState.length); // Randomly place the agent
        cleanCount = 0; // Reset clean count
    }

    public boolean isDirty(int location) {
        return roomState[location];
    }

    public void clean(int location) {
        roomState[location] = false;
        cleanCount++;
    }

    public int getAgentLocation() {
        return agentLocation;
    }

    public int getCleanCount() {
        return cleanCount;
    }

    public void moveAgent(int location) {
        agentLocation = location;
    }
}

class VacuumAgent {
    private VacuumEnvironment environment;

    public VacuumAgent(VacuumEnvironment environment) {
        this.environment = environment;
    }

    public void cleanRoom() {

        int currentLocation = environment.getAgentLocation();
        if (environment.isDirty(currentLocation)) {
            environment.clean(currentLocation);
        }
    }


    public void moveRandomly() {
        int roomSize = environment.roomState.length;
        int nextLocation = (environment.getAgentLocation() + 1) % roomSize; // Move to the next room cyclically
        environment.moveAgent(nextLocation);
    }
}

public class vaccum_cleaner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter room size");
        int roomSize = sc.nextInt();
        int iterations = 1000;

        VacuumEnvironment environment = new VacuumEnvironment(roomSize);
        VacuumAgent agent = new VacuumAgent(environment);

        for (int i = 0; i < iterations; i++) {
            agent.cleanRoom();
            agent.moveRandomly();
        }

        System.out.println("Performance:");
        System.out.println("Cleaned rooms: " + environment.getCleanCount());
    }
}

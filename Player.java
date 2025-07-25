public class Player {
    private String name;
    private int position;
    private int sightings;
    private int score;

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.sightings = 0;
    }
    public Player(){
        this.name = "Player";
        this.position = 0;
        this.sightings = 0;
    }

    public void move(int steps) {
        position += steps;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int pos) {
        position = pos;
    }

    public void addSightings(int count) {
        sightings += count;
    }

    public int getSightings() {
        return sightings;
    }

    public String getName() {
        return name;
    }
    public void calculateScore(int baseScore) {
        score = score+ baseScore;
    }
    public int getScore() {
        return score;
    }
}
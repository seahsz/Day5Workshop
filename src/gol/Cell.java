package gol;

public class Cell {

    private String alive = CONSTANTS.DEAD;
    private int x;
    private int y;
    private int neighAlive;

    public Cell(String alive, int x, int y) {
        this.alive = alive;
        this.x = x;
        this.y = y;
    }

    // Getters and Setters
    public String getAlive() { return alive; }
    public void setAlive(String alive) { this.alive = alive; }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public int getNeighAlive() { return neighAlive; }
    public void setNeighAlive(int neighAlive) { this.neighAlive = neighAlive; }

    

    // evaluate a cell --> is it better to do it in cell or grid?
    // problem with doing in cell --> hard to tell whether got someone beside or not

    
    
}

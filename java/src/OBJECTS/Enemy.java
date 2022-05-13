package OBJECTS;

import processing.core.PVector;

public class Enemy extends VisualSetup{

    public PVector position, center;
    public int size;
    private VisualSetup p;
    private boolean collision;
    
    public Enemy(int width, int height, int health, int damage, VisualSetup p){
        this.position = new PVector(random(width), random(height));
        this.center = new PVector(width/2, height/2);
        this.size = (int)random(10, 50);
        this.p = p;

    }

    public void updateEnemy(){
        p.fill(255);
        p.stroke(0, 100, 100);
        p.rect(position.x, position.y, size, size);
        p.roundWin = true;
    }
}

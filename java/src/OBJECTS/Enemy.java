package OBJECTS;

import processing.core.PVector;

public class Enemy extends VisualSetup{

    public PVector position, center, velocity;
    public int size;
    private VisualSetup p;
    //private boolean collision;
    
    public Enemy(int width, int height, int health, int damage, VisualSetup p){
        this.position = generatePosition(width, height);
        this.center = new PVector(width/2, height/2);
        this.velocity = PVector.sub(center, position).div(10);
        this.size = (int)random(25, 50);
        this.p = p;

    }

    public void updateEnemy(){
        p.fill(255);
        p.stroke(0, 100, 100);
        position.add(velocity);
        p.rect(position.x, position.y, size, size);
        System.out.println(position);
    }

    public PVector generatePosition(int width, int height){

        int side = (int)random(4);
        PVector position;

        switch(side){
            case 0: System.out.println("0");
            position = new PVector(random(0, width), -50);
            return position;

            case 1: System.out.println("1");
            position = new PVector(width + 50, random(0, height));
            return position;

            case 2: System.out.println("2");
            position = new PVector(random(0, width), height + 50);
            return position;

            case 3: System.out.println("3");
            position = new PVector(-50, random(0, height));
            return position;

            default:
            System.out.println("default case");
            return new PVector(0, 0);
        }
    }
}

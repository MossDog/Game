package OBJECTS;

import processing.core.PVector;

public class Enemy extends VisualSetup{

    public PVector position, center, velocity;
    public int size;
    public float distance, health, damage;
    private VisualSetup p;
    private boolean collision;
    


    public Enemy(int width, int height, int health, int damage, float speed, VisualSetup p){

        this.position = generatePosition(width, height);
        this.center = new PVector(width/2, height/2);
        this.velocity = PVector.sub(center, position).div(speed);
        this.size = (int)random(25, 50);
        this.health = health;
        this.damage = damage;
        this.p = p;

    }



    public void updateEnemy(int height){

        //CALCULATE COLLISION
        collision = circleRect(height);
        if(collision == false){
            position.add(velocity);
        }

        //DRAW ENEMY TO SCREEN
        p.fill(0, 0, 0);
        p.strokeWeight(3);
        p.stroke(0, 100, 100);
        p.rect(position.x, position.y, size, size);

    }



    public PVector generatePosition(int width, int height){

        int side = (int)random(4);
        PVector position;

        switch(side){
            case 0:
            position = new PVector(random(-size, width), -50);
            return position;

            case 1:
            position = new PVector(width + 50, random(0, height));
            return position;

            case 2:
            position = new PVector(random(0, width), height + 50);
            return position;

            case 3:
            position = new PVector(-50, random(0, height));
            return position;

            default:
            System.out.println("default case");
            return new PVector(0, 0);

        }
    }


    
    // Enemy-Player Collision detection
    boolean circleRect(int height){

        // temporary variables to set edges for testing
        float testX = center.x;
        float testY = center.y;
        
        // which edge is closest?
        // left edge
        if (center.x < position.x){
            testX = position.x;
        }else
        // right edge
        if (center.x > position.x +  size){
            testX = position.x +  size;
        }
    
        // which edge is closest?
        // top edge
        if (center.y < position.y){
            testY = position.y;
        }   
        else
        // bottom edge
        if (center.y > position.y +  size){
            testY = position.y +  size;
        } 
        
        // get distance from closest edges
        float distX = center.x-testX;
        float distY = center.y-testY;
        distance = sqrt((distX*distX) + (distY*distY));
        //System.out.println("distx: " + distX + "\nDisty: " + distY + "\ndistance: " + distance + "\n(int)(height * 0.2f)" + (int)(height * 0.2f));
        
        // if the distance is less than the radius, collision!
        if (distance <= (int)(height * 0.2f)/2){
            return true;
        }

        return false;
    }
}

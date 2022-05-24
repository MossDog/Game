package OBJECTS;

import java.util.ArrayList;

import processing.core.PVector;

public class Player extends VisualSetup{

    //declare variables.
    public PVector position;
    public int diameter, damage;
    private VisualSetup p;

    //cosntructor
    public Player(int width, int height, int damage, VisualSetup p){
        this.position = new PVector(width/2, height/2);
        this.diameter = (int)(height * 0.2f);
        this.p = p;
        this.damage = 10;
    }

    public void updatePlayer(){
        p.fill(0, 0, 100);
        p.strokeWeight(3);
        p.stroke(abs(p.frameCount%360), 100, 100);
        p.ellipse(position.x, position.y, diameter, diameter);
    }

    public void fire(ArrayList<Enemy> enemiesArray){
        float closest = Float.MAX_VALUE;
        //dummy Enemy object
        Enemy enemy = new Enemy(0, 0, 0, 0, 0, p);

        for(Enemy e : enemiesArray){
            if(e.distance < closest){
                closest = e.distance;
                enemy = e;
            }
        }

        PVector rectCenter = new PVector(enemy.position.x + enemy.size/2, enemy.position.y + enemy.size/2);
        PVector circleSurf = findCircleSurf(rectCenter, position, (float)diameter/2);

        p.line(circleSurf.x, circleSurf.y, rectCenter.x, rectCenter.y);

    }


    PVector findCircleSurf(PVector rectCenter, PVector circleCenter, float radius){
        PVector offset  = PVector.sub(circleCenter, rectCenter);          
        offset.normalize();
        PVector p2 = PVector.sub(circleCenter, offset.mult(radius));
        return p2;
    }

}
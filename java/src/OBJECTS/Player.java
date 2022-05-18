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

        PVector riseRun = PVector.sub(position, enemy.position);
        float angle = atan(riseRun.x/riseRun.y);
        p.strokeWeight(12);
        p.line(position.x + cos(angle), position.y + sin(angle), enemy.position.x + (enemy.size/2), enemy.position.y + enemy.size/2);
    }

/* 
    cos(radians(angle)) * radius;
    sin(radians(angle)) * radius;

    angle is a value between 0 and 359
    radius gives you the length of the line
    atan is used to get the angle of the beam in radians using rise/run
*/

}
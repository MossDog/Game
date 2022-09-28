package OBJECTS;

import java.util.ArrayList;

import processing.core.PVector;

public class Player extends VisualSetup{

    //declare variables.
    public PVector position;
    public long prevTimeDamage, prevtimeRegen;
    public float diameter, health, maxHealth, healthRegen, regenCooldown, damage, damageCooldown,  range, points;
    private VisualSetup v;



    //constructor
    public Player(int width, int height, float maxHealth, float healthRegen, float regenCooldown, float damage, float damageCooldown, float range, VisualSetup applet){

        this.position = new PVector(width/2, height/2);
        this.diameter = (int)(height * 0.2f);
        this.prevTimeDamage = System.currentTimeMillis();
        this.prevtimeRegen = System.currentTimeMillis();
        this.v = applet;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.healthRegen = healthRegen;
        this.regenCooldown = regenCooldown;
        this.damage = damage;
        this.damageCooldown = damageCooldown;
        this.range = range;
        this.points = 0;

    }//end method



    public void updatePlayer(ArrayList<Enemy> enemies){

        //DRAW PLAYER
        v.noStroke();
        v.fill(abs(v.frameCount%360), 100, 100);
        v.ellipse(position.x, position.y, diameter, diameter);
        v.fill(0, 0, 100);
        v.ellipse(position.x, position.y, diameter-16, diameter-16);

        //DRAW PLAYER RANGE
        v.noFill();
        v.strokeWeight(4);
        v.stroke(0, 100, 100, 70);
        v.circle(position.x, position.y, range * 2);

        //IF ENEMIES EXIST, PLAYER CONSIDERS FIRING
        if(enemies.size()>0) drawBeam(enemies);
        regenHealth();

    }//end method



    private void regenHealth() {

        long timePassedRegen = System.currentTimeMillis() - prevtimeRegen;

        if(timePassedRegen > regenCooldown && health + healthRegen < maxHealth){

            prevtimeRegen = System.currentTimeMillis();
            health += healthRegen;

        }//end if

    }



    public void drawBeam(ArrayList<Enemy> enemies){

        float closest = Float.MAX_VALUE;

        //dummy Enemy object
        Enemy enemy = null;

        //find closest enemy to player
        for(Enemy e : enemies){

            if(e.distance < closest && e.health > 0){

                closest = e.distance;
                enemy = e;

            }//end if
        }//end for
        
        //only fire if enemy is in range
        if(enemy.distance < range){
            //calculate point of origin for weapon fire
            PVector circleSurf = findCircleSurf(enemy.position, position, (float)diameter/2);

            //draw player fire
            v.strokeWeight(10);
            v.stroke(abs(v.frameCount%360), 100, 100);
            v.line(circleSurf.x, circleSurf.y, enemy.position.x, enemy.position.y);

            dealDmg(enemy);

        }//end if
    }//end method



    public void dealDmg(Enemy enemy){

        long timePassedDmg = System.currentTimeMillis() - prevTimeDamage;

            //deal damage
            if(timePassedDmg > damageCooldown){

                prevTimeDamage = System.currentTimeMillis();
                enemy.health -= damage;

            }//end if
    }//end method



    PVector findCircleSurf(PVector rectCenter, PVector circleCenter, float radius){

        PVector offset  = PVector.sub(circleCenter, rectCenter);          
        offset.normalize();
        PVector p2 = PVector.sub(circleCenter, offset.mult(radius));

        //return pVector containing origin of player fire
        return p2;

    }//end method
}//end class

package OBJECTS;

import java.util.ArrayList;

public class SpawnThread extends Thread{

    ArrayList<Enemy> input, buffer;
    float spawnCooldown;
    
    //spawnCooldown given in milliseconds
    public SpawnThread(ArrayList<Enemy> input, ArrayList<Enemy> buffer, float spawnCooldown_){
        this.input = input;
        this.buffer = buffer;
        this.spawnCooldown = spawnCooldown_;
    }

    public void run(){
        System.out.println("Spawn Thread is running");

         synchronized(buffer){
            for (Enemy e : input){
                long prevTime = System.currentTimeMillis();
    
                while(System.currentTimeMillis() - prevTime < spawnCooldown){
                    //System.out.println("current time: "+System.currentTimeMillis() + "| prevtime: "+prevTime + "triggertime: " + spawnCooldown);
                }
    
                prevTime = System.currentTimeMillis();
    
                buffer.add(e);
                System.out.println("Enemy has been added to active enemy buffer");
            }
         }
    }
}

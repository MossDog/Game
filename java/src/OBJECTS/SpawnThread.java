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
    }//end constructor

    public void run(){
        System.out.println("Spawn Thread is running");

        synchronized(buffer){
            for (Enemy e : input){
                long prevTime = System.currentTimeMillis();
    
                if(buffer.size() == 0){
                    prevTime = System.currentTimeMillis();
    
                buffer.add(e);
                System.out.println("Enemy has been added to active enemy buffer");
                }else{
                    while(System.currentTimeMillis() - prevTime < spawnCooldown){
                        //System.out.println("current time: "+System.currentTimeMillis() + "| prevtime: "+prevTime + "triggertime: " + spawnCooldown);
                    }//end while
        
                    prevTime = System.currentTimeMillis();
        
                    buffer.add(e);
                    System.out.println("Enemy has been added to active enemy buffer");
                }//end else
            }//end for
        }//end sync
    }//end method
}//end class

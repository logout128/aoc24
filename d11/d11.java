import java.io.*;
import java.util.*;

class d11 {

    public static void main(String[] args) {
    
        String data = "1750884 193 866395 7 1158 31 35216 0";

        ArrayList<String> oldBlink;
        ArrayList<ArrayList<Long>> shorts = new ArrayList();
            
        for (int n = 0; n<10; n++) {
            ArrayList<Long> shortLine = new ArrayList<Long>();            
            oldBlink = new ArrayList<String>();
            oldBlink.add(Integer.toString(n));
            for (int i=0;i<40; i++) {            
                ArrayList<String> newBlink = new ArrayList<String>();
                for (int s=0; s<oldBlink.size(); s++) {
                    String stone = oldBlink.get(s);
                    if (stone.equals("0")) {
                        newBlink.add("1"); 
                    } else if (stone.length() %2 == 0) {                
                        Long firstHalf = Long.parseLong(stone.substring(0, stone.length()/2));
                        Long secondHalf = Long.parseLong(stone.substring(stone.length()/2));
                        newBlink.add(firstHalf.toString());
                        newBlink.add(secondHalf.toString());
                    } else {
                        Long numStone = Long.parseLong(stone);
                        numStone *= 2024L;
                        newBlink.add(numStone.toString());
                    }
                }
                shortLine.add((long)newBlink.size());
                oldBlink = newBlink;   
             }
             shorts.add(shortLine);
             System.out.println(shortLine);
        }
        
        oldBlink = new ArrayList<String>(Arrays.asList(data.split(" ")));
        Long size = 0L;

        int mmax=34;

        for (int i=0;i<=mmax; i++) {
            System.out.println(i);
            ArrayList<String> newBlink = new ArrayList<String>();
            
            for (int s=0; s<oldBlink.size(); s++) {
                String stone = oldBlink.get(s);                
                if (stone.equals("0")) {
                    newBlink.add("1"); 
                } else if (stone.length() %2 == 0) {                
                    Long firstHalf = Long.parseLong(stone.substring(0, stone.length()/2));
                    Long secondHalf = Long.parseLong(stone.substring(stone.length()/2));
                    newBlink.add(firstHalf.toString());
                    newBlink.add(secondHalf.toString());
                } else {
                    Long numStone = Long.parseLong(stone);
                    numStone *= 2024L;
                    newBlink.add(numStone.toString());
                }                
            }          
            oldBlink = newBlink;                        
        }        

        mmax=39;

        for (int i=0;i<=mmax; i++) {
            System.out.println(i);
            ArrayList<String> newBlink = new ArrayList<String>();
            
            for (int s=0; s<oldBlink.size(); s++) {
                String stone = oldBlink.get(s);                
                if (stone.equals("0")) {
                    newBlink.add("1"); 
                } else if (stone.length() %2 == 0) {                
                    Long firstHalf = Long.parseLong(stone.substring(0, stone.length()/2));
                    Long secondHalf = Long.parseLong(stone.substring(stone.length()/2));
                    newBlink.add(firstHalf.toString());
                    newBlink.add(secondHalf.toString());
                } else {
                    Long numStone = Long.parseLong(stone);
                    numStone *= 2024L;
                    newBlink.add(numStone.toString());
                }                
            }          
            oldBlink = newBlink;                        
            
            if (i != mmax) {
                for (int x=0;x<oldBlink.size();x++) {
                    Long stone = Long.parseLong(oldBlink.get(x));
                    if (stone < 10) {
                        size += shorts.get(Integer.parseInt(oldBlink.get(x))).get(mmax-(i+1));
                        oldBlink.remove(x);
                        x--;
                    }
                }
            
            }
            
            if(oldBlink.size()==0) {
                break;
            }
                         
        }     
           
        System.out.println(oldBlink.size());
        System.out.println(oldBlink.size()+size);
        
        
        

        
    }
}
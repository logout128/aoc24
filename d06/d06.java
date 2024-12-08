import java.io.*;
import java.util.*;

class d06 {

    public static void main(String[] args) {

        // read file into array
        
        int width=130;
        int height=130;
        
        char[][] map = new char[height][width];	
        char[][] copy = new char[height][width];
        char[][] archive = new char[height][width];
        

        try (	
                BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        ) {
                String line;
                int i=0;
                while((line = reader.readLine()) != null) {
                    map[i] = line.toCharArray();
                    copy[i] = line.toCharArray();
                    archive[i] = line.toCharArray();

                    i++; 
                }
                reader.close();
                
        } catch (Exception ex) {
                ex.printStackTrace();
        }
        
        int initX=0, posX=0, prevX=0;
        int initY=0, posY=0, prevY=0;

        
        for (int y=0;y<height;y++) {
            for (int x=0;x<width;x++) {
                if (map[y][x]=='^') {
                    initX=x;
                    initY=y;            
                }
            }
        }
        
        posX = initX;
        posY = initY;
        
        // 0 .. up; 1 .. right; 2 .. down; 3 .. left;
        int dir=0;            

        while (1==1) {
        
            prevX=posX;
            prevY=posY;
        
            copy[posY][posX] = 'X';
            
            if (dir==0) {
                posY--;
            } else if (dir==1) {
                posX++;
            } else if (dir==2) {
                posY++;
            } else {
                posX--;
            }
            
            if (posX>(width-1) || posX<0 || posY>(height-1) || posY<0) {
                break;
            }

            if (map[posY][posX]=='#') {            
                dir++;
                dir = dir & 3;            
                posX=prevX;
                posY=prevY;
            }

                            
        }        
        
        int positions=0;
        for (int y=0;y<height;y++) {
            for (int x=0;x<width;x++) {
                if (copy[y][x]=='X') {
                    positions++;
                }
            }
        }

        System.out.print("Distinct positions: ");
        System.out.println(positions);
        
        int obstacles=0;
        
        for (int y=0;y<height;y++) {
            for (int x=0;x<width;x++) {

                int steps=0;
                
                if (map[y][x] != '#' && map[y][x] != '^') {

                    for (int i=0; i<height; i++) {                    
                        System.arraycopy(archive[i], 0, map[i], 0, width);
                    }
                                    
                    map[y][x] = '#';
                
                    // 0 .. up; 1 .. right; 2 .. down; 3 .. left;
                    dir=0;            

                    posX = initX;
                    posY = initY;
                    while (1==1) {
                    
                        prevX=posX;
                        prevY=posY;
                    
                        if (dir==0) {
                            posY--;
                        } else if (dir==1) {
                            posX++;
                        } else if (dir==2) {
                            posY++;
                        } else {
                            posX--;
                        }
                        
                        if (posX>(width-1) || posX<0 || posY>(height-1) || posY<0) {
                            break;
                        }

                        if (map[posY][posX]=='#') {            
                            dir++;
                            dir = dir & 3;            
                            posX=prevX;
                            posY=prevY;
                        }
                        
                        steps++;
                        
                        if (steps>width*height*5) {
                            obstacles++;
                            break;
                        }
                                        
                    }   
                    
                }
            }
        }        

        System.out.print("Possible obstacles: ");
        System.out.println(obstacles);

        
    }    
}
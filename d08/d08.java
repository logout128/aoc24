import java.io.*;
import java.util.*;

class d08 {

    public static int locCount(ArrayList<ArrayList<Character>> target) {
        int count=0;
        for (ArrayList<Character> line : target) {
            for (Character c : line) {
                if (c=='#') {
                    count++;
                } 
            }
        }
        return count;
    }

        
    // here we start
    public static void main(String[] args) {    
        // read the data 
        ArrayList<ArrayList<Character>> map = new ArrayList();
        ArrayList<ArrayList<Character>> target = new ArrayList();

            
        try (	
                BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        ) {

                String line = reader.readLine();
                while(line != null) {
                    ArrayList<Character> mapLine = new ArrayList<Character>();
                    for (int i=0;i<line.length();i++) {
                        mapLine.add(line.charAt(i));
                    }
                    map.add(mapLine);
                    line = reader.readLine();
                }
                reader.close();
        } catch (Exception ex) {
                ex.printStackTrace();
        }
        

        int height= map.size();
        int width = map.get(0).size();
        
        for (int y=0;y<height;y++) {
            ArrayList<Character> targetLine = new ArrayList<Character>();            
            for (int x=0;x<width;x++) {
                targetLine.add('.');
            }        
            target.add(targetLine);
        }         

        // part one        
        for (int y=0;y<height;y++) {
            ArrayList<Character> mapLine = map.get(y);
            for (int x=0;x<width;x++) {
                if (mapLine.get(x) != '.') {
                    for (int ty=y;ty<height;ty++) {
                        ArrayList<Character> searchLine = map.get(ty);
                        for (int tx=0;tx<width;tx++) {
                            if (searchLine.get(tx) == mapLine.get(x) && x!=tx && y!=ty) {
                                int dx1 = (tx-x);
                                int dy1 = (ty-y);
                                
                                if ((x-dx1) >=0 && (x-dx1) < width && (y-dy1) >= 0) {
                                    target.get(y-dy1).set(x-dx1, '#');
                                }

                                if ((tx+dx1) >=0 && (tx+dx1) < width && (ty+dy1) < height) {
                                    target.get(ty+dy1).set(tx+dx1, '#');
                                }
                                                                
                            }
                                
                        }
                    }
                }
            }
        }
        
        int count=0;
        
        System.out.print("Part one location count: ");
        System.out.println(locCount(target));

        // part two        
        for (int y=0;y<height;y++) {
            ArrayList<Character> targetLine = new ArrayList<Character>();            
            for (int x=0;x<width;x++) {
                targetLine.add('.');
            }        
            target.add(targetLine);
        }         



        for (int y=0;y<height;y++) {
            ArrayList<Character> mapLine = map.get(y);
            for (int x=0;x<width;x++) {
                if (mapLine.get(x) != '.') {
                    for (int ty=y;ty<height;ty++) {
                        ArrayList<Character> searchLine = map.get(ty);
                        for (int tx=0;tx<width;tx++) {
                            if (searchLine.get(tx) == mapLine.get(x) && x!=tx && y!=ty) {
                                int dx1 = (tx-x);
                                int dy1 = (ty-y);
                                
                                int dx = 0;
                                int dy = 0;
                                               
                                // ninety-nine just looks fine         
                                for (int i=0;i<99;i++) {
                                                                    
                                    if ((x-dx) >=0 && (x-dx) < width && (y-dy) >= 0) {
                                        target.get(y-dy).set(x-dx, '#');
                                    }

                                    if ((tx+dx) >=0 && (tx+dx) < width && (ty+dy) < height) {
                                        target.get(ty+dy).set(tx+dx, '#');
                                    }

                                    dx+=dx1;
                                    dy+=dy1;

                                }
                                                                                        
                            }
                                
                        }
                    }
                }
            }
        }
            
        System.out.print("Part two location count: ");
        System.out.println(locCount(target));

    }    
}


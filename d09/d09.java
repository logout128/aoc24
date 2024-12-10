import java.io.*;
import java.util.*;

class d09 {

    // checks whether part one disk is already sorted
    public static boolean isSorted (ArrayList<Integer> list) {
        for (int i=1;i<list.size();i++) {        
            if (list.get(i) >= 0 && list.get(i-1) == -1) {
                return false;
            }                    
        }        
        return true;
    }
    
    // finds next free block on part-one disk
    public static int getNextFree (ArrayList<Integer> list) {        
        for (int i=1;i<list.size();i++) {        
            if (list.get(i) == -1) {
                return i;
            }
        }
        return -1;        
    }
        
    // here we start
    public static void main(String[] args) {    
        // read the raw input data into string
        String rawDisk = "";        
        try (	
                BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        ) {
                while(reader.ready()) {
                    rawDisk += (char)reader.read();
                }
                reader.close();
        } catch (Exception ex) {
                ex.printStackTrace();
        }

        // process raw dat to ArrayList where:
        // ... sequence of -1 marks free sectors
        // ... sequence of positive numbers marks files by their ID
        ArrayList<Integer> disk = new ArrayList<Integer>();
        int id = 0;        
        for (char c : rawDisk.toCharArray()) {
            int h = Character.getNumericValue(c);        
            if (id % 2 ==0) {                    
                for (int i=0; i<h; i++) {
                    disk.add((int)(id/2));
                }
            } else {
                for (int i=0; i<h; i++) {
                    disk.add(-1);
                }
            }                
            id++;            
        }

        // copy of the disk for part two
        ArrayList<Integer> copy = new ArrayList<Integer>(disk);       

        // sorting according part one rules
        for (int i=disk.size()-1;i>=0;i--) {        
            if (isSorted(disk)) {
                break;            
            } else {
                disk.set(getNextFree(disk), disk.get(i));
                disk.set(i, -1);
            }                
        }

        // calculating checksum for part one
        // (we can stop when reaching first free sector)
        Long checksum = 0L;
        for (int i=0; i<disk.size();i++) {
            if (disk.get(i) == -1) {
                break;
            } else {
                checksum += i*disk.get(i);
            }
        }        
            
        System.out.print("Fragmented checksum: ");
        System.out.println(checksum);   
        
        // part two begins, this won't be pretty
        // iterating disk from back
        for (int i=copy.size()-1;i>=0;i--) {
            int c = copy.get(i);
            //if not free sector then read the length of file            
            if (c!=-1) {
                String file = "";
                int fileLen = 0;                
                while (copy.get(i)==c) {
                    fileLen++;
                    i--;            
                    if (i<0) {
                        break;
                    }
                }            
                i++;
                // then find from the beginning first free space
                for (int x=0;x<i;x++) {
                    int e = copy.get(x);
                    if (e==-1) {
                        int spaceLen = 0;
                        int b = x;
                        while (copy.get(x)==e) {
                            spaceLen++;
                            x++;
                        }

                        // if its long enought to accomodate the file
                        if (spaceLen >= fileLen) {
                            // put the file there                        
                            for (int y=b; y<(b+fileLen); y++) {
                                copy.set(y, c);
                            }
                            // clear the file from original place                            
                            for (int y=i;y<i+fileLen;y++) {
                                copy.set(y, -1);
                            }
                            // move to next file
                            break;                            
                        }
                    }
                }
            }
        }
        
        // calculating checksum for part two
        // (free sectors are just skipped)        
        checksum = 0L;
        for (int i=0; i<copy.size();i++) {
            if (copy.get(i) != -1) {
                checksum += i*copy.get(i);
            }
        }                
        System.out.print("Non-fragmented checksum: ");
        System.out.println(checksum);           
    }    
}


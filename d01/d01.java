import java.io.*;
import java.util.*;

class d01 {

    public static void main(String[] args) {
        ArrayList<Integer>array1, array2;
        
        array1 = new ArrayList<Integer>();
        array2 = new ArrayList<Integer>();
        
        // read and process data into lists
        try (	
                BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        ) {
                String line = reader.readLine();
                while(line != null) {
                    String[] parts = line.split("   ");
                    line = reader.readLine();
                    array1.add(Integer.parseInt(parts[0]));
                    array2.add(Integer.parseInt(parts[1]));      
                    }
                reader.close();
        } catch (Exception ex) {
                ex.printStackTrace();
        }
        // sort 'em        
        array1.sort(null);
        array2.sort(null);
        
        int diff=0;
        int similarity=0;
        
        // iterate and calculate similarity and difference        
        for(int i=0;i<array1.size();i++) {
            diff += Math.abs(array2.get(i)-array1.get(i));
            similarity += array1.get(i) * Collections.frequency(array2, array1.get(i));
        }
        
        System.out.print("Diff: ");
        System.out.print(diff);
        System.out.print("\t|\t");
        System.out.print("Similarity: ");
        System.out.println(similarity);
    }
}
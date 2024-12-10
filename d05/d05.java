import java.io.*;
import java.util.*;

class d05 {

    public static int getMiddleMember (ArrayList<Integer> list) {
        int middle = ((int) (list.size()/2));
        return list.get(middle);
    }
    
    public static boolean validateNumber (ArrayList<ArrayList<Integer>> rules, ArrayList valueLine, int index) {
    
        for (int r=0; r<rules.size(); r++) {
            if(valueLine.get(index)==rules.get(r).get(0)) {
                for (int i=0;i<index+1;i++) {
                    if (valueLine.get(i)==rules.get(r).get(1)) {
                        return false;
                    }
                }
            }
        }    
        return true;
    }    


    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> rules = new ArrayList();
        ArrayList<ArrayList<Integer>> values = new ArrayList();

        try (	
                BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        ) {
                String line;
                while((line = reader.readLine()) != null) {                
                    if (line.indexOf('|') >0) {
                        String[] lineParts = line.split("\\|");
                        ArrayList<Integer> ruleLine = new ArrayList<Integer>();
                        ruleLine.add(Integer.parseInt(lineParts[0]));
                        ruleLine.add(Integer.parseInt(lineParts[1]));                        
                        rules.add(ruleLine);                
                    } else if (line.indexOf(',') >0) {
                        String[] lineParts = line.split("\\,");
                        ArrayList<Integer> valueLine = new ArrayList<Integer>();                                                
                        for (String part : lineParts) {
                            valueLine.add(Integer.parseInt(part));                      
                        }
                        
                        values.add(valueLine);                
                    
                    }
                }
                reader.close();
                
        } catch (Exception ex) {
                ex.printStackTrace();
        }

    int cResult = 0;
    int iResult = 0;
    boolean addIt=false;

    for (ArrayList<Integer> valueLine : values) {    
        
        for (int v=1; v<valueLine.size(); v++) {
            addIt = validateNumber(rules, valueLine, v);
            if (addIt==false) {
                break;
            }
        }

        if (addIt==true) {
            cResult += getMiddleMember(valueLine);
        }
            
    }

    System.out.print("Part one result:");        
    System.out.println(cResult);        
    
    }    
}
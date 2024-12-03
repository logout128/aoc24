import java.io.*;
import java.util.*;
import java.util.regex.*;

class d03 {

    // returns last value from list which is lower than searchValue
    public static int searchList(ArrayList<Integer> list, Integer searchValue) {            
        int prevValue = 0;            
        for(int i=0; i<list.size(); i++) {
            if ((prevValue < searchValue || prevValue == 0) && (list.get(i) > searchValue || i ==list.size()-1)) {
                return prevValue;
            }            
            prevValue = list.get(i);
        } 
        // should not ever get here
        return -1;
    }

    public static void main(String[] args) {

        // read file into string        
        String data = new String();
        StringBuilder builder = new StringBuilder();        
        try (	
                BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        ) {
                String line;
                while((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                reader.close();
                data = builder.toString();
        } catch (Exception ex) {
                ex.printStackTrace();
        }


        // search for all do() and don't() in data       
        Pattern pattern;
        
        ArrayList<Integer> doList = new ArrayList<Integer>();
        ArrayList<Integer> dontList = new ArrayList<Integer>();
        
        // we start as if there was do()
        doList.add(0);
    

        // regex for do()
        pattern = Pattern.compile("do\\(\\)");
        Matcher doMatcher = pattern.matcher(data);        
        while (doMatcher.find()) {        
            doList.add(doMatcher.end());
        }

        // regex for don't()
        pattern = Pattern.compile("don't\\(\\)");
        Matcher dontMatcher = pattern.matcher(data);        
        while (dontMatcher.find()) {        
            dontList.add(dontMatcher.end());
        }

        // regex for mul(number, number)        
        pattern = Pattern.compile("mul\\(([0-9]+),([0-9]+)\\)");
        Matcher muls = pattern.matcher(data);

        int total = 0;
        int cd = 0;
        
        while (muls.find()) {              
            int number1 = Integer.parseInt(muls.group(1));
            int number2 = Integer.parseInt(muls.group(2));                       
            int lastDo = searchList(doList, muls.end());
            int lastDont = searchList(dontList, muls.end());
            // total for all muls
            total += number1*number2;                                   
            // conditional just if we "do()"
            cd += (lastDo >= lastDont) ? number1*number2 : 0;
        }
        
        System.out.print("Total: ");
        System.out.print(total);
        System.out.print("\t\t");
        System.out.print("With conditions: ");
        System.out.println(cd);
    }    
}
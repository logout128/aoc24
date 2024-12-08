import java.io.*;
import java.util.*;
import java.lang.Math;

class d07 {

    public static void main(String[] args) {
        ArrayList<Long> results;
        ArrayList<ArrayList<Integer>> numbers;
        
        results = new ArrayList<Long>();
        numbers = new ArrayList();
        
        try (	
                BufferedReader reader = new BufferedReader(new FileReader("sample.txt"));
        ) {
                String line = reader.readLine();
                while(line != null) {
                    String[] parts = line.split(":");
                    results.add(Long.parseLong(parts[0]));
                    ArrayList<Integer> numberLine = new ArrayList<Integer>();
                    for(String number : parts[1].split(" ")) {
                                if (number.length()>0) {
                                    numberLine.add(Integer.parseInt(number));
                                }
                    }                    
                    numbers.add(numberLine);
                    line = reader.readLine();
                }
                reader.close();
        } catch (Exception ex) {
                ex.printStackTrace();
        }

        long total = 0L;

        for(int i=0;i<results.size();i++) {
            
            System.out.println(results.get(i));
            System.out.println(numbers.get(i));
            
            int digits = (numbers.get(i).size()-1);
            
            for(int b=0;b<Math.pow(2,digits); b++) {        
                String binNum = String.format(Character.toString(37)+Integer.toString(digits)+"s", Integer.toBinaryString(b)).replace(' ', '0');            
                long current = 0L;
                current += numbers.get(i).get(0);
                System.out.print(current);
                
                for(int x=1; x<digits+1; x++) {
                    if (binNum.charAt(x-1)=='0') {
                        current += numbers.get(i).get(x);
                        System.out.print("+");
                    } else {
                        current *= numbers.get(i).get(x);
                        System.out.print("*");
                    }            
                    System.out.print(numbers.get(i).get(x));                
                }
                
                System.out.println("="+current +", "+ results.get(i));

                System.out.println(current==results.get(i));
                
            }
        }
    }
}
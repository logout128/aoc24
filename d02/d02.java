import java.io.*;
import java.util.*;

class d02 {

    public static boolean isSorted(List list) {
    	List copyAsc = new ArrayList(list);
    	List copyDesc = new ArrayList(list);
	Set<Integer> set = new HashSet<Integer>(list);
    	
	Collections.sort(copyAsc);
    	Collections.sort(copyDesc, Collections.reverseOrder());
    	return ((copyAsc.equals(list) || copyDesc.equals(list)) && (set.size() == list.size()));
    } 

    public static boolean isSafe(ArrayList<Integer> dataLine) {

		int maxdiff=0;	
		int mindiff=0;
		int prevdiff=0;

		for (int i=1; i<dataLine.size(); i++) {	
				int diff = Math.abs(dataLine.get(i)-dataLine.get(i-1));			
				if (i==1) {
					mindiff = diff;
				} else {
					mindiff = (diff < mindiff) ? diff : mindiff;
				}

				maxdiff = (diff > maxdiff) ? diff : maxdiff;
			
				prevdiff = diff;
		}
	
		if (isSorted(dataLine) && mindiff>=1 && maxdiff<=3) {
			return true;
		} else {
			return false;
		}

    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>>data;
        data = new ArrayList<>();
        
        try (	
                BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        ) {
                String line = reader.readLine();
		
                while(line != null) {
                	String[] parts = line.split(" ");
		        ArrayList<Integer> dataLine = new ArrayList<Integer>();
                	for(String number : parts) {
				dataLine.add(Integer.parseInt(number));
			}				
			data.add(dataLine);
			line = reader.readLine();
                    }
                reader.close();
        } catch (Exception ex) {
                ex.printStackTrace();
        }                                                                                                                                                         

	int safe=0;	

	for (ArrayList<Integer> dataLine : data) {
		if (isSafe(dataLine)) {
			safe++;
		} else {

			for (int i=0; i<dataLine.size(); i++) {
				ArrayList<Integer> copy = new ArrayList(dataLine);
				copy.remove(i);
				if(isSafe(copy)) {
					safe++;
					break;
				}
			}
		}

        }
	System.out.print("Safe: ");
	System.out.println(safe);		

    
  }
}
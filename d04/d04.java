import java.io.*;
import java.util.*;
import java.util.regex.*;

class d04 {

    public static void main(String[] args) {

        // read file into string with respecting of line ends        
        int width=0, height=0;
        String data = new String();
        StringBuilder builder = new StringBuilder();        
        try (	
                BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        ) {
                String line;
                while((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                    height++;                    
                    width = (width==0) ? line.length() : width;
                }
                reader.close();
                data = builder.toString();
        } catch (Exception ex) {
                ex.printStackTrace();
        }

        char[][] matrix = new char[height][width];

        int count = 0, x = 0, y = 0, cross = 0;
        Pattern xmasPat, samxPat;
        String line = null;
        
        xmasPat = Pattern.compile("XMAS");
        samxPat = Pattern.compile("SAMX");
        
        BufferedReader bufReader = new BufferedReader(new StringReader(data));       

        try {        
            while ((line = bufReader.readLine()) != null) {
                // counting two directions: horizontal forwards and backwars
                Matcher xmasMat = xmasPat.matcher(line);       
                Matcher samxMat = samxPat.matcher(line);                
                count += xmasMat.results().count();
                count += samxMat.results().count();
                // rotating matrix                
                for(x=0;x<line.length();x++) {
                    matrix[x][y] = line.charAt(x);
                }
                
            y++;                
            }            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        String rotated = new String();
        
        for(y=0;y<height;y++) {
            rotated += new String(matrix[y]);
            rotated += "\n";
        }

        bufReader = new BufferedReader(new StringReader(rotated));       

        try {        
            while ((line = bufReader.readLine()) != null) {
                // counting two directions: vertical forwards and backwars
                Matcher xmasMat = xmasPat.matcher(line);       
                Matcher samxMat = samxPat.matcher(line);
                count += xmasMat.results().count();
                count += samxMat.results().count();
            }            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        // counting two directions: first diagonal forwards and backwars
        for (y=0; y<height-3; y++) {
            for (x=0; x<width-3; x++) {        
                if(matrix[x][y]=='X' && matrix[x+1][y+1]=='M' && matrix[x+2][y+2]=='A' && matrix[x+3][y+3]=='S') {
                    count++;
                }
                if(matrix[x][y]=='S' && matrix[x+1][y+1]=='A' && matrix[x+2][y+2]=='M' && matrix[x+3][y+3]=='X') {
                    count++;
                }
            }
        }
        // counting two directions: second diagonal forwards and backwars        
        for (y=0; y<height-3; y++) {
            for (x=3; x<width; x++) {        
                if(matrix[x][y]=='X' && matrix[x-1][y+1]=='M' && matrix[x-2][y+2]=='A' && matrix[x-3][y+3]=='S') {
                    count++;
                }
                if(matrix[x][y]=='S' && matrix[x-1][y+1]=='A' && matrix[x-2][y+2]=='M' && matrix[x-3][y+3]=='X') {
                    count++;
                }
            }
        }

        // part two
        for (y=0; y<height-2; y++) {
            for (x=0; x<width-2; x++) {        
                if(matrix[x][y]=='M' && matrix[x+1][y+1]=='A' && matrix[x+2][y+2]=='S' && matrix[x+2][y]=='M' && matrix[x][y+2]=='S') {
                    cross++;
                }
                else if(matrix[x][y]=='S' && matrix[x+1][y+1]=='A' && matrix[x+2][y+2]=='M' && matrix[x+2][y]=='S' && matrix[x][y+2]=='M') {
                    cross++;
                }
                else if(matrix[x][y]=='M' && matrix[x+1][y+1]=='A' && matrix[x+2][y+2]=='S' && matrix[x+2][y]=='S' && matrix[x][y+2]=='M') {
                    cross++;
                }
                else if(matrix[x][y]=='S' && matrix[x+1][y+1]=='A' && matrix[x+2][y+2]=='M' && matrix[x+2][y]=='M' && matrix[x][y+2]=='S') {
                    cross++;
                }                                
            }
        }
      
        System.out.print("XMAS count: ");
        System.out.print(count);
        System.out.print("\t");
        System.out.print("Cross count: ");
        System.out.println(cross);
    }    
}
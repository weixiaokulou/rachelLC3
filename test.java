import java.io.*;

public class test {
    public static void main(String [] args) throws IOException {
    	
    	new JF();
        String fileName = "1.txt";
        CPU cpu=new CPU();
        int entry=0x30;
        cpu.PC = entry;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int i=entry;
            String line = null;
            
            while((line = bufferedReader.readLine()) != null) {
                    cpu.str = Integer.parseInt(line, 2);
                    cpu.memory[i]=cpu.str;
                    i++;
            }  

            while(true) {
              int mar = cpu.PC;
              if(cpu.memory[mar] == 0x0 ) break;
              System.out.println("----------------------------------------------------------------------------");
              System.out.println("Fetch finish, the instruction is: "+intToString(cpu.memory[mar], 4));
              cpu.PC = cpu.PC + 1;
              cpu.Decode(cpu.memory[mar],cpu);    
              System.out.printf("Number of instructions executed: %d\n", (int)cpu.PC - 48);
              //print();
            } 
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }       
        }
        static String intToString(int number, int groupSize) {
          StringBuilder result = new StringBuilder();
          for(int i = 15; i >= 0 ; i--) {
              int mask = 1 << i;
              result.append((number & mask) != 0 ? "1" : "0");
              if (i % groupSize == 0)
                  result.append(" ");
        }
          result.replace(result.length() - 1, result.length(), "");

          return result.toString();
        }    
}

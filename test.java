import java.io.*;

public class test {
    public static void main(String [] args) {
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
            int memory[]= new int[128];
            while((line = bufferedReader.readLine()) != null) {
                    int str = Integer.parseInt(line, 2);
                    memory[i]=str;
                    //System.out.println("i: "+i);
                    i++;
          }  

        while(true) {
          int mar = cpu.PC;
          if(memory[mar] == 0x0 ) break;
          cpu.PC = cpu.PC + 1;
          Decode(memory[mar],cpu);
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
    public static void Decode(int instr,CPU cpu)
     {
      switch(instr >> 12){
        case 1://OP_ADD:
          OpADD(instr,cpu); 
          break;
        case 5://OP_AND:
          OpAND(instr,cpu);
          break;
        /*ase 9://OP_NOT:
          OpNOT(instr);   
          break;
        case 12://OP_ST: STB
          OpST(instr);    
          break;
        case 11://OP_STI:
          OpSTI(instr);   
          break;
        case 7://OP_STR:
          OpSTR(instr);   
          break;
        case 2://OP_LD: LDB
          OpLD(instr);    
          break;
        case 10://OP_LDI:
          OpLDI(instr);   
          break;
        case 6://OP_LDR:
          OpLDR(instr);   
          break;
        case 14://OP_LEA:
          OpLEA(instr);
          break;
        case 0://OP_BR:
          OpBR(instr);
          break;
        case 4://OP_JSR_JSRR:
          OpJSR_JSRR(instr);
          break;
        case 15://OP_TRAP:
          OpTRAP(instr);
          break;
        case 12://OP_JMP_RET:
          OpJMP_RET(instr);
          break;*/
        default:
          break;
       }
      }
      public static int SExt(int targ, int n)
      {
        if(targ >> n-1 == 0)  return targ;
        else  return (0xffff << n) + targ;
      }

      public static int ZExt(int targ)
      {
        return targ; 
      }

      public static void OpADD(int inst,CPU cpu)
      {
        System.out.println("OpADD ");
       int src1,src2,imm5,dst;
        src1 = ((inst >> 6) & 0x7); 
        dst = ((inst >> 9) & 0x7); 
        System.out.println("dst "+dst);
        if(((inst >> 5) & 0x1) == 0) {
          src2 = inst & 0x7; 
          cpu.reg[dst] = src1 + src2;
          System.out.println("OpADD result "+cpu.reg[dst]);
        } else if(((inst >> 5) & 0x1) == 1) {
          imm5 = SExt(inst & 0x1f,5); 
          cpu.reg[dst] = src1 + imm5;
          System.out.println("Other OpADD result "+cpu.reg[dst]);
        }
        //last_altered_reg = dst;
       }
      public static void OpAND(int inst,CPU cpu)
      {
        System.out.println("OpAND ");
      }
}

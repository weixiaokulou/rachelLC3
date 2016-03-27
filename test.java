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
            
            while((line = bufferedReader.readLine()) != null) {
                    int str = Integer.parseInt(line, 2);
                    cpu.memory[i]=str;
                    i++;
          }  

        while(true) {
          int mar = cpu.PC;
          if(cpu.memory[mar] == 0x0 ) break;
          cpu.PC = cpu.PC + 1;
          Decode(cpu.memory[mar],cpu);
          System.out.printf("Number of instructions executed: %c",cpu.PC);
          System.out.println();
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
        case 9://OP_NOT:
          OpNOT(instr,cpu);   
          break;
        case 3://OP_ST: STB
          OpST(instr,cpu);  
          break;
        /*case 11://OP_STI:
          OpSTI(instr,cpu);   
          break;
        case 7://OP_STR:
          OpSTR(instr,cpu);   
          break;
        case 2://OP_LD: LDB
          OpLD(instr,cpu);    
          break;
        case 10://OP_LDI:
          OpLDI(instr,cpu); 
          break;
        case 6://OP_LDR:
          OpLDR(instr,cpu);  
          break;
        case 14://OP_LEA:
          OpLEA(instr,cpu);
          break;
        case 0://OP_BR:
          OpBR(instr,cpu);
          break;
        case 4://OP_JSR_JSRR:
          OpJSR_JSRR(instr,cpu);
          break;
        case 15://OP_TRAP:
          OpTRAP(instr,cpu);
          break;
        case 12://OP_JMP_RET:
          OpJMP_RET(instr,cpu);
          break;*/
        default:
          break;
       }
      }
      public static int SExt(int targ, int n)
      {
        if(targ >> n-1 == 0)  return targ;//if the first bit is 0, nothing changes
        else  return (0xffff << n) + targ;//if the first bit is 1, return targ which all other bit before it are "1"s
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
        cpu.reg[src1] = src1;
        dst = ((inst >> 9) & 0x7); 
        System.out.println("dst "+dst);
        if(((inst >> 5) & 0x1) == 0) {
          src2= inst & 0x7; 
          cpu.reg[src2] =src2;
          cpu.reg[dst] = cpu.reg[src1] + cpu.reg[src2];
          System.out.println("OpADD result "+cpu.reg[dst]);
        } else if(((inst >> 5) & 0x1) == 1) {
          imm5 = SExt(inst & 0x1f,5); 
          cpu.reg[dst] = cpu.reg[src1] + imm5;
          System.out.println("Other OpADD result "+cpu.reg[dst]);
        }
        //last_altered_reg = dst;
       }
      public static void OpAND(int inst,CPU cpu)
      {
        System.out.println("OpAND ");
        int src1,src2,imm5,dst;
        src1 = ((inst >> 6) & 0x7);
        cpu.reg[src1]= src1;
        dst = ((inst >> 9) & 0x7); 
        if(((inst >> 5) & 0x1) == 0) {
          src2 = inst & 0x7; 
          cpu.reg[src2] = src2;
          cpu.reg[dst] = cpu.reg[src1] & cpu.reg[src2];
          System.out.println("OpAND result "+cpu.reg[dst]);
        } else {
          imm5 = SExt(inst & 0x1f, 5); 
          cpu.reg[dst] = cpu.reg[src1] & imm5; 
          System.out.println("Other OpAND result "+cpu.reg[dst]);
        }
        //last_altered_reg = dst;
      }
      public static void OpNOT(int inst,CPU cpu)
      {
        System.out.println("OpNOT ");
        int src1,dst;
        src1=((inst >> 6) & 0x7);
        cpu.reg[src1] = src1; /*sro1*/
        dst = ((inst >> 9) & 0x7); /*dst*/
        cpu.reg[dst] = ~ cpu.reg[src1];
        //last_altered_reg = dst;
      }
      public static void OpST(int inst,CPU cpu)
      {
        System.out.println("OpST ");
        int src,baserc,pcoffset6,madr;
        src = ((inst >> 9) & 0x7); /*src*/
        baserc=((inst >> 6) & 0x7);
        cpu.reg[src] = src;
        pcoffset6 = SExt(inst & 0x6, 6); /*PCoffset9*/
        System.out.println("OpST result,pcoffset6 "+pcoffset6);
        madr = baserc + pcoffset6;
        System.out.println("OpST result,madr "+madr);
        cpu.memory[madr] = cpu.reg[src];
      }
}

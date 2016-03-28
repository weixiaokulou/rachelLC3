import java.io.*;

public class CPU{
  int reg[]= new int[8];
  int PC;
  int memory[]= new int[128];
  int str;
  int last_altered_reg;
  void Decode(int instr,CPU cpu)
     {
      switch(instr >> 12){
        case 1://OP_ADD:
        System.out.println("Decode finish, the instruction is OP_ADD");
          OpADD(instr,cpu); 
          break;
        case 5://OP_AND:
        System.out.println("Decode finish, the instruction is OP_AND");
          OpAND(instr,cpu);
          break;
        case 9://OP_NOT:
        System.out.println("Decode finish, the instruction is OP_NOT");
          OpNOT(instr,cpu);   
          break;
        case 3://OP_ST: STB
        System.out.println("Decode finish, the instruction is OP_STB");
          OpST(instr,cpu);  
          break;
        case 11://OP_STI:
        System.out.println("Decode finish, the instruction is OP_STI");
          OpSTI(instr,cpu);   
          break;
        case 7://OP_STR:
        System.out.println("Decode finish, the instruction is OP_STR");
          OpSTR(instr,cpu);   
          break;
        case 2://OP_LD: LDB
        System.out.println("Decode finish, the instruction is OP_LDB");
          OpLD(instr,cpu);    
          break;
        case 10://OP_LDI:
        System.out.println("Decode finish, the instruction is OP_LDI");
          OpLDI(instr,cpu); 
          break;
        case 6://OP_LDR:
        System.out.println("Decode finish, the instruction is OP_LDR");
          OpLDR(instr,cpu);  
          break;
        case 14://OP_LEA:
        System.out.println("Decode finish, the instruction is OP_LEA");
          OpLEA(instr,cpu);
          break;
        case 0://OP_BR:
        System.out.println("Decode finish, the instruction is OP_BR");
          OpBR(instr,cpu);
          break;
        case 4://OP_JSR_JSRR:
        System.out.println("Decode finish, the instruction is OP_JSR_JSRR");
          OpJSR_JSRR(instr,cpu);
          break;
        case 15://OP_TRAP:
        System.out.println("Decode finish, the instruction is OP_TRAP");
          OpTRAP(instr,cpu);
          break;
        case 12://OP_JMP_RET:
        System.out.println("Decode finish, the instruction is OP_JMP_RET");
          OpJMP_RET(instr,cpu);
          break;
        default:
          break;
       }
      }
  	  static int SExt(int targ, int n)
      {
        /*if(targ >> n-1 == 0)  return targ;
        else 
        {
          /*short shorttarg=(short)targ;
          int mid=0xffff;
          short shortmid=(short)mid;
          targ=(shortmid<<n)+shorttarg;
         
            return (0xffff << n) + targ;
          
        } */
        return targ; 
      }

      static int ZExt(int targ)
      {
        return targ; 
      }

      void OpADD(int inst,CPU cpu)
      {
        System.out.println("Executing OpADD... ");
        int src1,src2,imm5,dst;
        src1 = ((inst >> 6) & 0x7);
        cpu.reg[src1] = src1;
        dst = ((inst >> 9) & 0x7); 
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
        last_altered_reg = dst;
       }
      void OpAND(int inst,CPU cpu)
      {
        System.out.println("Executing OpAND... ");
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
        last_altered_reg = dst;
      }
      void OpNOT(int inst,CPU cpu)
      {
        System.out.println("Executing OpNOT... ");
        int src1,dst;
        src1=((inst >> 6) & 0x7);
        cpu.reg[src1] = src1; /*sro1*/
        dst = ((inst >> 9) & 0x7); /*dst*/
        cpu.reg[dst] = ~ cpu.reg[src1];
        last_altered_reg = dst;
      }
      static void OpST(int inst,CPU cpu)
      {
        System.out.println("Executing OpST... ");
        int src,baserc,pcoffset6,madr;
        src = ((inst >> 9) & 0x7); 
        baserc=((inst >> 6) & 0x7);
        cpu.reg[src] = src;
        int x=inst & 0x3f;
        System.out.println("inst & 0x3f : "+x);
        pcoffset6 = SExt(inst & 0x3f, 6); 
        System.out.println("pcoffset6 "+pcoffset6);
        System.out.println(pcoffset6);
        madr = baserc + pcoffset6;
        cpu.memory[madr] = cpu.reg[src]&0xff;
        System.out.println("OpST result :"+cpu.memory[madr]+" has been written in memory["+madr+"]");
      }
      void OpSTI(int inst,CPU cpu)
      {
        System.out.println("Executing OpST... ");
        int src,baserc,pcoffset6,madr,madr1;
        src = ((inst >> 9) & 0x7); 
        baserc=((inst >> 6) & 0x7);
        cpu.reg[src] = src;
        int x=inst & 0x3f<<1;
        System.out.println("inst & 0x3f : "+x);
        pcoffset6 = SExt(inst & 0x3f, 6)<<1; 
        System.out.println("pcoffset6 "+pcoffset6);
        madr = baserc + pcoffset6;
        madr1=memory[madr];
        memory[madr1] = cpu.reg[src];
        System.out.println("OpSTI result :"+memory[madr1]+" has been written in memory["+madr+"]");
      }
      static void OpSTR(int inst,CPU cpu)
      {
        System.out.println("Executing OpSTR... ");
        int src,baserc,pcoffset6,madr;
        src = ((inst >> 9) & 0x7); 
        baserc=((inst >> 6) & 0x7);
        cpu.reg[src] = src;
        pcoffset6 = SExt(inst & 0x3f, 6)<<1; 
        int x=inst & 0x3f<<1;
        System.out.println("inst & 0x3f : "+x);
        System.out.println("pcoffset6 "+pcoffset6);
        madr = baserc + pcoffset6;
        cpu.memory[madr] = cpu.reg[src];
        System.out.println("OpST result :"+cpu.memory[madr]+" has been written in memory["+madr+"]");
      }
      void OpLD(int inst,CPU cpu)
      {
        System.out.println("Executing OpLD... ");
        int dst,baserc,pcoffset6,madr;
        dst = ((inst >> 9) & 0x7); 
        pcoffset6 = SExt(inst & 0x3f, 6); 
        baserc=((inst >> 6) & 0x7);
        madr = baserc + pcoffset6;
        cpu.reg[dst] = ZExt(memory[madr]);
        last_altered_reg = dst;
        System.out.println("OpLD result :"+cpu.reg[dst]+" has been written in memory["+dst+"]");
      }
      void OpLDI(int inst,CPU cpu)
      {
        System.out.println("Executing OpLDI... ");
        int dst,baserc,pcoffset6,madr,madr1;
        dst = ((inst >> 9) & 0x7); 
        pcoffset6 = SExt(inst & 0x3f, 6)<<1; 
        baserc=((inst >> 6) & 0x7);
        madr = baserc + pcoffset6;
        madr1=memory[madr];
        cpu.reg[dst] = ZExt(memory[madr1]);
        last_altered_reg = dst;
        System.out.println("OpLDI result :"+cpu.reg[dst]+" has been written in memory["+dst+"]");
      }
      void OpLDR(int inst,CPU cpu)
        {
          
          System.out.println("Executing OpLDR... ");
          int dst,baserc,pcoffset6,madr;
          dst = ((inst >> 9) & 0x7); 
          pcoffset6 = SExt(inst & 0x3f, 6)<<1; 
          baserc=((inst >> 6) & 0x7);
          madr = baserc + pcoffset6;
          cpu.reg[dst] = ZExt(memory[madr]);
          last_altered_reg = dst;
          System.out.println("OpLD result :"+cpu.reg[dst]+" has been written in memory["+dst+"]");
      }

        void OpLEA(int inst,CPU cpu)
        {
          System.out.println("Executing OpLEA... ");
          int dst,pcoffset9;
          dst = ((inst >> 9) & 0x7); 
          pcoffset9 = SExt(inst & 0x1ff, 9); 
          cpu.reg[dst] = cpu.PC + pcoffset9;
          System.out.println("OpLD result : new address(including cpu.PC) has been written in memory["+dst+"]");
          last_altered_reg = dst;
        }

        void OpBR(int inst,CPU cpu)
        {
          System.out.println("Executing OpBR... ");
          int n,z,p,pcoffset9;
          n = ((inst >> 11) & 0x1); 
          z = ((inst >> 10) & 0x1); 
          p = ((inst >> 9) & 0x1); 
          pcoffset9 = SExt(inst & 0x1ff, 9); 
          System.out.printf("pcoffset9: %d  inst: %d", pcoffset9, inst );
          int targ_reg = cpu.reg[last_altered_reg];
          System.out.println("last_altered_reg : "+cpu.reg[last_altered_reg]);
          if((n == 1 && targ_reg < 0) || (z == 1 && targ_reg == 0) || (p == 1 && targ_reg > 0)) {
            cpu.PC = cpu.PC + pcoffset9;
          }
          System.out.println("OpLD result : branch has been made");
        }

        void OpJSR_JSRR(int inst,CPU cpu)
        {
          System.out.println("Executing OpJSR_JSRR... ");
          int pcoffset11,baser;
          cpu.reg[7] = cpu.PC;
          if(((inst >> 11) & 0x1) == 0) {
            baser = (inst >> 6) & 0x7; 
            cpu.PC = cpu.reg[baser];
          } else {
            pcoffset11 = SExt(inst & 0x7ff, 11); 
            cpu.PC = cpu.PC + pcoffset11;   
          }
        }

        void OpTRAP(int inst,CPU cpu)
        {
          System.out.println("Executing OpTRAP... ");
          int trapvect8;
          trapvect8 = inst & 0xff; 
          cpu.reg[7] = cpu.PC;
          cpu.PC = memory[ZExt(trapvect8)];
        }

        void OpJMP_RET(int inst,CPU cpu)
        {
          System.out.println("Executing OpJMP_RET... ");
          int baser;
          baser = (inst >> 6) & 0x7; 
          cpu.PC = cpu.reg[baser];
        }
}
package org.lc3simulator.instructions;

public class OP_AND extends Instruction {

	public OP_AND(long pc, int instr) {
		super(pc, instr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(int[] reg) {
		System.out.println("Executing OpAND... ");
	    int src1,src2,imm5,dst;
	    src1 = ((instr >> 6) & 0x7);
	    reg[src1]= src1;
	    dst = ((instr >> 9) & 0x7); 
	    if(((instr >> 5) & 0x1) == 0) {
	      src2 = instr & 0x7; 
	      reg[src2] = src2;
	      reg[dst] = reg[src1] & reg[src2];
	      System.out.println("OpAND result " + reg[dst]);
	  } else {
	      //imm5 = SExt(instr & 0x1f, 5);
		  imm5 = instr & 0x1f; 
	      reg[dst] = reg[src1] & imm5; 
	      System.out.println("Other OpAND result " + reg[dst]);
	  }
	}

	@Override
	public void mem(int[] reg, int[] memory) {
		// Do Nothing
	}
}

package org.lc3simulator.model;

import org.lc3simulator.instructions.*;

public class AbstractCPU {
	int reg[] = new int[8];
	int PC;
	int memory[]= new int[128];
	int str;
	int last_altered_reg;

	public AbstractCPU () {
		// TODO Auto-generated constructor stub
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
    
	/**
	 * Executes the instruction in single stage pipeline.
	 * @param instrt, an int holding the unsigned short of
	 * 			the instruction to be run
	 */
	protected void execute(int instr) {
		// The "struct" of our instruction
		Instruction n;
		int mark = this.PC;
		
        if (this.memory[mark] == 0x0) {
        	System.out.println("Halt.");
        	return;
        }
        
        // Fetch
        System.out.println("——————————————————————————");
        System.out.println("Fetch finish, the instruction is: "
        		+ intToString(this.memory[mark], 4));
        this.PC++;
        
        // Decode
        n = this.decode(this.memory[mark]);    
        System.out.printf("Number of instructions executed: %d\n",
        		this.PC - 48);
        
        // Execute
        n.execute(this.reg);
	}
		
	protected Instruction decode(int instr) {
		// The "struct" of our instruction
		Instruction n;
		InstructionType opcode = InstructionType.decode(instr);

		switch (opcode) {
		case ADD:
			n = new OP_ADD(this.PC, instr);
			break;
		case AND:
			n = new OP_AND(this.PC, instr);
			break;
		case NOT:
			n = new OP_NOT(this.PC, instr);
			break;
		case ST:
			n = new OP_ST(this.PC, instr);
			break;
		case STI:
			n = null;
			break;
		case STR:
			n = null;
			break;
		case LD:
			n = null;
			break;
		case LDI:
			n = null;
			break;
		case LDR:
			n = null;
			break;
		case LEA:
			n = null;
			break;
		case BR:
			n = null;
			break;
		case JSR_JSRR:
			n = null;
			break;
		case TRAP:
			n = null;
			break;
		case JMP_RET:
			n = null;
			break;
		default:
			System.err.println("ERR: Failed to find opcode on [ "
					+ intToString(instr, 4) + "] .");
			return null;
		}
		System.out.println("Decoded " + opcode);
		return n;
	}


}

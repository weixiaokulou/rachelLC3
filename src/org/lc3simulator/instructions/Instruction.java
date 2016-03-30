/**
 * 
 */
package org.lc3simulator.instructions;

public abstract class Instruction {
	/**
	 * The previous and next instruction
	 */
	protected Instruction prev, next;
	/**
	 * The instruction line this instruction is found on.  Also,
	 * known as the program counter.
	 */
	public final long pc;
	/**
	 * The instruction type
	 */
	public final InstructionType opcode = null;
	/**
	 * The raw 16bit instruction
	 */
	public final int instr;
	
	/**
	 * 
	 * @param pc, the instruction line row--program counter
	 */
	public Instruction(long pc, int instr) {
		prev = null;
		next = null;
		this.pc = pc;
		this.instr = instr;
	}

	public abstract void execute(int[] reg);
	
	public abstract void mem(int[] reg, int[] memory);
}

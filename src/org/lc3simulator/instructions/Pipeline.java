package org.lc3simulator.instructions;

public class Pipeline {

	protected Instruction head, tail;
	
	protected void addInstruction(long instructionLine, Instruction n) {
		/*
		 * myPipeline.addInstruction(InstructionType.OP_ADD);
		 * int foo = InstructionType.OP_ADD.opcode;
		 */
		// add to empty list
		if (head == null) {
			// redundant
			n.next = null;
			n.prev = null;
			
			head = n;
			tail = n;
		}
		// add to end
		else {
			tail.next = n;
			n.prev = tail;
			// redundant
			n.next = null;
			
			tail = n;
		}
	}
}

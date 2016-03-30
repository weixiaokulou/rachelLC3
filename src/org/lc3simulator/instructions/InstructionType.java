package org.lc3simulator.instructions;

public enum InstructionType {
	ADD (1, "ADD"),
	AND (5, "AND"),
	NOT (9, "NOT"),
	ST (3, "ST"),
	STI (11, "STI"),
	STR (7, "STR"),
	LD (2, "LD"),
	LDI (10, "LDI"),
	LDR (6, "LDR"),
	LEA (14, "LEA"),
	BR (0, "BR"),
	JSR_JSRR (4, "JSR_JSRR"),
	TRAP (15, "TRAP"),
	JMP_RET (12, "JMP_RET");

	final int opcode;
	final String name;
	public static final int OP_ADD = 1, OP_AND = 5, OP_NOT = 9,
			OP_ST = 3, OP_STI = 11, OP_STR = 7, OP_LD = 2,
			OP_LDI = 10, OP_LDR = 6, OP_LEA = 14, OP_BR = 0,
			OP_JSR_JSRR = 4, OP_TRAP = 15, OP_JMP_RET = 12;

	InstructionType(int opcode, String name) {
		this.opcode = opcode;
		this.name = name;
	}
	
	public String toString() {
		return name;
	}

	public static InstructionType decode(int instr) {
		switch (instr >> 12) {
		case OP_ADD:
			return ADD;
			
		case OP_AND:
			return AND;

		case OP_NOT:
			return NOT;

		case OP_ST:
			return ST;

		case OP_STI:
			return STI;

		case OP_STR:
			return STR;

		case OP_LD:
			return LD;

		case OP_LDI:
			return LDI;

		case OP_LDR:
			return LDR;

		case OP_LEA:
			return LEA;

		case OP_BR:
			return BR;

		case OP_JSR_JSRR:
			return JSR_JSRR;

		case OP_TRAP:
			return TRAP;

		case OP_JMP_RET:
			return JMP_RET;

		default:
			return null;
		}
	}
}

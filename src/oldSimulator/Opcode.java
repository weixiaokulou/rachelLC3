package oldSimulator;
import java.io.*;

public class Opcode {
	public static final int OP_ADD=1;
	public static final int OP_AND=5;
	public static final int OP_NOT=9;
	public static final int OP_ST=3;
	public static final int OP_STI=11;
	public static final int OP_STR=7;
	public static final int OP_LD=2;
	public static final int OP_LDI=10;
	public static final int OP_LDR=6;
	public static final int OP_LEA=14;
	public static final int OP_BR=0;
	public static final int OP_JSR_JSRR=4;
	public static final int OP_TRAP=15;
	public static final int OP_JMP_RET=12;
}
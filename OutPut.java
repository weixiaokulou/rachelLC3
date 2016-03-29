import java.io.*; 
class OutPut { 
public static void main(String [] args) {
  	int array[];
  	int arrayname;

		}
		public static void print(int[] array,int arraytype, int arrayname) {
			try {
					BufferedWriter outputWriter = null;
					String filename=getfilename(arraytype,arrayname);
					outputWriter = new BufferedWriter(new FileWriter(filename));
				  	for (int i = 0; i < array.length; i++) {
				  	if(arraytype==2){
				  		outputWriter.write("reg["+i+"]= "+array[i]+"  ");
				   		}
				    else{
				    	outputWriter.write("memory["+i+"]= "+array[i]+"  ");
				    	}

				    if((i+1)%8==0){
				    outputWriter.newLine();
					}
				  }
					  outputWriter.flush();  
					  outputWriter.close();  
				}
			catch(Exception e){
				System.out.println("error: can't print...");
			}
		}
		public static String getfilename(int arraytype, int arrayname) {
			StringBuilder filename = new StringBuilder();
			filename.append(Integer.toString(arrayname));
			filename.append(".txt");
			//String filename="abcd.txt";
			return filename.toString();
		}
}
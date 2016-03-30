import java.awt.Font;
import java.awt.SystemColor;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Simconsle extends JTextArea{

	public static JTextArea console(final InputStream out) {
    final JTextArea area = new JTextArea();
    area.setBackground(SystemColor.control);
    area.setBorder(BorderFactory.createLoweredBevelBorder());
    area.setEditable(false);
    
    // handle "System.out"
    new SwingWorker<Void, String>() {
        @Override protected Void doInBackground() throws Exception {
            Scanner s = new Scanner(out);
            while (s.hasNextLine()) publish(s.nextLine() + "\n");
            return null;
        }
        @Override protected void process(List<String> chunks) {
            for (String line : chunks) area.append(line);
        }
    }.execute();
    return area;
	}

}
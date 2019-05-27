import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
	

public class WorkingDialog extends JFrame{
	private static final long serialVersionUID = 1L;
	JTextArea textArea = new JTextArea(5, 20);
	JScrollPane scrollPane = new JScrollPane(textArea); 
	WorkingDialog(){
			//progress bar	
			textArea.setEditable(false);
			textArea.setBounds(10,10,100,100);
			scrollPane.setBounds(10,10,100,100);	
			textArea.setVisible(true);
			scrollPane.setVisible(true);
			
			setBounds(0,0, 400, 500);
			setLocationRelativeTo(null);
			setFocusable(true);
			setResizable(true);
			add(scrollPane);
			setTitle("Working on it...");
			if (!(Main.Showcheckbox.isSelected())) {return;} else {setVisible(true);}
			
		}
		
		public void forceShow() {
			this.setVisible(true);
		}
	
		public void Close(boolean overideAutoclose) {
			if (Main.Closecheckbox.isSelected()) {
				if (!overideAutoclose) {
				this.dispose();}}
		
	
			setTitle("Detached from working thread.");
			textArea.append(newline + "============================");
			textArea.append(newline + "[Dialog] Released from working thread." + newline + "[Dialog] This dialog is now detatched.");
			textArea.append(newline + "[Dialog] Safe to close dialog.");
			textArea.append(newline + "============================");}

		public void newLine() {
			textArea.append(newline);
		}
		
		private final static String newline = "\n";
	
		public void SetText(String string) {
			textArea.append(string + newline);

		}
}

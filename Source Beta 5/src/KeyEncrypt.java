import javax.swing.JOptionPane;

public class KeyEncrypt {
	
	public static void Encrypt() {
	//String
	String toEncrypt = JOptionPane.showInputDialog("Input string");
	
	if (!(Word_API.inputIsValid(toEncrypt))){
		JOptionPane.showMessageDialog(Main.canvas, "Invalid input");
		return;
	}
	
	
	
	int rotation = 0;
	
	//Encryption
	boolean rotationSet = true;
	while (rotationSet) {
	try {
	rotation = Integer.parseInt(JOptionPane.showInputDialog("Input rotation (1 - 256)"));
	if (rotation < 1) {throw new Exception();}
	if (rotation > 256) {throw new Exception();}
	rotationSet = false;
	} catch (Exception e1) {
		JOptionPane.showMessageDialog(Main.canvas, "Invalid rotation");
	}}
	
	String output = EncryptString(toEncrypt, rotation);

	JOptionPane.showMessageDialog(Main.canvas, output);
	Main.CopyToClipboard(output);
	}
	
	public static void Decrypt() {
		String toDecrypt = JOptionPane.showInputDialog("Input string");
		int rotation = 0;
		if (!(Word_API.inputIsValid(toDecrypt))){
			JOptionPane.showMessageDialog(Main.canvas, "Invalid input");
			return;
		} 
		
			try {
			rotation = Integer.parseInt(JOptionPane.showInputDialog("Input rotation"));
			if (rotation < 1) {throw new Exception();}
			if (rotation > 256) {throw new Exception();}

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(Main.canvas, "Invalid rotation");
				return;
			}
		

		String output = DecryptString(toDecrypt, rotation);
		JOptionPane.showMessageDialog(Main.canvas, output);
		Main.CopyToClipboard(output);
	}
	
	public static String DecryptString(String string, int Key) {
		Character character = ' ';
		int conversion = 0;
		int count = 1;
		String output = "";
		while (count < string.length() + 1) {
			character = string.charAt(count -1);
			
			conversion = (int) character;
			conversion += -Key;
			output += (char) conversion ;
			count++;
	}
	return output;
	}
	
	public static String EncryptString(String string, int Key) {
		Character character = ' ';
		int conversion = 0;
		int count = 1;
		String output = "";
		while (count < string.length() + 1) {
			character = string.charAt(count -1);
			
			conversion = (int) character;
			conversion += Key;
			output += (char) conversion ;
			count++;
		}
		return output;
	}
	
}

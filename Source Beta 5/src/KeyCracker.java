import javax.swing.JOptionPane;

public class KeyCracker {

	public static void CrackWithOutput() {
		String Input = JOptionPane.showInputDialog("Input string");
		String Output = JOptionPane.showInputDialog("Output string");
		String crackOutput = "";
		Character character;
		int conversion = 0;
		int tomatch;
		
		if (!(Word_API.inputIsValid(Input)) || !(Word_API.inputIsValid(Output))){
			JOptionPane.showMessageDialog(Main.canvas, "Invalid input");

			return;
		} 
		
		character = Output.charAt(0);
		tomatch = (int) character;
		character = Input.charAt(0);
		
		for (int key = 1; key <= 256; key++) {
			conversion = (int) character;
			conversion += key;
			
			
			
			if (conversion == tomatch) {
				crackOutput = KeyEncrypt.DecryptString(Input, key);			
				if (crackOutput.equals(Output)) {
					JOptionPane.showMessageDialog(Main.canvas, "Cracked the key! It is: " + key);
					return;
				}	
			}
		}	
		JOptionPane.showMessageDialog(Main.canvas, "Nope");
	}	
	
	
	
	public static void CrackByWord() {
		int key = -999;
		WorkingDialog dialog = new WorkingDialog();
		dialog.SetText("[Cracker] Checking if Word_API needs initialising");
		if (!(Word_API.getIsInitialised())) {
			dialog.SetText("[Cracker] It does, attempting to initialise Word_API");
			if (!(Word_API.initialise())) {
				JOptionPane.showMessageDialog(Main.canvas, "[Cracker] Failed to initialise the Word API. Cannot continue.");
				dialog.SetText("[Cracker] Failed to initialise Word_API, returning.");
				dialog.Close(false);
				return;
			} else {
				dialog.SetText("[Cracker] Word_API.initialise called, returned 'true'.");
			}
		} else {
			dialog.SetText("[Cracker] Word_API already initalised!");
		}
		
		dialog.SetText("[Cracker] Word_API is verified to be ready for use. Coninuing.");
		dialog.SetText("[Cracker] Awaiting user input.");
		String Input = JOptionPane.showInputDialog("Input string");
		
		if (!(Word_API.inputIsValid(Input))){
			dialog.SetText("[Cracker] Word_API failed input validation");
			dialog.SetText("[Cracker] Likely caused by a user operation cancel.");
			dialog.SetText("[Cracker] Detatching and returning..");
			JOptionPane.showMessageDialog(Main.canvas, "Invalid input");
			dialog.Close(false);
			return;
		} 
		
		String crackOutput = "";
		String Keys = "";
		dialog.SetText("[Cracker] Starting crack attempt.");
		dialog.newLine();
		try {
		for (key = 1; key <= 256; key++) {
		dialog.SetText("[Cracker] Checking key [" + key + "]");
		crackOutput = "";
			crackOutput = KeyEncrypt.DecryptString(Input, key);
		if (Word_API.isWord(crackOutput)){
			Keys += key + " ";
			dialog.SetText("[Cracker] [" + key + "] seems to be a valid key.");
		} else {
			dialog.SetText("[Cracker] [" + key + "] does not appear to be a valid key.");
		
		}
		dialog.SetText(Word_API.getPrevResualt());
		dialog.newLine();
		}} catch (Exception e) {
			dialog.newLine();
			dialog.SetText("[Cracker] Exception occoured in Cracker!");
			dialog.SetText("[Cracker] Failed to finish crack attempt");
			dialog.SetText("[Cracker] Stopped at key " + key);
			dialog.SetText("[Cracker] With exception : " + e.getMessage());
			dialog.SetText("[Cracker] With cause : " + e.getCause());
			dialog.SetText("[Cracker] In class : " + e.getClass());
			dialog.SetText("[Cracker] VV[STACK TRACE]VV");
			dialog.newLine();
			dialog.SetText(e.getStackTrace().toString());
			dialog.newLine();
			dialog.SetText("[Cracker] This can be caused by invalid user input.");
			dialog.SetText("[Cracker] Close this dialog, and try again.");
		}		
		String Message;
		if (! (Keys.equals(""))) {
			Message = "My best guesses are: " + Keys;
			dialog.SetText("============================");
			dialog.newLine();
			dialog.SetText("[Cracker] Finished successfully with keys: " + Keys);
		} else {
			dialog.SetText("============================");
			dialog.newLine();
			Message = "No valid key was found.";
			dialog.SetText("[Cracker] Finished successfully with no valid key found");
		}
		dialog.Close(false);
		JOptionPane.showMessageDialog(Main.canvas, Message);
		
		}
	
	public static void CrackByPartial() {
		WorkingDialog dialog = new WorkingDialog();
		dialog.SetText("[Cracker] Awaiting user input");
		String Input = JOptionPane.showInputDialog(Main.canvas, "Input String");
		String Partial = JOptionPane.showInputDialog(Main.canvas, "Partial Output");	
		

		if (!(Word_API.inputIsValid(Input)) || !(Word_API.inputIsValid(Partial))){
			dialog.SetText("[Cracker] Word_API failed input validation");
			dialog.SetText("[Cracker] Likely caused by a user operation cancel.");
			dialog.SetText("[Cracker] Detatching and returning..");
			JOptionPane.showMessageDialog(Main.canvas, "Invalid input");
			dialog.Close(false);
			return;
		} 
		
		int Key = 0;
		String Keys = "";
		for (Key = 1; Key <= 256; Key++) {
			dialog.SetText("[Cracker] Checking key [" + Key + "]");				
			if (KeyEncrypt.DecryptString(Input, Key).contains(Partial)){
				Keys += Key + " ";
				dialog.SetText("[Cracker] [" + Key + "] seems to be a valid key.");
			} else {
				dialog.SetText("[Cracker] [" + Key + "] does not appear to be a valid key.");
			}}
		
		String Message = "";
		if (! (Keys.equals(""))) {
			Message = "My best guesses are: " + Keys;
			dialog.SetText("============================");
			dialog.newLine();
			dialog.SetText("[Cracker] Finished successfully with keys: " + Keys);
		} else {
			dialog.SetText("============================");
			dialog.newLine();
			Message = "No valid key was found.";
			dialog.SetText("[Cracker] Finished successfully with no valid key found");
		}
		dialog.Close(false);
		JOptionPane.showMessageDialog(Main.canvas, Message);
	}
	
	public static void CrackAll() {
		WorkingDialog dialog = new WorkingDialog();
		dialog.forceShow();
		dialog.SetText("[Cracker] Awaiting user input.");
		String Input = JOptionPane.showInputDialog(Main.canvas, "Input string");
		if (!(Word_API.inputIsValid(Input))) {
			dialog.SetText("[Cracker] Invalid input string. Either empty, or user cancelled. Returning.");
			JOptionPane.showMessageDialog(Main.canvas, "Invalid string, cannot be empty.");
			return;			
		}
		dialog.newLine();
		for (int Key = 1; Key <= 256; Key++) {
			dialog.SetText("[Key " + Key + "] " + KeyEncrypt.DecryptString(Input, Key));
		}
		
		dialog.Close(true);
	}
	
	public static void Sentence() {
		WorkingDialog dialog = new WorkingDialog();
		dialog.SetText("[Cracker] Checking if Word_api is initialised.");
		if (Word_API.getIsInitialised()) {
			dialog.SetText("[Cracker] It already is!");
		} else {
			dialog.SetText("[Cracker] It is not, initialising Word_api.");
			if (!Word_API.initialise()) {
				dialog.SetText("[Cracker] Word_API failed to initialise.");
				JOptionPane.showMessageDialog(Main.canvas, "Word API could not be initialised, cracking cannot continue.");
				return;
			} else {
				dialog.SetText("[Cracker] Word_API started sucessfully!");
			}
		}
		dialog.SetText("[Cracker] Word_API verified to be ready for use. Continuing.");
		
		dialog.SetText("[Cracker] Awaiting user input");
		String input = JOptionPane.showInputDialog("Input string");
		String decrypt;
		String ValidKeys = " ";
		

		if (!(Word_API.inputIsValid(input))){
			dialog.SetText("[Cracker] Word_API failed input validation");
			dialog.SetText("[Cracker] Likely caused by a user operation cancel.");
			dialog.SetText("[Cracker] Detatching and returning..");
			JOptionPane.showMessageDialog(Main.canvas, "Invalid input");
			dialog.Close(false);
			return;
		} 
		
		
		dialog.SetText("[Cracker] Starting crack.");
		for (int Key = 1; Key <= 256; Key++) {
			dialog.newLine();
			dialog.SetText("[Cracker] Trying key [" + Key + "]");
			decrypt = KeyEncrypt.DecryptString(input, Key);
			int count = Word_API.getWordCount(decrypt);
			
			if (count < 1) {
				dialog.SetText("[Cracker] " + decrypt + " Contains less than one word. Skipping.");
				continue;
			}  else {
				dialog.SetText("[Cracker] Testing " + decrypt);
				if (Word_API.isWord(Word_API.getWordCountFirstWord())) {
					dialog.SetText("[Cracker] Key [" + Key + "] appears to be valid. Added it to success list.");
					ValidKeys += String.valueOf(Key);
					ValidKeys += " ";
				} else {
					dialog.SetText("[Cracker] " + decrypt + " does not appear to be a valid string.");
					continue;
				}
							
			}
				
			
			
			
			
		}		
		
		
		
		dialog.SetText("[Cracker] Completed loop testing. ValidKeys = " + ValidKeys);
		dialog.newLine();
		dialog.Close(false);		
		
		String message;
		if (ValidKeys.equals(" ")) {
			message = "No valid keys were found!";
		} else {
			message = "My best guesses are:" + ValidKeys;
		}
		JOptionPane.showMessageDialog(Main.canvas, message);
		
		
	}
	
}

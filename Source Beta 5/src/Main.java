import java.awt.Canvas;
import java.awt.KeyEventDispatcher;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class Main extends JFrame implements Runnable, KeyListener, KeyEventDispatcher, ActionListener {
	private static final long serialVersionUID = 5074300128065680059L;
	public static Canvas canvas = new Canvas();
	public static boolean inFocus = false;
	//private RenderHandler renderer;
	public static Integer FrameLimit = 30, WindowX = 170, WindowY = 380, posx = 0, posy = 0; //170 280
	public static Thread gameThread;
	public static String CurrentWindow = "Menu";
	
	//JFRAME OBJECTS
	//Buttons
	JButton MenuBtnEncrypt = new JButton();
	JButton MenuBtnKeyDecrypt = new JButton();
	JButton MenuBtnOutput = new JButton();
	JButton MenuBtnPartialOutput = new JButton();
	JButton MenuBtnDisplayAllKeys = new JButton();
	JButton MenuBtnWordCheckChar = new JButton();
	JButton MenuBtnWordCheckSpace = new JButton();
	static JCheckBox Closecheckbox = new JCheckBox();
	static JCheckBox Showcheckbox = new JCheckBox();
	
	//Instantiate
	public Main(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(posx,posy, WindowX, WindowY);
		setLocationRelativeTo(null);
		add(canvas);
		setFocusable(true);
		setResizable(false);
		setTitle("Cody");
		
		//Prepare jframe obejcts
		MenuBtnEncrypt.setBounds(10,10,150,40);
		MenuBtnEncrypt.setText("Encrypt w/ a key");
		MenuBtnEncrypt.addActionListener(this);
		add(MenuBtnEncrypt);
		
		MenuBtnKeyDecrypt.setBounds(10,50,150,40);
		MenuBtnKeyDecrypt.setText("Decrypt w/ a key");
		MenuBtnKeyDecrypt.addActionListener(this);
		add(MenuBtnKeyDecrypt);
		
		MenuBtnOutput.setBounds(10,90,150,40);
		MenuBtnOutput.setText("Decrypt w/ output");
		MenuBtnOutput.addActionListener(this);
		add(MenuBtnOutput);
		
		MenuBtnPartialOutput.setBounds(10,130,150,40);
		MenuBtnPartialOutput.setText("Decrypt w/ partial");
		MenuBtnPartialOutput.addActionListener(this);
		add(MenuBtnPartialOutput);
		
		MenuBtnDisplayAllKeys.setBounds(10,170,150,40);
		MenuBtnDisplayAllKeys.setText("Decrypt w/ all keys");
		MenuBtnDisplayAllKeys.addActionListener(this);
		add(MenuBtnDisplayAllKeys);
		
		MenuBtnWordCheckChar.setBounds(10,210,150,40);
		MenuBtnWordCheckChar.setText("Decrypt by sentence");
		MenuBtnWordCheckChar.addActionListener(this);
		add(MenuBtnWordCheckChar);
		
		MenuBtnWordCheckSpace.setBounds(10,250,150,40);
		MenuBtnWordCheckSpace.setText("Decrypt by word");
		MenuBtnWordCheckSpace.addActionListener(this);
		add(MenuBtnWordCheckSpace);
		
		Showcheckbox.setBounds(10,300,200,20);
		Showcheckbox.setText("Show outputs?");
		Showcheckbox.setVisible(true);
		Showcheckbox.setSelected(true);
		add(Showcheckbox);
		
		Closecheckbox.setBounds(10,325,200,20);
		Closecheckbox.setText("Autoclose outputs?");
		Closecheckbox.setVisible(true);
		add(Closecheckbox);
		
		Word_API.initialise();
		JLabel l = new JLabel();
		add(l);
		setVisible(true);
		}
	
	
	@Override
	 public void keyPressed(KeyEvent e) {} @Override public void keyTyped(KeyEvent e) {}	@Override public void keyReleased(KeyEvent e) {}
	
	public void run() {
		while(true) {
		posx = this.getX();
		posy = this.getY();
		
		}}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
	return false;
	}
	
	public static void main(String[] args){
		gameThread = null;
		Main game = null;
		game = new Main();
		gameThread = new Thread(game);
		gameThread.start();				
		}



	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		super.setVisible(false);
		switch (e.getActionCommand()) {
		case "Encrypt w/ a key":
			KeyEncrypt.Encrypt();
		break;
		case "Decrypt w/ a key":
			KeyEncrypt.Decrypt();
		break;
		case "Decrypt w/ output":
			KeyCracker.CrackWithOutput();
		break;
		case "Decrypt by word":
			KeyCracker.CrackByWord();
		break;
		case "Decrypt w/ partial":
			KeyCracker.CrackByPartial();
		break;
		case "Decrypt w/ all keys":
			KeyCracker.CrackAll();
		break;
		case "Decrypt by sentence":
			KeyCracker.Sentence();
		break;
	}
	super.setVisible(true);	
	}
	
	
	
	public static void CopyToClipboard(String myString) {
		StringSelection stringSelection = new StringSelection(myString);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);	
	}
	
	
	
	
}
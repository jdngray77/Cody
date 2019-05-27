import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Word_API
 * Uses a pre-defined list of words to determine if
 * parsed word exists.
 * 
 * @author Jordan Gray 
 * @author Jaime Watts
 * @category Boolean, String Verification.
 * @serial W19D18
 * @version 3
 *
 * TODO String clean method.
 */
public class Word_API {
//No note as not visible to api user or end user.
private static String PrevResualt = "[Word_API] Not Initialised";
private static int WordIndex, CallCount, ReadFailCount, ErrorCount, Matches, FailedMatches, initialiseCount;
private static boolean isInitialised = false;
private static String[] WordList = new String[59977];
	/**
	 * initialise(){}
	 * CHECK IF IS INITIALISED BEFORE CALLING!
	 * @see getIsInitialised();
	 * 
	 * @return false if encountered
	 * a problem initialising OR
	 * if already initialised.
	 * Use getIsInitialised to determine which.
	 * 
	 *  Reads Words.txt, populates array it
	 *  and initialises variables.
	 */
	@SuppressWarnings("resource")
	public static boolean initialise() {
		if (isInitialised) {return false;}
		Scanner words = null;
		try { words = new Scanner(new File("Words.txt"));} catch (FileNotFoundException e) {setReadFailCount(getReadFailCount() + 1); isInitialised = false; return false;} 
		try {
		for (int i = 0 ; i < WordList.length ; i ++) {
			WordList[i] = words.nextLine();  
			}
		}catch (Exception e) {
			setErrorCount(getErrorCount() + 1);
			isInitialised = false;
			return false;
		}
		
		WordIndex = -1;
		CallCount = 0;
		ReadFailCount = 0;
		ErrorCount = 0;
		Matches = 0;
		FailedMatches = 0;
		WordCountUsed = false;
		PrevResualt = "[Word_API] Initialised, Not yet used.";
		isInitialised = true;
		setInitialiseCount(getInitialiseCount() + 1);
		return true;
	}
	
	
	/**
	 * getWordCount()
	 * 
	 * Used to get count of blocks in a string, as seperated by a space.
	 *
	 * @param input string to count
	 * @return count of words, broken by spaces in a string.
	 * @return -1 on invalid string.
	 */
	private static String[] words;
	private static boolean WordCountUsed = false;
	public static int getWordCount(String input) {
		WordCountUsed = true;		
		if (input == null || input.isEmpty()) {return -1;} 
		
		words = input.split("\\s+");
		return words.length; }

	/**
	 * getWordCountFirstWord()
	 *
	 * @return first word in list as defined by getWordCount.
	 * @return -1 if word list is not created yet.
	 */
	public static String getWordCountFirstWord() {
		if (!(WordCountUsed)) {
			return "-1";
		}
		
		return words[0];		
	}
	/**
	 * isWord(String Word){}
	 * @return boolean representing
	 * if parsed string does
	 * or doesn't exist.
	 * @return false if not initialised.
	 */
	public static boolean isWord (String Word) {
		if (!isInitialised) {
			setPrevResualt("[Word_API] isWord called, but i'm not initialised!");
			return false;
		}
		setCallCount(getCallCount() + 1);
		
		try {
		for (int i = 0 ; i < WordList.length ; i ++) {
		if(WordList[i].equals(Word.toLowerCase())) {
				setWordIndex(i);
				setPrevResualt("[Word_API] Word " + Word + " Exists");
				System.out.println("[Word_API] Word " + Word + " Exists");
				return true;
			}
		}
		}catch (Exception e) {
			setErrorCount(getErrorCount() + 1);
		}
		setFailedMatches(getFailedMatches() + 1);
		setPrevResualt("[Word_API] Word " + Word + " does NOT exist!");
		System.out.println("[Word_API] Word " + Word + " does NOT exist!");
		setWordIndex(-1);
		return false;
	}

	public static boolean inputIsValid(String Input) {
		if (!(isInitialised)) {System.out.println("[Word_API] WARN: isValid called, but word api is not initialised.");
		System.out.println("[Word_API] WARN: This is OKAY, but code is using the api, and may may call a function which will not complete.");
		}
		try {
			if (Input.equals("")) {
				return false;
			} else {
				return true;
			}
			} catch (Exception e) {
				return false;}
	}
	
	//---------Getters and Setters-----------//
	/**
	 * getWordIndedx()
	 *  
	 * @return integer of previous word.
	 * @return -1 if word is not found
	 * @return -2 if not Word_API is not initialised.  
	 */
	public static int getWordIndex() {
		if (!isInitialised) {return -2;}
		return WordIndex;
	}

	/**
	 * @deprecated
	 *  
	 * sets previous word index
	 * results in erroneous readings if set manually
	 * 
	 * @param int WordIndex integer to set
	 * @return void without setting if not initialised.
	 */
	public static void setWordIndex(int wordIndex) {
		if (!isInitialised) {return;}
		WordIndex = wordIndex;
	}

	/**
	 * getCallCount()
	 * 
	 * @return int of quanity of times
	 * isWord() has been called. 
	 * @return -1 if not initialised
	 */
	public static int getCallCount() {
		if (!isInitialised) {return -1;}
		return CallCount;
	}

	/**
	 * @deprecated
	 * 
	 * sets quantity of times
	 * isWord has been called.
	 * For internal or debug use only.
	 * 
	 * @param int callCount to set
	 * @return void without setting if not initialised.
	 */
	public static void setCallCount(int callCount) {
		if (!isInitialised) {return;}
		CallCount = callCount;
	}

	/**
	 * getReadFailCount()
	 * 
	 * returns count of failiures to load Words.TXT
	 * 
	 * @return -1 if not initialised
	 * @return ReadFailCount
	 */
	public static int getReadFailCount() {
		if (!isInitialised) {return -1;}
		return ReadFailCount;
	}

	/**
	 * @deprecated
	 * Do not use.
	 */
	public static void setReadFailCount(int readFailCount) {
		if (!isInitialised) {return;}
		ReadFailCount = readFailCount;
	}

	/**
	 * @return count of general errors
	 * encountered in isWord
	 * 
	 * @return -1 if not initalised
	 */
	public static int getErrorCount() {
		if (!isInitialised) {return -1;}
		return ErrorCount;
	}

	/**
	 * @deprecated
	 * Do not use.
	 */
	public static void setErrorCount(int errorCount) {
		if (!isInitialised) {return;}
		ErrorCount = errorCount;
	}

	/**
	 * @return int of words
	 * isWord has verified
	 * as a word.
	 * 
	 * @return -1 if not initialised.
	 */
	public static int getMatches() {
		if (!isInitialised) {return -1;}
		return Matches;
	}

	/**
	 * @deprecated
	 * Do not use.
	 */
	public static void setMatches(int matches) {
		if (!isInitialised) {return;}
		Matches = matches;
	}

	/** 
	 * getFailedMatches()
	 * 
	 * @return number of times
	 * isWord could not verify
	 * parsed string to be
	 * a valid word.
	 * 
	 * @return -1 if not initialised.
	 */
	public static int getFailedMatches() {
		if (!isInitialised) {return -1;}
		return FailedMatches;
	}

	/**
	 * @deprecated
	 * Do not use.
	 */
	public static void setFailedMatches(int failedMatches) {
		if (!isInitialised) {return;}
		FailedMatches = failedMatches;
	}	
	
	/**
	 * getPrevResualt()
	 * 
	 * @return the previous output of Word API,
	 * which indicates it's current state;
	 * including when uninitialised.
	 */
	public static String getPrevResualt() {
		return PrevResualt;
	}
	
	
	/**
	 * @return count of initialisations
	 * @return -1 if not initialised
	 */
	public static int getInitialiseCount() {
		if (!isInitialised) {return -1;}
		return initialiseCount;
	}
		
	public static void setInitialiseCount(int initialiseCount) {
		Word_API.initialiseCount = initialiseCount;
	}
	
	/**
	 * getIsInitialised(){}
	 * returns isInitialised
	 * Represents if the Word_API
	 * is prepared for use.
	 */
	public static boolean getIsInitialised() {
		return isInitialised;
	}
	
	/**
	 * @deprecated
	 * Do not use.
	 */
	public static void setPrevResualt(String prevResualt) {
		if (!isInitialised) {return;}
		PrevResualt = prevResualt;
	}
	//--------^Getters and Setters^----------//
}

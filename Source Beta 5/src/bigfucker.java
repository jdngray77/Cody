import java.math.BigInteger;

public class bigfucker {
 public static BigInteger currstep, prevstep, swap;
 public static boolean set = false;
 
 	bigfucker(){
 		init();
 	}
 
 	private static void init() {
 		 currstep = BigInteger.valueOf(0);
 		 prevstep = BigInteger.valueOf(1);
 	}
 	
 	
 	
 public static BigInteger next() {
	 if (!set) {
		 init();
		 set = true;
	 }
	 swap = currstep;
	 currstep = currstep.add(prevstep);
	 prevstep = swap;
	 
	 
	 swap = null;
	 return currstep;
 }


 
 
 
}

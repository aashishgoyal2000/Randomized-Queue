import edu.princeton.cs.algs4.StdRandom;    
import edu.princeton.cs.algs4.StdIn;    
import java.util.Iterator;

public class Permutation{
	public static void main(String[] args) {

		int inp = Integer.parseInt(args[0]);
	    RandomizedQueue<String> rq = new RandomizedQueue<String>();

		int ind=0;
		while(!StdIn.isEmpty()){
	        rq.enqueue(StdIn.readString());
        	ind++;
	    }
	    Iterator<String> it = rq.iterator();

	    while(it.hasNext() && inp > 0){
	    	System.out.println(it.next());
	    	inp--;
	    }
	}
}
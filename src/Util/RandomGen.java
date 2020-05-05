package Util;

import java.util.PrimitiveIterator;
import java.util.Random;

public final class RandomGen {
	private PrimitiveIterator.OfInt randomIterator;

    
    public RandomGen(int min, int max) {
        randomIterator = new Random().ints(min, max + 1).iterator();
    }

   
    public int nextInt() {
        return randomIterator.nextInt();
    }
}

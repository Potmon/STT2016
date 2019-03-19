package service;

public class Triangle {
    public static boolean ifconsist(int x) {
    	//int a[] = {1,1,1,5,5,20,50};
    	if(x <= 83 && x > 0) {
    		if(x >= 50) {
        		x = x - 50;
        		if(x == 0) {
        			return true;
        		}
        	}
        	if(x >= 20) {
        		x = x - 20;
        		if(x==0) {
        			return true;
        		}
        	}
        	if(x>=5) {
        		x = x - 5;
        		if(x == 0) {
        			return true;
        		}
        	}
        	if(x >= 5) {
        		x = x - 5;
        		if(x == 0) {
        			return true;
        		}
        	}
        	if(x <= 3) {
        		return true;
        	}
    	}
		return false;   	
    }
}

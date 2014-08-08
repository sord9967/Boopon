package com.boopon.util;

import java.text.DecimalFormat;

public class BooponUtils {
	private static DecimalFormat df = new DecimalFormat("##"+"%");
	public static String getAvaliableRate(double up,double down){
		if((up+down)<=0){
			return "0%";
		}
		
		double rate = (up / (up+down));
		return df.format(rate);
	}

}

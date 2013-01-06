package me.blockcat.Utils;

import java.text.DecimalFormat;

public class MoneyFormat {
	
	private static DecimalFormat df1 = new DecimalFormat("####.00");

	public static String formatString(double money) {
		return df1.format(money);
	}
	
	public static double formatDouble(double money) {
		return Double.parseDouble(df1.format(money));
	}

}

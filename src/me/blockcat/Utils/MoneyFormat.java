package me.blockcat.Utils;

import java.text.DecimalFormat;

public class MoneyFormat {
	
	private static DecimalFormat df1 = new DecimalFormat("####.00");

	public static String formatString(double money) {
		String string = df1.format(money);
		if (string.startsWith(".")) {
			string = string.replace(".", "");
			string = "0." + string;
		}
		return string;
	}
	
	public static double formatDouble(double money) {
		return Double.parseDouble(df1.format(money));
	}

}

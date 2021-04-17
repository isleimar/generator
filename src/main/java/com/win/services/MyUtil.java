package com.win.services;

public class MyUtil {

	/**
	 * A common method for all enums since they can't have another base class
	 * @param <T> Enum type
	 * @param c enum type. All enums must be all caps.
	 * @param string case insensitive
	 * @return corresponding enum, or null
	 */
	public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
		if ( c != null && string != null) {
			try {
				return Enum.valueOf(c, string.trim().toUpperCase());
			}catch (Exception e) {
			}
		}
		return null;
	}
	
	public static String getVerifuingDigitCNPJ(String cnpj) {
		int[] op1 =    {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		int[] op2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		if (cnpj == null) return null;		
		if (cnpj.length() < 12 || cnpj.length() > 14) return null;
		cnpj = cnpj.substring(0, 12);
		int digits[] = new int[14];
		int sum = 0;
		for (int i = 0; i < 12; i++) {
			digits[i] = cnpj.charAt(i) - 48;
			if (digits[i] < 0 || digits[i] > 9) return null;
			sum += digits[i] * op1[i];
		}
		sum %= 11;
		digits[12] = (sum < 2) ? 0 : 11 - sum;
		sum = 0;
		for (int i = 0; i < 13; i++) {
			sum += digits[i] * op2[i];
		}
		sum %= 11;
		digits[13] = (sum < 2) ? 0 : 11 - sum;
		return String.format("%d%d", digits[12], digits[13]);		
	}
	
	public static Boolean validateCNPJ(String cnpj) {
		cnpj = cnpj.trim();
		cnpj = cnpj.replace(".", "");
		cnpj = cnpj.replace("-", "");
		cnpj = cnpj.replace("/", "");
		if (cnpj.length() < 14) return false;
		String digits = getVerifuingDigitCNPJ(cnpj);
		if (digits == null) return false;
		cnpj = cnpj.substring(12, 14);
		return digits.equals(cnpj);
	}
}

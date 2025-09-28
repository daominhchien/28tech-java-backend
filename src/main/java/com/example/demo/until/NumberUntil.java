package com.example.demo.until;

public class NumberUntil {
    public static boolean checkNumber(String data) {
		try {
			Long number = Long.parseLong(data);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}
}

package com.hangang.HangangRiver.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class test {
public static void main(String[] args) {
	SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
	Date currentTime = new Date ();
	String mTime = mSimpleDateFormat.format ( currentTime );
System.out.println(mTime+" 00:00:00");


}
}
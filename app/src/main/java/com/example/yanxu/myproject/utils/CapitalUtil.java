package com.example.yanxu.myproject.utils;
/**
 * 汉字转换
 * @author yanxu
 */
public class CapitalUtil {
	
	public static String toChinese(int num){
		String str = null;
		if(0<num&&num<=10){
			str =tohanzi(num);
		}else if(num>10&&num<20){
			str = "十"+tohanzi(num-10);
		}else if(num>=20&&num<30){
			str = "二十"+tohanzi(num-20);
		}else if(num>=30&&num<40){
			str = "三十"+tohanzi(num-30);
		}else if(num>=40&&num<50){
			str = "四十"+tohanzi(num-40);
		}else if(num>=50&&num<60){
			str = "五十"+tohanzi(num-50);
		}else if(num>=60&&num<70){
			str = "六十"+tohanzi(num-60);
		}else if(num>=70&&num<80){
			str = "七十"+tohanzi(num-70);
		}else if(num>=70&&num<80){
			str = "八十"+tohanzi(num-80);
		}else if(num>=80&&num<90){
			str = "九十"+tohanzi(num-90);
		}
		return str;
	}
	private static String tohanzi(int num){
		String str= null;
		switch(num){
		case 0:
			str="";
			break;
		case 1:
			str = "一";
			break;
		case 2:
			str = "二";
			break;
		case 3:
			str = "三";
			break;
		case 4:
			str = "四";
			break;
		case 5:
			str = "五";
			break;
		case 6:
			str = "六";
			break;
		case 7:
			str = "七";
			break;
		case 8:
			str = "八";
			break;
		case 9:
			str = "九";
			break;
		case 10:
			str = "十";
			break;
		}
		return str;
	} 
}

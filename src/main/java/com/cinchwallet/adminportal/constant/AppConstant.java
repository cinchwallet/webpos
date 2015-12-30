package com.cinchwallet.adminportal.constant;


public class AppConstant {

    public static Integer              ENTIRY_TYPE_MERCHANT                          = 1;
    public static Integer              ENTIRY_TYPE_STORE                             = 2;

    public static String  REPORT_TXN           = "TXN_REPORT";
    public static String  REPORT_BALANCE       = "BALANCE_REPORT";


    public enum CardStatus {
	 OPEN(1), CLOSED(0), NEW(2);
	 private int code;
	 private CardStatus(int c) {
	   code = c;
	 }
	 public int getCode() {
	   return code;
	 }
   }


    public enum UserType {
	 STORE_USER(2), ADMIN_USER(1);
	 private int userTypeCode;
	 private UserType(int c) {
	   userTypeCode = c;
	 }
	 public int getUserTypeCode() {
	   return userTypeCode;
	 }
   }


}

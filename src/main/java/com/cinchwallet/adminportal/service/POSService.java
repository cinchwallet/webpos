package com.cinchwallet.adminportal.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cinchwallet.adminportal.model.Merchant;
import com.cinchwallet.adminportal.util.DBConnection;
import com.cinchwallet.adminportal.util.Util;


public class POSService {
    private static String SELECT_BY_USERNAME = "select * from USER_LOGIN where USER_NAME = ? and STATUS = true";

    private static String SELECT_STORE_MERCHANT = "select s.STORE_ID, s.NAME as  store_name, m.MERCHANT_ID, m.NAME as merchant_name from STORE s, MERCHANT m " +
    												"where m.UID = s.MERCHANT_ID and s.UID = ?";

    public Merchant getStoreDetail(String userName, String password){
	Connection lConnection = null;
	PreparedStatement lPreparedStatement = null;
	ResultSet lResultSet = null;
	String dbPassword = null;
	Merchant merchant = null;
	try {
	    lConnection = DBConnection.getConnection();
	    lPreparedStatement = lConnection.prepareStatement(SELECT_BY_USERNAME);
	    lPreparedStatement.setString(1, userName);
	    lResultSet = lPreparedStatement.executeQuery();
	    Integer storeId = null;
	    if (lResultSet != null && lResultSet.next()) {
	    	dbPassword = lResultSet.getString("PASSWORD");
	    	storeId = lResultSet.getInt("PARENT_ID");
	    }
	    if(dbPassword!=null && Util.getMD5(password).equals(dbPassword)){
	    	//fetch the store and merchant details
	    	lPreparedStatement.clearParameters();
		    lPreparedStatement = lConnection.prepareStatement(SELECT_STORE_MERCHANT);
		    lPreparedStatement.setInt(1, storeId);
		    lResultSet = lPreparedStatement.executeQuery();
		    if (lResultSet != null && lResultSet.next()) {
		    	merchant = new Merchant();
		    	merchant.setMerchantId(lResultSet.getString("MERCHANT_ID"));
		    	merchant.setStoreId(lResultSet.getString("STORE_ID"));
		    	merchant.setMerchantName(lResultSet.getString("merchant_name"));
		    	merchant.setStoreName(lResultSet.getString("store_name"));
		    }
	    }
	    
	} catch (SQLException _sqlException) {

	} catch (Exception _Exception) {

	} finally {
	    DBConnection.closeAll(lResultSet, lPreparedStatement, lConnection);
	}
	return merchant;
    }

}

package com.cinchwallet.adminportal.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.cinchwallet.adminportal.model.Merchant;
import com.cinchwallet.adminportal.util.DBConnection;
import com.cinchwallet.adminportal.util.Util;

@Service
public class POSService {
    private static String SELECT_BY_USERNAME = "select * from USER_LOGIN where USER_NAME = ? and STATUS = true";
    
    private static String UPDATE_PASSWORD = "UPDATE USER_LOGIN set PASSWORD =?, UPDATED_TS = ? where USER_NAME = ? and STATUS = true";

    private static String SELECT_STORE_MERCHANT = "select s.STORE_ID, s.NAME as  store_name, m.MERCHANT_ID, m.NAME as merchant_name from STORE s, MERCHANT m " +
    												"where m.UID = s.MERCHANT_ID and s.UID = ?";

	private static String SELECT_STORE_EMAIL = "SELECT s.EMAIL as store_email, m.EMAIL as merchant_email from USER_LOGIN ul, STORE s, MERCHANT m where ul.USER_TYPE=2 and ul.PARENT_ID = s.UID and s.MERCHANT_ID = m.UID and ul.USER_NAME = ?";

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
    
    
    public boolean updatePassword(String userName, String password){
    	Connection lConnection = null;
    	PreparedStatement lPreparedStatement = null;
    	ResultSet lResultSet = null;
    	boolean status = false;
    	try {
    	    lConnection = DBConnection.getConnection();
    	    lPreparedStatement = lConnection.prepareStatement(UPDATE_PASSWORD);
    	    lPreparedStatement.setString(1, password);
    	    lPreparedStatement.setTimestamp(2, new Timestamp(new Date().getTime()));
    	    lPreparedStatement.setString(3, userName);
    	    if(lPreparedStatement.executeUpdate()>0)
    	    	status = true;
    	    
    	} catch (SQLException _sqlException) {

    	} catch (Exception _Exception) {

    	} finally {
    	    DBConnection.closeAll(lResultSet, lPreparedStatement, lConnection);
    	}
    	return status;
        }
    
    public String getStoreEmail(String userName){
    	Connection lConnection = null;
    	PreparedStatement lPreparedStatement = null;
    	ResultSet lResultSet = null;
    	String email = null;
    	
    	try {
    	    lConnection = DBConnection.getConnection();
    	    lPreparedStatement = lConnection.prepareStatement(SELECT_STORE_EMAIL);
    	    lPreparedStatement.setString(1, userName);
    	    lResultSet = lPreparedStatement.executeQuery();
    	    if (lResultSet != null && lResultSet.next()) {
    	    	email = lResultSet.getString("store_email");
    	    	if(email==null)
    	    		email = lResultSet.getString("merchant_email");
    	    }
    	    
    	} catch (SQLException _sqlException) {

    	} catch (Exception _Exception) {

    	} finally {
    	    DBConnection.closeAll(lResultSet, lPreparedStatement, lConnection);
    	}
    	return email;
        }

}

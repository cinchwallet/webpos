package com.cinchwallet.adminportal.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;




/**
 *
 * The <code>DBConnection</code> is the utility class to create and holds the
 * connections. This class is responsible for making database connection
 * available to the resource whoever request for it.
 * <p>
 * Switch application interacts with the following two schemas:
 * <p>
 * <blockquote>
 *
 * <pre>
 * ks_transaction - contains the table used by the switch application.
 * ofbiz - contains the table of MMS application and switch uses this data for validation purpose.
 * </pre>
 *
 * </blockquote>
 * </p>
 *
 * database.properties contains the entire information related to database like
 * url, login credential and connection settings.
 * <p>
 * <blockquote>
 *
 * <pre>
 * txnLog.database.url - defines the url for ks_transaction schema
 * ofbiz.database.url - - defines the url for ofbiz
 * database.userName - username to connect to database
 * database.password - password to connect to database
 * </pre>
 *
 * </blockquote>
 * </p>
 *
 * To point to new database/schema, simply modify the respective information in
 * the database.properties and this class would do entire process to make the
 * connection available.
 * <p>
 * <code>DBConnection</code> uses two BasicDataSource Object, one for each
 * schemas to hold the connections.
 * <p>
 * txnLogdataSource - DataSource for ks_transaction schema.
 * <p>
 * ofbizDataSource - DataSource for ofbiz schema.
 *
 * <p>
 * Two static public methods have been defined to get the connection for each
 * schemas.
 * <p>
 * <blockquote>
 *
 * <pre>
 * getConnection - returns the connection for ofbiz.
 * getTxnConnection - returns the connection for ks_transaction.
 * </pre>
 *
 * </blockquote>
 * </p>
 *
 * @see BasicDataSource
 *
 */
public class DBConnection {
    private static Properties             driverInfo       = new Properties();
    private static BasicDataSource        dataSource       = null;
    private static final Logger           logger           = Logger.getLogger(DBConnection.class);

    /**
     * Initialize the DataSource by reading the database.properties file. Read
     * the database.properties file and load the entire content into
     * driverConfig instance of {@link RPSSimpleConfiguration}. driverInfo,
     * instance of {@link Properties} is initialized by reading the
     * driverConfig.
     *
     * <code>setupDB</code> method is now invoked to initialize the DataSource
     * for ks_transaction/ofbiz.
     *
     * @param propertyFile - contains the entire database related property.
     * @throws Exception
     */
    public synchronized static void init() throws Exception {
	if (dataSource == null) {
	     InputStream inp = DBConnection.class.getClassLoader().getResourceAsStream("database.properties");
	     //InputStream inp  = new FileInputStream(fileName);
	     driverInfo.load(inp);
	     inp.close();
	     setupDataSource();
	}
    }

    private static void setupDataSource() throws Exception {

	dataSource = new BasicDataSource();
	dataSource.setDriverClassName(driverInfo.getProperty(DBConstants.DB_DRIVER_CLASS));
	dataSource.setUsername(driverInfo.getProperty(DBConstants.DB_USER_NAME));
	dataSource.setPassword(driverInfo.getProperty(DBConstants.DB_PASSWORD));
	dataSource.setUrl(driverInfo.getProperty(DBConstants.DB_URL));
	dataSource.setInitialSize(Integer.parseInt(driverInfo.getProperty(DBConstants.DB_INITIAL_SIZE)));
	dataSource.setMaxWait(Long.parseLong(driverInfo.getProperty(DBConstants.DB_MAX_WAIT)));
	dataSource.setMaxActive(Integer.parseInt(driverInfo.getProperty(DBConstants.DB_MAX_ACTIVE)));
	dataSource.setMaxIdle(Integer.parseInt(driverInfo.getProperty(DBConstants.DB_MAX_IDLE)));
	dataSource.setMinIdle(Integer.parseInt(driverInfo.getProperty(DBConstants.DB_MIN_IDLE)));
	dataSource.setValidationQuery(driverInfo.getProperty(DBConstants.DB_VALIDATION_QUERY));
	dataSource.setTestOnBorrow(Boolean.parseBoolean(driverInfo.getProperty(DBConstants.DB_TESTONBORROW)));
	dataSource.setTestWhileIdle(Boolean.parseBoolean(driverInfo.getProperty(DBConstants.DB_TESTWHILEIDLE)));
	dataSource.addConnectionProperty(DBConstants.DB_REWRITEBATCHSTATEMENTS, "true");
	dataSource.setPoolPreparedStatements(true);
	dataSource.setDefaultTransactionIsolation(java.sql.Connection.TRANSACTION_READ_UNCOMMITTED);

    }

    public static java.sql.Connection getConnection() throws Exception {
	if (dataSource == null) {
	    init();
	}
	Connection connection = dataSource.getConnection();
	return connection;
    }

    
    /**
     * Use to reinitialize the txnLogdataSource/ofbizDataSource. It first
     * destroys already created datasource by calling
     * <code>shutdownDataSource</code> and then calls <code>setupDB</code>
     * to create again.
     *
     * @throws Exception
     */
    protected static synchronized void reinitializeDatasource() throws Exception {
	logger.warn("DBConnection:: reinitializeDatasource()");
	shutdownDataSource();
	setupDataSource();
    }

    /**
     * Destroy all the DataSource objects.
     *
     * @throws SQLException
     */
    public static void shutdownDataSource() throws SQLException {
	dataSource.close();
    }

    /**
     * Utility method to close Connection, Statements and Resultset. Application
     * calls this method to close the Connection/Statement/Resultset after done
     * with these object.
     *
     * Connection get returned to its DataSource, once close is called on it.
     * Doing this make the same connection available to some other resource.
     *
     * @param pResultSet - ResultSet to close
     * @param pPrepStmt - Statement to close
     * @param pCon - Connection to close
     */
    public static void closeAll(ResultSet pResultSet, PreparedStatement pPrepStmt, Connection pCon) {
	if (pResultSet != null) {
	    try {
		pResultSet.close();
	    } catch (Exception _Ex) {
		//CWLogger.appLog.error("Exception ::", _Ex);
	    }
	}
	if (pPrepStmt != null) {
	    try {
		pPrepStmt.close();
	    } catch (Exception _Ex) {
		//CWLogger.appLog.error("Exception ::", _Ex);
	    }
	}
	if (pCon != null) {
	    try {
		pCon.close();
	    } catch (Exception _Ex) {
		//CWLogger.appLog.error("Exception ::", _Ex);
	    }
	}
    }
}
package com.swiftcorp.portal.report.util;


import com.crystaldecisions.reports.sdk.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.data.IConnectionInfo;
import com.crystaldecisions.sdk.occa.report.data.ITable;
import com.crystaldecisions.sdk.occa.report.data.Tables;
import com.crystaldecisions.sdk.occa.report.lib.IStrings;
import com.crystaldecisions.sdk.occa.report.lib.PropertyBag;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;


public class JRCHelperSample {
	/**CRJavaHelper.java
      * Changes the DataSource for a specific Table
      * @param clientDoc The reportClientDocument representing the report being used
      * @param reportName     "" for main report, name of subreport for subreport, null for all reports
      * @param tableName          name of table to change.  null for all tables.
      * @param username  The DB logon user name
      * @param password  The DB logon password
      * @param connectionURL  The connection URL
      * @param driverName     The driver Name
      * @param jndiName          The JNDI name
      * @throws ReportSDKException
      */
     public static void changeDataSource(ReportClientDocument clientDoc,
                    String reportName, String tableName,
                    String username, String password, String connectionURL,
                    String driverName,String jndiName) throws ReportSDKException {

          PropertyBag propertyBag = null;
          IConnectionInfo connectionInfo = null;
          com.crystaldecisions.sdk.occa.report.data.ITable origTable = null;
          ITable newTable = null;

          // Declare variables to hold ConnectionInfo values.
          // Below is the list of values required to switch to use a JDBC/JNDI
          // connection
          String TRUSTED_CONNECTION = "false";
          String SERVER_TYPE = "JDBC (JNDI)";
          String USE_JDBC = "true";
          String DATABASE_DLL = "crdb_jdbc.dll";
          String JNDI_OPTIONAL_NAME = jndiName;
          String CONNECTION_URL = connectionURL;
          String DATABASE_CLASS_NAME = driverName;

          // The next few parameters are optional parameters which you may want to
          // uncomment
          // You may wish to adjust the arguments of the method to pass these
          // values in if necessary
          // String TABLE_NAME_QUALIFIER = "new_table_name";
          // String SERVER_NAME = "new_server_name";
          // String CONNECTION_STRING = "new_connection_string";
          // String DATABASE_NAME = "new_database_name";
          // String URI = "new_URI";

          // Declare variables to hold database User Name and Password values
          String DB_USER_NAME = username;
          String DB_PASSWORD = password;

          // Obtain collection of tables from this database controller
          if (reportName == null || reportName.equals("")) {
               Tables tables = clientDoc.getDatabaseController().getDatabase().getTables();
               for(int i = 0;i < tables.size();i++){
                    origTable = tables.getTable(i);
                    if (tableName == null || origTable.getName().equals(tableName)) {
                         newTable = (ITable)origTable.clone(true);

                         // We set the Fully qualified name to the Table Alias to keep the
                         // method generic
                         // This workflow may not work in all scenarios and should likely be
                         // customized to work
                         // in the developer's specific situation. The end result of this
                         // statement will be to strip
                         // the existing table of it's db specific identifiers. For example
                         // Xtreme.dbo.Customer becomes just Customer
                         newTable.setQualifiedName(origTable.getAlias());

                         // Change properties that are different from the original datasource
                         // For example, if the table name has changed you will be required
                         // to change it during this routine
                         // table.setQualifiedName(TABLE_NAME_QUALIFIER);

                         // Change connection information properties
                         connectionInfo = newTable.getConnectionInfo();

                         // Set new table connection property attributes
                         propertyBag = new PropertyBag();

                         // Overwrite any existing properties with updated values
                         propertyBag.put("Trusted_Connection", TRUSTED_CONNECTION);
                         propertyBag.put("Server Type", SERVER_TYPE);
                         propertyBag.put("Use JDBC", USE_JDBC);
                         propertyBag.put("Database DLL",DATABASE_DLL );
                         propertyBag.put("JNDIOptionalName",JNDI_OPTIONAL_NAME );
                         propertyBag.put("Connection URL", CONNECTION_URL);
                         propertyBag.put("Database Class Name", DATABASE_CLASS_NAME);
                         // propertyBag.put("Server Name", SERVER_NAME); //Optional property
                         // propertyBag.put("Connection String", CONNECTION_STRING); //Optional property
                         // propertyBag.put("Database Name", DATABASE_NAME); //Optional property
                         // propertyBag.put("URI", URI); //Optional property
                         connectionInfo.setAttributes(propertyBag);

                         // Set database username and password
                         // NOTE: Even if the username and password properties do not change
                         // when switching databases, the
                         // database password is *not* saved in the report and must be set at
                         // runtime if the database is secured.
                         connectionInfo.setUserName(DB_USER_NAME);
                         connectionInfo.setPassword(DB_PASSWORD);

                         // Update the table information
                         clientDoc.getDatabaseController().setTableLocation(origTable, newTable);
                    }
               }
          }
          // Next loop through all the subreports and pass in the same
          // information. You may consider
          // creating a separate method which accepts
          if (reportName == null || !(reportName.equals(""))) {
               IStrings subNames = clientDoc.getSubreportController().getSubreportNames();
               for (int subNum=0;subNum<subNames.size();subNum++) {
                    Tables tables = clientDoc.getSubreportController().getSubreport(subNames.getString(subNum)).getDatabaseController().getDatabase().getTables();
                    for(int i = 0;i < tables.size();i++){
                         origTable = tables.getTable(i);
                         if (tableName == null || origTable.getName().equals(tableName)) {
                              newTable = (ITable)origTable.clone(true);

                              // We set the Fully qualified name to the Table Alias to keep
                              // the method generic
                              // This workflow may not work in all scenarios and should likely
                              // be customized to work
                              // in the developer's specific situation. The end result of this
                              // statement will be to strip
                              // the existing table of it's db specific identifiers. For
                              // example Xtreme.dbo.Customer becomes just Customer
                              newTable.setQualifiedName(origTable.getAlias());

                              // Change properties that are different from the original
                              // datasource
                              // table.setQualifiedName(TABLE_NAME_QUALIFIER);

                              // Change connection information properties
                              connectionInfo = newTable.getConnectionInfo();

                              // Set new table connection property attributes
                              propertyBag = new PropertyBag();

                              // Overwrite any existing properties with updated values
                              propertyBag.put("Trusted_Connection", TRUSTED_CONNECTION);
                              propertyBag.put("Server Type", SERVER_TYPE);
                              propertyBag.put("Use JDBC", USE_JDBC);
                              propertyBag.put("Database DLL",DATABASE_DLL );
                              propertyBag.put("JNDIOptionalName",JNDI_OPTIONAL_NAME );
                              propertyBag.put("Connection URL", CONNECTION_URL);
                              propertyBag.put("Database Class Name", DATABASE_CLASS_NAME);
                              // propertyBag.put("Server Name", SERVER_NAME); //Optional property
                              // propertyBag.put("Connection String", CONNECTION_STRING); //Optional property
                              // propertyBag.put("Database Name", DATABASE_NAME); //Optional property
                              // propertyBag.put("URI", URI); //Optional property
                              connectionInfo.setAttributes(propertyBag);

                              // Set database username and password
                              // NOTE: Even if the username and password properties do not
                              // change when switching databases, the
                              // database password is *not* saved in the report and must be
                              // set at runtime if the database is secured.
                              connectionInfo.setUserName(DB_USER_NAME);
                              connectionInfo.setPassword(DB_PASSWORD);

                              // Update the table information
                              clientDoc.getSubreportController().getSubreport(subNames.getString(subNum)).getDatabaseController().setTableLocation(origTable, newTable);
                         }
                    }
               }
          }
     }
}

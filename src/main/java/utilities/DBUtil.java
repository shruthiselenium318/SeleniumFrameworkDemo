package utilities;

import java.sql.*;

public class DBUtil {

    private Connection connection=null;
    public  DBUtil() {

        fileUtil fileutil = new fileUtil("config.properties");
        //Read file to get DB Properties
        String url = fileutil.GetProperty("HostUrl");
        String port = fileutil.GetProperty("Port");
        String dbname = fileutil.GetProperty("DBName");
        String username = fileutil.GetProperty("Username");
        String password = fileutil.GetProperty("Password");

        try {
            connection= DriverManager.getConnection("jdbc:mysql://"+url+":"+port+"/"+dbname,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet executeQuery(String query)
    {
        ResultSet resultset=null;
        try {
            Statement statement=connection.createStatement();
            resultset=statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultset;
    }

    public void closeConnection()
    {
        try
        {
            if(connection!=null && !connection.isClosed())
            {
                connection.close();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }




}

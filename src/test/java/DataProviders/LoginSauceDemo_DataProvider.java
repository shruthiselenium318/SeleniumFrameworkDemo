package DataProviders;

import org.testng.annotations.DataProvider;
import utilities.DBUtil;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginSauceDemo_DataProvider {

    DBUtil dbutil=new DBUtil();
    @DataProvider(name = "LoginSauceDemo")
    public Object[][] getData_sauceDemo()
    {
        Object[][] getdata=null;
        List<Map<String,String>> table=new ArrayList<Map<String,String>>();

        String query="select * from login_data;";


        try {
            ResultSet resultset=dbutil.executeQuery(query);
            ResultSetMetaData metadata=resultset.getMetaData();
            int colCount=metadata.getColumnCount();

            while(resultset.next())
            {
                Map<String,String> row=new HashMap<String,String>();
                for(int i=1;i<=colCount;i++)
                {
                    row.put(metadata.getColumnLabel(i),resultset.getString(i));
                }
                table.add(row);
            }

            resultset.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getdata=new Object[table.size()][1];
        for(int i=0;i<table.size();i++)
        {
            getdata[i][0]=table.get(i);
        }
        return getdata;

    }
}

package DataProviders;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.annotations.DataProvider;

import utilities.DBUtil;

public class Accounts_DataProvider {

    
    private static final String TESTCASE_COLUMN = "testcase_name";
    private static final Map<String, List<Map<String, String>>> CACHE = new ConcurrentHashMap<>();

    @DataProvider(name = "Accounts")
    public Object[][] getAccountsData(Method method) {
        return getDataFor("ACCOUNTS_DETAILS", method.getName());
    }

    
    protected static Object[][] getDataFor(String tableName, String testCaseName) {
        List<Map<String, String>> allRows = CACHE.computeIfAbsent(tableName, Accounts_DataProvider::loadTable);

        List<Map<String, String>> filtered = new ArrayList<>();
        for (Map<String, String> row : allRows) {
            if (testCaseName.equalsIgnoreCase(row.get(TESTCASE_COLUMN))) {
                filtered.add(new HashMap<>(row)); // copy, so a test can't modify the cached data
            }
        }

        if (filtered.isEmpty()) {
            throw new IllegalStateException(
                "No rows in " + tableName + " where " + TESTCASE_COLUMN + " = '" + testCaseName + "'");
        }

        Object[][] data = new Object[filtered.size()][1];
        for (int i = 0; i < filtered.size(); i++) {
            data[i][0] = filtered.get(i);
        }
        return data;
    }

    
    private static List<Map<String, String>> loadTable(String tableName) {
        System.out.println("Loading test data from DB table: " + tableName);
        List<Map<String, String>> rows = new ArrayList<>();
        DBUtil dbutil = new DBUtil();

        try (ResultSet rs = dbutil.executeQuery("SELECT * FROM " + tableName)) {
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();
            

            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                for (int i = 1; i <= colCount; i++) {
                    // lower-case keys so "TESTCASENAME" / "TestCaseName" / "testcasename" all match
                    row.put(meta.getColumnLabel(i).toLowerCase(), rs.getString(i));
                }
                rows.add(row);
                
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load data from " + tableName, e);
        } finally {
            dbutil.closeConnection();
        }
        return Collections.unmodifiableList(rows);
    }
}
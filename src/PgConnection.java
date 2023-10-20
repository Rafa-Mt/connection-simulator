import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

public class PgConnection {
    private Connection connection;
    private ResultSet lastResultSet;

    public PgConnection() {
        connection = Connect();
        // System.out.println("Connected to db");
    }

    public Connection Connect() {
        Connection conn;
        PropertiesHandler propFile = new PropertiesHandler("connection");

        String url = MessageFormat.format("jdbc:postgresql://{0}/{1}", propFile.Get("host"), propFile.Get("name"));
        String user = propFile.Get("user");
        String passwd = propFile.Get("passwd");

        try {
            conn = DriverManager.getConnection(url, user, passwd);
            return conn;
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Boolean ExecuteQuery(String query) {
        String queryType = query.split(" ")[0].toUpperCase();
        // System.out.println(MessageFormat.format("Query: {0} ", query));
        Statement state;

        try {
            state = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            if (queryType.equals("SELECT")) {
                ResultSet result = state.executeQuery(query);
                lastResultSet = result;
            }
            else state.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet GetLastRS() {
        try {
            return lastResultSet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


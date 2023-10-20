import java.sql.ResultSet;

public class ConnectionThread extends Thread {
    private int identifier;
    private boolean show;
    private PgConnection connection;
    private String state;

    public ConnectionThread(int identifier, boolean show) {
        super();
        this.identifier = identifier;
        this.show = show;
        this.state = "Initializing";
    }

    @Override
    public void run() {
        this.state = "Running";
        PropertiesHandler props = new PropertiesHandler("querys");
        connection = new PgConnection();
        
        String query = props.Get("Select-All", new String[] { "materia" });
        connection.ExecuteQuery(query);
        if (this.show) {
            System.out.println(String.format("In Thread %d:", this.identifier));
            ResultSet result = connection.GetLastRS();
            Utils.PrintResultSet(result);
        }
        this.state = "Done";
    }

    public String GetState() {
        return this.state;
    }
}
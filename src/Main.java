import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static String connectionString = "jdbc:sqlserver://sql8001.site4now.net; database = db_a8cc79_WaHarom";
    static String username = "db_a8cc79_WaHarom_admin";
    static String password = "Gq68fQ86xA96";

    public static void main(String[] args) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(connectionString,username,password);
            System.out.println(conn);
            addBlade(conn);
            String sql = "select * from movies";
            var stmt = conn.prepareStatement(sql);
            var rs = stmt.executeQuery();
            while(rs.next()) {
                System.out.println("Movie Title: " + (rs.getString("title")+ "   Year : " + (rs.getString("year"))));
//                System.out.print(" ");
//                System.out.println(rs.getString("year"));
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

    private static void addMovie (Connection conn) throws SQLException{
        String sql ="insert into movies (title, year, price) values ('Back to the Future', 1985, 6.99)";
        var stmt = conn.prepareStatement(sql);
        int result = stmt.executeUpdate();
        if (result > 0){
            System.out.println("Update successful");
        }

    }


    private static void addBlade (Connection conn) throws SQLException {

        Scanner reader = new Scanner(System.in);
        System.out.print("Enter Movie name: ");
        String movieName = reader.nextLine();
        System.out.print("Enter Movie year: ");
        int movieYear = reader.nextInt();
        System.out.print("Enter Movie cost: ");
        float movieCost = reader.nextFloat();
        //return class object

        String sql = "insert into movies (title, year, price) values (?,?,?)";
        var stmt = conn.prepareStatement(sql);
        stmt.setObject(1, movieName);
        stmt.setObject(2, movieYear);
        stmt.setObject(3, movieCost);
        int result = stmt.executeUpdate();
        if (result > 0){
            System.out.println("Update successful!");
        }
    }
}

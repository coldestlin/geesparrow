

import java.sql.*;


public class mymy {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://9.135.108.39:3306/?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","DermN*2468yu");
        Statement statement = conn.createStatement();
        String sql = "show databases";
        ResultSet rs = statement.executeQuery(sql);

        System.out.println("bbb");
        System.out.println(rs.getFetchSize());
        while(rs.next()) {
            System.out.println(rs.getString(1));
//            System.out.println(rs.getFetchSize());
        }

        System.out.println("-----next----");
        rs = statement.executeQuery("select * from grafana.user");
        System.out.println(rs.getRow());
        while(rs.next()) {
            System.out.println(rs.getString("login"));
        }
    }

}

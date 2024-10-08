import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        EmployeeRepository test = new EmployeeRepository();

        try {
            test.opgaveA();
            test.opgaveB();
            test.opgaveC();
            test.opgaveD();
            test.opgaveE();

            test.opgaveF("Clark");
            test.opgaveG();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Employee obj: test.getList()) {
            System.out.println(obj.toString());
        }
    }
}

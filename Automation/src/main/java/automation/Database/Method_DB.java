package Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//Chỉ lấy dòng đầu tiên
public class Method_DB extends DBConnection {
	public static String getFieldValue(String query, String fieldName) {
        String value = null;
        try (Connection conn = getConnection();
        		//Tạo đối tượng dùng để chạy SQL query.
             Statement stmt = conn.createStatement();
        		//Thực thi SQL SELECT và lấy kết quả.
             ResultSet rs = stmt.executeQuery(query)) {

            // Move to first row (Di chuyển tới dòng kế tiếp (ở đây là hàng đầu tiên).
            if (rs.next()) {
                value = rs.getString(fieldName); // lấy giá trị cột theo tên (Lấy giá trị của cột theo tên từ dòng hiện tại.)
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }


//Lấy nhiều cột và hàng
public static List<User> getUsers(String query) {
    List<User> users = new ArrayList<>();

    try (Connection conn = DBConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String email = rs.getString("email");

            users.add(new User(id, username, email));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return users;
}

}

package term;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class termp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/board?serverTimezone=UTC","root","root1234");
			Scanner sc=new Scanner(System.in);
			Statement stmt=con.createStatement();
			int menu = 0;
			System.out.println("menu");
			menu=sc.nextInt();
			if(menu == 1 ) {
			ResultSet rs=stmt.executeQuery("select * from board");
			
			while(rs.next())
				System.out.println(rs.getInt(1)+" "+rs.getString(2));
			}
			con.close();
		
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}

package pratice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

public class termp {



	public static void main(String[] args) {

		// TODO Auto-generated method stub

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/team_manage","bae","wjdgus123");
			Scanner sc=new Scanner(System.in);
			Statement stmt=con.createStatement();
			int menu = 0;
			int r;
			int num;
			String a;
			String b;
			String c;
			String d;
			String e;
			String f;
			String g;
			String h;
			int i=1;
			System.out.println("menu");

			System.out.println("1. select table  2.insert   3.delete table");

			System.out.println("4. update table ");

			menu=sc.nextInt();

			String table;

			switch (menu) {

			case 1:
				System.out.println("select 할 테이블 입력 : ");
				a=sc.next();
				ResultSet rs=stmt.executeQuery("select * from "+a);
				ResultSetMetaData rsmd = rs.getMetaData();
				int col=rsmd.getColumnCount();
				while(rs.next()) {
					i=1;
					while(i<=col) {
					System.out.print(rs.getString(i)+" ");
					i++;
					}
					System.out.println(" ");
				}
				break;

			case 2:

				System.out.println("player table 값 입력 : (player_id, name, position, age, height, weight, t_name");
				sc.nextLine();
				
				PreparedStatement pstmt = con.prepareStatement("insert into player (name,position,back_num,height,weight,contract_date,t_name)value(?,?,?,?,?,?,?);");
				//a=sc.next();
				b=sc.next();
				c=sc.next();
				d=sc.next();
				e=sc.next();
				f=sc.next();
				g=sc.next();
				
				sc.nextLine();
				
				h=sc.nextLine();

				
				pstmt.setString(1,b);
				pstmt.setString(2,c);
				pstmt.setString(3,d);
				pstmt.setString(4,e);
				pstmt.setString(5,f);
				pstmt.setString(6,g);
				pstmt.setString(7,h);
				r=pstmt.executeUpdate();
				
				
				if(r==0) {
					System.out.println("내용 입력에 실패하였습니다.");
				}
				else
					System.out.println("내용이 입력 되었습니다.");
				break;

			case 3:
				System.out.println("삭제할 내용 입력 (ex player_id , name , position , back_num , age , height , weight , t_name");
				sc.nextLine();
				String x=sc.nextLine();
				
				stmt = con.createStatement();
				r = stmt.executeUpdate("delete from player where "+ x);

				if( r == 0 ){
					System.out.println("삭제 할  내용을 찾을 수 없습니다.");
				}else{
					System.out.println("삭제 되었습니다.");

				}
				break;

			case 4:
				System.out.println("수정할 내용 입력 : ");
				a=sc.next();
				System.out.println("조건 입력 : ");
				b=sc.next();
				stmt = con.createStatement();
				r = stmt.executeUpdate("update member set "+ a +" where "+ b);
				if( r == 0 ){
					System.out.println("수정 할  내용을 찾을 수 없습니다.");
				}else{
					System.out.println("수정 되었습니다.");
				}
				break;

			case 5:
				System.out.println("검색할 테이블 입력 : ");
				a=sc.next();
				System.out.println("검색할 내용 입력 : ");
				b=sc.next();

				rs=stmt.executeQuery("select * from "+a+" where "+b);
				rsmd = rs.getMetaData();
				col=rsmd.getColumnCount();
				i=1;

				while(rs.next())
					while(i<=col) {
						System.out.print(rs.getString(i)+" ");
						i++;
					}
				break;
			}
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
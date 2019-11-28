package term;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

public class term {



	public static void main(String[] args) {

		// TODO Auto-generated method stub

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://192.168.56.101:4567/team_manage","bae","wjdgus123");
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



			String table;
			do {
				System.out.println("1.select table    2.update table     3.delete table");
				System.out.println("4.조건 select      5.insert table     6.insert team");
				System.out.println("7.insert coach,coach_contract,league,participation,team_coach");
				System.out.println("8.insert stadium  9.insert schedule  10.find win,draw,loss");
				System.out.println("99. quit");
				menu=sc.nextInt();
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
				System.out.println(" ");
				break;

			case 2:
				System.out.println("수정할 테이블 입력 : ");
				a=sc.next();
				System.out.println("수정할 내용 입력 : ");
				b=sc.next();
				System.out.println("조건 입력 : (ex id=3, name='무리뉴')");
				c=sc.next();
				stmt = con.createStatement();
				r = stmt.executeUpdate("update "+a+" set "+ b +" where "+c);
				if( r == 0 ){
					System.out.println("수정 할  내용을 찾을 수 없습니다.");
				}else{
					System.out.println("수정 되었습니다.");
				}
				break;


			case 3:
				System.out.println("삭제할 테이블 입력 : ");
				a=sc.next();
				System.out.println("삭제할 조건 입력 (ex id=1 , name='무리뉴')");
				sc.nextLine();
				String x=sc.nextLine();

				stmt = con.createStatement();
				r = stmt.executeUpdate("delete from "+a+" where "+ x);

				if( r == 0 ){
					System.out.println("삭제 할  내용을 찾을 수 없습니다.");
				}else{
					System.out.println("삭제 되었습니다.");
				}
				break;

			case 4:
				System.out.println("검색할 테이블 입력 : ");
				a=sc.next();
				System.out.println("검색할 내용 입력 : (ex id=9 , name='손흥민'");
				b=sc.next();

				rs=stmt.executeQuery("select * from "+a+" where "+b);
				rsmd = rs.getMetaData();
				col=rsmd.getColumnCount();
	

				while(rs.next()) {
					i=1;
					while(i<=col) {
						System.out.print(rs.getString(i)+" ");
						i++;
					}
					System.out.println("");
				}
				break;

			case 5:

				System.out.println("player table 값 입력 : name,position,back_num,height,weight,contract_date");
				sc.nextLine();

				PreparedStatement pstmt = con.prepareStatement("insert into player(name,position,back_num,height,weight,contract_date,t_name)value(?,?,?,?,?,?,?);");
				b=sc.next();
				c=sc.next();
				d=sc.next();
				e=sc.next();
				f=sc.next();
				g=sc.next();

				sc.nextLine();
				System.out.println("t_name 입력: ");
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
				
			case 6:
				System.out.println("team table 값 입력 : team_name, frm, stadium, c_name");
				sc.nextLine();
				pstmt = con.prepareStatement("insert into team value(?,?,?,?);");
				System.out.print("팀이름 입력 : ");
				b=sc.nextLine();
				System.out.print("연고지 입력 : ");
				c=sc.nextLine();
				System.out.print("구 입력 : ");
				d=sc.nextLine();
				System.out.print("감독이름 입력 : ");
				e=sc.nextLine();

				pstmt.setString(1,b);
				pstmt.setString(2,c);
				pstmt.setString(3,d);
				pstmt.setString(4,e);
				r=pstmt.executeUpdate();
				if(r==0) {
					System.out.println("내용 입력에 실패하였습니다.");
				}
				else
					System.out.println("내용이 입력 되었습니다.");
				break;
			case 7:
				System.out.println("table 입력 (league, coach ,team_coach, coach_contract, paricipation 만 가능)");
				a=sc.next();
				
				pstmt = con.prepareStatement("insert into "+a+" value(?,?);");
				sc.nextLine();
				System.out.println("칼럼1 값 입력 : ");
				b=sc.nextLine();
				System.out.println("칼럼2 값 입력 : ");
				c=sc.nextLine();
				pstmt.setString(1,b);
				pstmt.setString(2,c);
				r=pstmt.executeUpdate();
				if(r==0) {
					System.out.println("내용 입력에 실패하였습니다.");
				}
				else
					System.out.println("내용이 입력 되었습니다.");
				break;
			case 8:
				System.out.println("stadium table 값 입력 : stadium_name, address, ht_name");
				sc.nextLine();
				pstmt = con.prepareStatement("insert into stadium value(?,?,?);");
				System.out.print("구장 이름 입력 : ");
				b=sc.nextLine();
				System.out.print("구장 주소 입력 : ");
				c=sc.nextLine();
				System.out.print("홈 팀 이름 입력 : ");
				d=sc.nextLine();
				

				pstmt.setString(1,b);
				pstmt.setString(2,c);
				pstmt.setString(3,d);
				r=pstmt.executeUpdate();
				if(r==0) {
					System.out.println("내용 입력에 실패하였습니다.");
				}
				else
					System.out.println("내용이 입력 되었습니다.");
				break;
			case 9:
				System.out.println("schedule table 값 입력 : s_num, h_tm, a_tm, h_score, a_score, stadium, schedule, league_name");
				
				pstmt = con.prepareStatement("insert into schedule (h_tm,a_tm,h_score,a_score,stadium,schedule,league_name) value(?,?,?,?,?,?,?);");
				sc.nextLine();
				System.out.print("홈 팀 이름 입력 : ");
				b=sc.nextLine();
				System.out.print("어웨이 팀 이름 입력 : ");
				c=sc.nextLine();
				System.out.print("홈 팀 스코어 입력 : ");
				d=sc.nextLine();
				System.out.print("어웨이팀 스코어 입력 : ");
				e=sc.nextLine();
				System.out.print("구장 입력 : ");
				f=sc.nextLine();
				System.out.print("일정 입력 : ");
				g=sc.nextLine();
				System.out.print("리그 이름 입력 : ");
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
			case 10:
				int win=0;
				int draw=0;
				int loss=0;
				System.out.println("찾고자 하는 팀의 이름과 리그 입력 :");
				sc.nextLine();
				a=sc.nextLine();
				b=sc.nextLine();
				stmt=con.createStatement();
				rs=stmt.executeQuery("select count(*) from schedule where (h_tm = '"+a+"' AND league_name = '"+b+"' AND h_score < a_score) OR (a_tm = '"+a+"' AND a_score<h_score)");
				
				if(rs.next()) {
					loss=rs.getInt(1);
				}
				rs=stmt.executeQuery("select count(*) from schedule where (h_tm = '"+a+"' AND league_name = '"+b+"' AND h_score > a_score) OR (a_tm = '"+a+"' AND a_score>h_score)");
				
				if(rs.next()) {
					win=rs.getInt(1);
				}
				rs=stmt.executeQuery("select count(*) from schedule where (h_tm = '"+a+"' AND league_name = '"+b+"' AND h_score = a_score) OR (a_tm = '"+a+"' AND a_score = h_score)");
				
				if(rs.next()) {
					draw=rs.getInt(1);
				}
				int wm=win*3+draw;
				System.out.println("승 : "+win+" 무 : "+draw+" 패 : "+loss+" 승점 : "+wm);
				System.out.println("");
				break;
			}
				
			}while(menu!=99);
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
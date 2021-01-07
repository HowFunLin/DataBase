package manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import entity.Staff;

public class StaffManage
{
	private static Scanner scan = new Scanner(System.in);
	private static QueryRunner qr = new QueryRunner();

	public static boolean insert(Connection conn) throws SQLException
	{
		String sql = "INSERT INTO Staff VALUES(?,?,?,?,?,?,?,?)";

		System.out.println("输入教职工信息：");
		System.out.print("职工号：");
		String id = scan.nextLine();
		System.out.print("姓名：");
		String name = scan.nextLine();
		System.out.print("性别：");
		String sex = scan.nextLine();
		System.out.print("出生年月：");
		String birth = scan.nextLine();
		System.out.print("所在系号：");
		int departmentID = Integer.valueOf(scan.nextLine());
		System.out.print("职称：");
		String title = scan.nextLine();
		System.out.print("专业：");
		String major = scan.nextLine();
		System.out.print("教学方向：");
		String direct = scan.nextLine();

		if (qr.update(conn, sql, id, name, sex, birth, departmentID, title, major, direct) == 1)
		{
			System.out.println("输入教职工信息成功！");
			return true;
		} else
		{
			System.out.println("输入教职工信息失败！请重新进行操作！");
			return false;
		}
	}

	public static boolean update(Connection conn) throws SQLException
	{
		String sql = "SELECT * FROM Staff WHERE id = ?";
		
		System.out.print("输入要修改的教职工的职工号：");
		String id = scan.nextLine();
		
		Staff d = qr.query(conn, sql, new BeanHandler<Staff>(Staff.class), id);
		if (d == null)
		{
			System.out.println("查无该课程！请重新输入！");
			return false;
		}

		System.out.print("输入要修改的属性名（id, name, sex, birth, departmentID, title, major, direct）：");
		String column = scan.nextLine();
		System.out.print("输入修改后的属性内容：");
		String data = scan.nextLine();

		String update = "UPDATE Staff SET " + column + " = ? WHERE id = ?";

		if (qr.update(conn, update, data, id) == 1)
		{
			if (column.equals("id"))
				id = data;

			System.out.println("修改成功！修改后的信息为：");
			Staff nd = qr.query(conn, sql, new BeanHandler<Staff>(Staff.class), id);
			System.out.println(nd);

			return true;
		} else
		{
			System.out.println("修改失败！");

			return false;
		}
	}
	
	public static boolean delete(Connection conn) throws SQLException
	{
		System.out.print("输入要删除的课程的课程号：");
		String id = scan.nextLine();

		String sql = "DELETE FROM Staff WHERE id = ?";

		if (qr.update(conn, sql, id) == 1)
		{
			System.out.println("已将该课程的信息删除！");
			
			return true;
		} else
		{
			System.out.println("从课程表删除该课程信息失败！请重新进行操作！");
			
			return false;
		}
	}
}

package manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import entity.Department;

public class DepartmentManage
{
	private static Scanner scan = new Scanner(System.in);
	private static QueryRunner qr = new QueryRunner();

	public static boolean insert(Connection conn) throws SQLException
	{
		String sql = "INSERT INTO Department VALUES(?,?,?)";

		System.out.println("输入系的基本信息：");
		System.out.print("系号：");
		String id = scan.nextLine();
		System.out.print("系名称：");
		String name = scan.nextLine();
		System.out.print("系的简介：");
		String intro = scan.nextLine();

		if (qr.update(conn, sql, id, name, intro) == 1)
		{
			System.out.println("输入系的基本信息成功！");
			return true;
		} else
		{
			System.out.println("输入系的基本信息失败！请重新进行操作！");
			return false;
		}
	}

	public static boolean update(Connection conn) throws SQLException
	{
		String sql = "SELECT * FROM Department WHERE id = ?";
		
		System.out.print("输入要修改的系的系号：");
		String id = scan.nextLine();
		
		Department d = qr.query(conn, sql, new BeanHandler<Department>(Department.class), id);
		if (d == null)
		{
			System.out.println("查无该系！请重新输入！");
			return false;
		}

		System.out.print("输入要修改的属性名（id, name，intro）：");
		String column = scan.nextLine();
		System.out.print("输入修改后的属性内容：");
		String data = scan.nextLine();

		String update = "UPDATE Department SET " + column + " = ? WHERE id = ?";

		if (qr.update(conn, update, data, id) == 1)
		{
			if (column.equals("id"))
				id = data;

			System.out.println("修改成功！修改后的信息为：");
			Department nd = qr.query(conn, sql, new BeanHandler<Department>(Department.class), id);
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
		System.out.print("输入要删除的系的系号：");
		String id = scan.nextLine();

		String sql = "DELETE FROM Department WHERE id = ?";

		if (qr.update(conn, sql, id) == 1)
		{
			System.out.println("已将该系的信息删除！");
			
			return true;
		} else
		{
			System.out.println("从系表删除该系信息失败！请重新进行操作！");
			
			return false;
		}
	}
}
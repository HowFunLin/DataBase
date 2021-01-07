package manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

public class Status
{
	private static Scanner scan = new Scanner(System.in);
	private static QueryRunner qr = new QueryRunner();

	public static boolean queryGradeForm(Connection conn) throws SQLException
	{
		System.out.print("选择查询成绩登记表的方式：1-课程号，2-课程名称，3-教师姓名，4-系号 ");
		int tag = Integer.valueOf(scan.nextLine());

		String column;
		String data;

		if (tag == 1)
		{
			column = "Course.id";

			System.out.print("输入要查询的课程号：");
			data = scan.nextLine();
		} else if (tag == 2)
		{
			column = "Course.name";

			System.out.print("输入要查询的课程名称：");
			data = scan.nextLine();
		} else if (tag == 3 || tag == 4)
		{
			if(tag == 3)
			{
				column = "Staff.name";
				
				System.out.print("输入要查询的教师姓名：");
				data = scan.nextLine();
			}
			else 
			{
				column = "Staff.departmentID";
				
				System.out.print("输入要查询的系号：");
				data = scan.nextLine();
			}
			
			String sql = "SELECT courseID 课程号, Course.name 课程名称, staffID 任课教师号, hours 学时, credits 学分, classTime 上课时间, classLocate 上课地点, examDate 考试时间, studentID 学号, Student.name 姓名, Student.sex 性别, peacetime 平时成绩, exam 考试成绩, total 总评成绩 "
					+ "FROM Course, Grade, Student, Staff " + "WHERE courseID = Course.id AND studentID = Student.id AND StaffID = Staff.id AND " + column
					+ " = ? " + "ORDER BY studentID";
			
			return queryGrade(conn, sql, data);
		}
		else
		{
			System.out.println("请选择正确的方式！");
			return false;
		}

		String sql = "SELECT courseID 课程号, Course.name 课程名称, staffID 任课教师号, hours 学时, credits 学分, classTime 上课时间, classLocate 上课地点, examDate 考试时间, studentID 学号, Student.name 姓名, sex 性别, peacetime 平时成绩, exam 考试成绩, total 总评成绩 "
				+ "FROM Course, Grade, Student " + "WHERE courseID = Course.id AND studentID = Student.id AND " + column
				+ " = ? " + "ORDER BY studentID";
		
		return queryGrade(conn, sql, data);
	}
	
	public static boolean queryGradeReport(Connection conn) throws SQLException
	{
		System.out.print("选择查询成绩报表的方式：1-课程号，2-课程名称，3-教师姓名，4-系号 ");
		int tag = Integer.valueOf(scan.nextLine());

		String column;
		String data;

		if (tag == 1)
		{
			column = "Course.id";

			System.out.print("输入要查询的课程号：");
			data = scan.nextLine();
		} else if (tag == 2)
		{
			column = "Course.name";

			System.out.print("输入要查询的课程名称：");
			data = scan.nextLine();
		} else if (tag == 3 || tag == 4)
		{
			if(tag == 3)
			{
				column = "Staff.name";
				
				System.out.print("输入要查询的教师姓名：");
				data = scan.nextLine();
			}
			else 
			{
				column = "Staff.departmentID";
				
				System.out.print("输入要查询的系号：");
				data = scan.nextLine();
			}
			
			String sql = "SELECT courseID 课程号, Course.name 课程名称, staffID 任课教师号, hours 学时, credits 学分, classTime 上课时间, classLocate 上课地点, examDate 考试时间, studentID 学号, Student.name 姓名, Student.sex 性别, peacetime 平时成绩, exam 考试成绩, total 总评成绩 "
					+ "FROM Course, Grade, Student, Staff " + "WHERE courseID = Course.id AND studentID = Student.id AND StaffID = Staff.id AND " + column
					+ " = ? " + "ORDER BY studentID";
			
			return queryGrade(conn, sql, data);
		}
		else
		{
			System.out.println("请选择正确的方式！");
			return false;
		}

		String sql = "SELECT courseID 课程号, Course.name 课程名称, staffID 任课教师号, hours 学时, credits 学分, classTime 上课时间, classLocate 上课地点, examDate 考试时间, studentID 学号, Student.name 姓名, sex 性别, peacetime 平时成绩, exam 考试成绩, total 总评成绩 "
				+ "FROM Course, Grade, Student " + "WHERE courseID = Course.id AND studentID = Student.id AND " + column
				+ " = ? " + "ORDER BY studentID";
		
		return queryGrade(conn, sql, data);
	}

	/**
	 * 查询成绩的方法封装
	 * 
	 * @param conn 数据库连接
	 * @param sql  SQL语句
	 * @param id   查询使用的数据
	 * @return 查询是否成功
	 * @throws SQLException
	 */
	private static boolean queryGrade(Connection conn, String sql, String data) throws SQLException
	{
		List<Map<String, Object>> grades = qr.query(conn, sql, new MapListHandler(), data);

		if (grades.size() == 0)
		{
			System.out.println("查询成绩失败！请检查输入是否正确！");
			return false;
		} else
		{
			for (Map<String, Object> map : grades)
				System.out.println(map.toString().replaceAll("=", "：").replaceAll("\\{|\\}", ""));

			return true;
		}
	}
}

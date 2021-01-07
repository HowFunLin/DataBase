package manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

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
			if (tag == 3)
			{
				column = "Staff.name";

				System.out.print("输入要查询的教师姓名：");
				data = scan.nextLine();
			} else
			{
				column = "Staff.departmentID";

				System.out.print("输入要查询的系号：");
				data = scan.nextLine();
			}

			String sql = "SELECT courseID 课程号, Course.name 课程名称, staffID 任课教师号, hours 学时, credits 学分, classTime 上课时间, classLocate 上课地点, examDate 考试时间, studentID 学号, Student.name 姓名, Student.sex 性别, peacetime 平时成绩, exam 考试成绩, total 总评成绩 "
					+ "FROM Course, Grade, Student, Staff "
					+ "WHERE courseID = Course.id AND studentID = Student.id AND StaffID = Staff.id AND " + column
					+ " = ? " + "ORDER BY studentID";

			return queryGrade(conn, sql, data);
		} else
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
			if (tag == 3)
			{
				column = "Staff.name";

				System.out.print("输入要查询的教师姓名：");
				data = scan.nextLine();
			} else
			{
				column = "Staff.departmentID";

				System.out.print("输入要查询的系号：");
				data = scan.nextLine();
			}

			String sql = "SELECT courseID 课程号, Course.name 课程名称, staffID 任课教师号, hours 学时, credits 学分, classTime 上课时间, classLocate 上课地点, examDate 考试时间, studentID 学号, Student.name 姓名, Student.sex 性别, peacetime 平时成绩, exam 考试成绩, total 总评成绩 "
					+ "FROM Course, Grade, Student, Staff "
					+ "WHERE courseID = Course.id AND studentID = Student.id AND StaffID = Staff.id AND " + column
					+ " = ? " + "ORDER BY studentID";

			return queryGrade(conn, sql, data) && calculatePro(conn, data, column);
		} else
		{
			System.out.println("请选择正确的方式！");
			return false;
		}

		String sql = "SELECT courseID 课程号, Course.name 课程名称, staffID 任课教师号, hours 学时, credits 学分, classTime 上课时间, classLocate 上课地点, examDate 考试时间, studentID 学号, Student.name 姓名, sex 性别, peacetime 平时成绩, exam 考试成绩, total 总评成绩 "
				+ "FROM Course, Grade, Student " + "WHERE courseID = Course.id AND studentID = Student.id AND " + column
				+ " = ? " + "ORDER BY studentID";

		return queryGrade(conn, sql, data) && calculatePro(conn, data, column);
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

			System.out.println();
			return true;
		}
	}

	/**
	 * 计算聚集函数的结果
	 * 
	 * @param conn 数据库连接
	 * @param sql  SQL语句
	 * @param data 聚集函数查询条件
	 * @return 聚集函数查询的整型结果
	 * @throws SQLException
	 */
	private static double calculate(Connection conn, String sql, String data) throws SQLException
	{
		Long sumBig = qr.query(conn, sql, new ScalarHandler<>(), data);
		return Double.valueOf(sumBig);
	}
	
	/**
	 * 计算比例
	 * @param conn 数据库连接
	 * @param data 聚集函数查询条件
	 * @param column 聚集函数查询的列
	 * @return 计算是否成功
	 * @throws SQLException
	 */
	private static boolean calculatePro(Connection conn, String data, String column) throws SQLException
	{
		String sumSQL = "SELECT COUNT(*) " + "FROM Course, Grade, Student "
				+ "WHERE courseID = Course.id AND studentID = Student.id AND " + column + " = ? ";

		double[] sum = new double[6];
		
		sum[0] = calculate(conn, sumSQL, data); // 表中行的总数
		
		String failSQL = "SELECT COUNT(*) " + "FROM Course, Grade, Student "
				+ "WHERE courseID = Course.id AND studentID = Student.id AND " + column + " = ? AND total < 60";
		
		sum[1] = calculate(conn, failSQL, data);

		String sixtySQL = "SELECT COUNT(*) " + "FROM Course, Grade, Student "
				+ "WHERE courseID = Course.id AND studentID = Student.id AND " + column + " = ? AND total > 60";
		
		sum[2] = calculate(conn, sixtySQL, data);
		
		String seventySQL = "SELECT COUNT(*) " + "FROM Course, Grade, Student "
				+ "WHERE courseID = Course.id AND studentID = Student.id AND " + column + " = ? AND total > 70";
		
		sum[3] = calculate(conn, seventySQL, data);
		
		String eightySQL = "SELECT COUNT(*) " + "FROM Course, Grade, Student "
				+ "WHERE courseID = Course.id AND studentID = Student.id AND " + column + " = ? AND total > 80";
		
		sum[4] = calculate(conn, eightySQL, data);
		
		String ninetySQL = "SELECT COUNT(*) " + "FROM Course, Grade, Student "
				+ "WHERE courseID = Course.id AND studentID = Student.id AND " + column + " = ? AND total > 90";
		
		sum[5] = calculate(conn, ninetySQL, data);
		
		for(int i = 90, j = 5; i >= 60; i = i - 10, j--)
		{
			System.out.println(i + "分以上的学生人数为：" + (int)sum[j] + "\t所占比例为：" + ((sum[j] / sum[0]) * 100) + "%");
		}
		System.out.println("不及格的学生人数为：" + (int)sum[1] + "\t所占比例为：" + ((sum[1] / sum[0]) * 100) + "%");
		
		return true;
	}
}

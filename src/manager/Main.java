package manager;

import java.sql.Connection;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		Connection conn = Connections.getConnection();
		Scanner scan = new Scanner(System.in);
		
		main:while (true)
		{
			System.out.println("\n选择操作内容：");
			System.out.println("1-学生基本信息管理，2-系基本信息管理，3-课程信息管理，4-教职工信息管理");
			System.out.print("5-选课管理，6-成绩管理，7-信息查询，8-统计报表，9-退出 ");

			
			int tag = Integer.valueOf(scan.next());

			switch (tag)
			{
			case 1:
				System.out.print("\n选择进行的操作：1-录入新生信息，2-学生信息修改，3-学生转学或毕业 ");
				int select = scan.nextInt();

				switch (select)
				{
				case 1:
					System.out.println();
					StudentManage.newStudentRecord(conn);
					break;
				case 2:
					System.out.println();
					StudentManage.selectByIDandUpdate(conn);
					break;
				case 3:
					System.out.println();
					StudentManage.transferOrGraduate(conn);
					break;
				}

				break;

			case 2:
				System.out.print("\n选择进行的操作：1-输入，2-修改，3-删除 ");
				select = Integer.valueOf(scan.nextLine());

				switch (select)
				{
				case 1:
					System.out.println();
					DepartmentManage.insert(conn);
					break;
				case 2:
					System.out.println();
					DepartmentManage.update(conn);
					break;
				case 3:
					System.out.println();
					DepartmentManage.delete(conn);
					break;
				}

				break;
			case 3:
				System.out.print("\n选择进行的操作：1-输入，2-修改，3-删除 ");
				select = Integer.valueOf(scan.nextLine());

				switch (select)
				{
				case 1:
					System.out.println();
					CourseManage.insert(conn);
					break;
				case 2:
					System.out.println();
					CourseManage.update(conn);
					break;
				case 3:
					System.out.println();
					CourseManage.delete(conn);
					break;
				}

				break;
			case 4:
				System.out.print("\n选择进行的操作：1-输入，2-修改，3-删除 ");
				select = Integer.valueOf(scan.nextLine());

				switch (select)
				{
				case 1:
					System.out.println();
					StaffManage.insert(conn);
					break;
				case 2:
					System.out.println();
					StaffManage.update(conn);
					break;
				case 3:
					System.out.println();
					StaffManage.delete(conn);
					break;
				}

				break;
			case 5:
				System.out.print("\n选择进行的操作：1-选课，2-修改，3-删除 ");
				select = Integer.valueOf(scan.nextLine());

				switch (select)
				{
				case 1:
					System.out.println();
					SelectCourseManage.select(conn);
					break;
				case 2:
					System.out.println();
					SelectCourseManage.update(conn);
					break;
				case 3:
					System.out.println();
					SelectCourseManage.delete(conn);
					break;
				}

				break;
			case 6:
				System.out.print("\n选择进行的操作：1-输入，2-修改 ");
				select = Integer.valueOf(scan.nextLine());

				switch (select)
				{
				case 1:
					System.out.println();
					GradeManage.insert(conn);
					break;
				case 2:
					System.out.println();
					GradeManage.update(conn);
					break;
				}

				break;
			case 7:
				System.out.print("\n选择进行的操作：1-查询学生，2-查询教职工，3-查询系，4-查询课程，5-查询成绩 ");
				select = Integer.valueOf(scan.nextLine());

				switch (select)
				{
				case 1:
					System.out.println();
					InformationQuery.queryStudent(conn);
					break;
				case 2:
					System.out.println();
					InformationQuery.queryStaff(conn);
					break;
				case 3:
					System.out.println();
					InformationQuery.queryDepartment(conn);
					break;
				case 4:
					System.out.println();
					InformationQuery.queryCourse(conn);
					break;
				case 5:
					System.out.println();
					InformationQuery.queryGrade(conn);
					break;
				}

				break;
			case 8:
				System.out.print("\n选择进行的操作：1-输出成绩登记表，2-输出成绩报表 ");
				select = Integer.valueOf(scan.nextLine());

				switch (select)
				{
				case 1:
					System.out.println();
					Status.queryGradeForm(conn);
					break;
				case 2:
					System.out.println();
					Status.queryGradeReport(conn);
					break;
				}

				break;
			case 9:
				System.out.println("系统已退出！");
				break main;
			}
			
		}
		
		scan.close();
	}
}

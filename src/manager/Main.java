package manager;

import java.sql.Connection;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		Connection conn = Connections.getConnection();
		//StudentManage.newStudentRecord(conn);
		//StudentManage.selectByIDandUpdate(conn);
		//StudentManage.transferOrGraduate(conn);
		//DepartmentManage.insert(conn);
		//DepartmentManage.update(conn);
		//DepartmentManage.delete(conn);
		//StaffManage.insert(conn);
		//CourseManage.insert(conn);
		//SelectCourseManage.select(conn);
		//GradeManage.insert(conn);
		//GradeManage.update(conn);
		//InformationQuery.queryStudent(conn);
		//InformationQuery.queryStaff(conn);
		//InformationQuery.queryDepartment(conn);
		//InformationQuery.queryCourse(conn);
		//InformationQuery.queryGrade(conn);
		//Status.queryGradeForm(conn);
		Status.queryGradeReport(conn);
	}
}

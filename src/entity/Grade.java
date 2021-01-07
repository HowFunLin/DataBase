package entity;

public class Grade
{
	private String studentID;
	private String courseID;
	private int peacetime;
	private int exam;
	private int total;
	
	public Grade(String studentID, String courseID, int peacetime, int exam, int total)
	{
		super();
		this.studentID = studentID;
		this.courseID = courseID;
		this.peacetime = peacetime;
		this.exam = exam;
		this.total = total;
	}

	public String getStudentID()
	{
		return studentID;
	}

	public void setStudentID(String studentID)
	{
		this.studentID = studentID;
	}

	public String getCourseID()
	{
		return courseID;
	}

	public void setCourseID(String courseID)
	{
		this.courseID = courseID;
	}

	public int getPeacetime()
	{
		return peacetime;
	}

	public void setPeacetime(int peacetime)
	{
		this.peacetime = peacetime;
	}

	public int getExam()
	{
		return exam;
	}

	public void setExam(int exam)
	{
		this.exam = exam;
	}

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}
}

package entity;

public class Department
{
	private int id;
	private String name;
	private String intro;
	
	public Department(int departmentID, String name, String intro)
	{
		this.id = departmentID;
		this.name = name;
		this.intro = intro;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getIntro()
	{
		return intro;
	}

	public void setIntro(String intro)
	{
		this.intro = intro;
	}

	@Override
	public String toString()
	{
		return "系的基本信息：系号：" + id + " 系名：" + name + " 简介：" + intro;
	}

	public Department()
	{
		
	}
}

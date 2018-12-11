package ouc.javaweb.model;

public class Student {
	
	private int id;
	private String name;
	private int age;
	
	public Student() {
		
	}
	
	public Student(String name, int age) {
		this.name = name;
		this.setAge(age);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

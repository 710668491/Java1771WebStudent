package Student.entity;

import java.io.Serializable;

public class teacher implements Serializable {
	private Integer id;
	private String name;
	private Integer age;
	private String gender;
    private String subject;
    
	public teacher() {
		super();
	}

	public teacher(Integer id, String name, Integer age, String gender, String subject) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.subject = subject;
	}
	

	public teacher(String name, Integer age, String gender, String subject) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.subject = subject;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", subject=" + subject
				+ "]";
	}
    
    
}

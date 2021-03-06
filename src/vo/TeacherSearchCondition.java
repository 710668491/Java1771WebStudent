package vo;

public class TeacherSearchCondition {
	private String name;
	private String age;
	private String gender;
	
	
	
	
	public TeacherSearchCondition() {
		super();
	}
	public TeacherSearchCondition(String name, String age, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "StudentSearchCondition [name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}
	
	

}

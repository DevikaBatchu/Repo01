package rcp3.x;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;

public class Employee implements Serializable{
	
	private int eId;
	private String eName;
	private int age;
	private double salary;
	private String gender;
	
	public int flag;
	public int counter;
	
	
	
	public Employee parent;
	public List<Employee> child = new ArrayList<>();

	
	public Employee() {
		super();
	}

	public Employee(int counter, Employee parent) {
		
		this.parent = parent;
		this.counter = counter;
	}

	public Employee(int eId, String eName, int age, double salary, String gender) {
		super();
		this.eId = eId;
		this.eName = eName;
		this.age = age;
		this.salary = salary;
		this.gender = gender;
	}
	
	


	public Employee(int eId, String eName, int age, double salary) {
		super();
		this.eId = eId;
		this.eName = eName;
		this.age = age;
		this.salary = salary;
	}
	

	
	
	public int geteId() {
		return eId;
	}



	public void seteId(int eId) {
		this.eId = eId;
	}



	public String geteName() {
		return eName;
	}



	public void seteName(String eName) {
		this.eName = eName;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public double getSalary() {
		return salary;
	}



	public void setSalary(double salary) {
		this.salary = salary;
	}


	

	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	@Override
	public String toString() {
		return "Employee [eId=" + eId + ", eName=" + eName + ", age=" + age + ", salary=" + salary + ", gender="
				+ gender + "]";
	}


}

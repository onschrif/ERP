package tn.esprit.b3.esprit1718b3erp.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Employee
 *
 */
@Entity

public class Employee implements Serializable {

	@Id
	private String idEmployee;
	private int nic;
	private String firstName;
	private String lastName;
	private String gender;
	private int age;
	private String marital;
	private String nationality;
	private int rib;
	private String address;
	private int phone;
	private int mobile;
	private String email;
	private String department;
	private String jobPosition;
	private String manager;
	private float salary;
	private String password;
	@OneToMany(mappedBy="employee")
	private List<Leaves> leaves;
	//@OneToMany(mappedBy="employee")
	//private List<TimeSheet> timesheet;
	@OneToMany(mappedBy="employee")
	private List<PaymentSheet> paymentsheet;
	@OneToMany(mappedBy="emp")
	private List<Assets> assetss;
	@OneToMany(mappedBy="E")
	private List<Opportunity> lOpportunity;
	@OneToMany(mappedBy="emp")
	private List<Expense> exp;

	@OneToMany(mappedBy="pr_employee")
	private List<Production> production  ;
	
	private static final long serialVersionUID = 1L;

	public Employee() {
		super();
	}
	
	public String getIdEmployee() {
		return this.idEmployee;
	}
	public void setIdEmployee(String idEmployee) {
		this.idEmployee = idEmployee;
	}   
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}   
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}   
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}   
	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}   
	public String getMarital() {
		return this.marital;
	}

	public void setMarital(String marital) {
		this.marital = marital;
	}   
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}   
	public int getPhone() {
		return this.phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}   
	public int getMobile() {
		return this.mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getJobPosition() {
		return this.jobPosition;
	}

	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}   
	public float getSalary() {
		return this.salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}   
	public int getRib() {
		return this.rib;
	}

	public void setRib(int rib) {
		this.rib = rib;
	}   
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getNic() {
		return nic;
	}

	public void setNic(int nic) {
		this.nic = nic;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Leaves> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Leaves> leaves) {
		this.leaves = leaves;
	}



	public List<PaymentSheet> getPaymentsheet() {
		return paymentsheet;
	}

	public void setPaymentsheet(List<PaymentSheet> paymentsheet) {
		this.paymentsheet = paymentsheet;
	}
	
	@Override
	public String toString()
	{
		return this.firstName+" "+this.lastName;
	}
}

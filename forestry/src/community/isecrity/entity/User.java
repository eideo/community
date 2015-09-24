package community.isecrity.entity;


import java.util.Date;
import java.util.List;

public class User {
    private Integer id;

    private String account;

    private String password;

    private Integer state;

    private Date loginTime;
    
    private String name;
    
    private Integer age;
    
    private Integer gender;
    
    private String phone;
    
    private List<Role> list;

	public User() {
		super();
	}

	
	
	public User(Integer id, String account, String password, Integer state,
			Date loginTime, String name, Integer age, Integer gender,
			String phone, List<Role> list) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.state = state;
		this.loginTime = loginTime;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.list = list;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public List<Role> getList() {
		return list;
	}

	public void setList(List<Role> list) {
		this.list = list;
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



	public Integer getGender() {
		return gender;
	}



	public void setGender(Integer gender) {
		this.gender = gender;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password="
				+ password + ", state=" + state + ", loginTime=" + loginTime
				+ ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", phone=" + phone + ", list=" + list + "]";
	}



	
	
   
}
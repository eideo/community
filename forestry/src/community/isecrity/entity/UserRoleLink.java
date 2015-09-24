package community.isecrity.entity;

public class UserRoleLink {
	private Integer id;
	private User user;
	private Role role;
	
	public UserRoleLink() {
		super();
	}

	public UserRoleLink(Integer id, User user, Role role) {
		super();
		this.id = id;
		this.user = user;
		this.role = role;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRoleLink [id=" + id + ", user=" + user + ", role=" + role
				+ "]";
	}
}

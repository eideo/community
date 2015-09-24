package community.isecrity.entity;

public class RolePrivilegeLink {
	private Integer id;
	private Role role;
	private Privilege privilege;

	public RolePrivilegeLink() {
		super();
	}

	public RolePrivilegeLink(Integer id, Role role, Privilege privilege) {
		super();
		this.id = id;
		this.role = role;
		this.privilege = privilege;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	@Override
	public String toString() {
		return "RolePrivilegeLink [id=" + id + ", role=" + role
				+ ", privilege=" + privilege + "]";
	}
}

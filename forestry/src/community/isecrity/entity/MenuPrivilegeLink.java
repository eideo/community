package community.isecrity.entity;
public class MenuPrivilegeLink {
	private Integer id;
	private Menu menu;
	private Privilege privilege;
	
	public MenuPrivilegeLink(Integer id, Menu menu, Privilege privilege) {
		super();
		this.id = id;
		this.menu = menu;
		this.privilege = privilege;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	@Override
	public String toString() {
		return "MenuPrivilegeLink [id=" + id + ", menu=" + menu
				+ ", privilege=" + privilege + "]";
	}
}

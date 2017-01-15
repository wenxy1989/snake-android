package android.node.model;

/**
 * 多终端同步，以最近时间的提交为准，同步时，应该先更新在上传
 * @author Administrator
 *
 */
public class Node {
	
	private int id;
	
	private String name;
	
	private String explain;
	
	private int from;
	
	private String user;

	private String create;
	
	private String update;
	
	/**
	 * 所属用户
	 * @return
	 */
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * node id
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 标题，同一个from中唯一，不可为空
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 记录的详细描述，可以为空
	 * @return
	 */
	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	/**
	 * 所属目录
	 * @return
	 */
	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	/**
	 * 创建时间，类型string格式yyyy-MM-dd hh:mm:ss
	 * @return
	 */
	public String getCreate() {
		return create;
	}

	public void setCreate(String create) {
		this.create = create;
	}

	/**
	 * 更新时间，其余同创建时间
	 * @return
	 */
	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

}

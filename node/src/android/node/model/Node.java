package android.node.model;

/**
 * ���ն�ͬ���������ʱ����ύΪ׼��ͬ��ʱ��Ӧ���ȸ������ϴ�
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
	 * �����û�
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
	 * ���⣬ͬһ��from��Ψһ������Ϊ��
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ��¼����ϸ����������Ϊ��
	 * @return
	 */
	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	/**
	 * ����Ŀ¼
	 * @return
	 */
	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	/**
	 * ����ʱ�䣬����string��ʽyyyy-MM-dd hh:mm:ss
	 * @return
	 */
	public String getCreate() {
		return create;
	}

	public void setCreate(String create) {
		this.create = create;
	}

	/**
	 * ����ʱ�䣬����ͬ����ʱ��
	 * @return
	 */
	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

}

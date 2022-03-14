package org.test.hotelsystp.repository;

public enum TableEnum {
	GUEST("GUEST", 1), RESERVATION("RESERVATION", 2), ROOM("ROOM", 3);

	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private TableEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(int index) {
		for (TableEnum tableEnum : TableEnum.values()) {
			if (tableEnum.getIndex() == index) {
				return tableEnum.name;
			}
		}
		return null;
	}

	// get set 方法
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
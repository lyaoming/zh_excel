package com.framework.utils;

/**
 * 常量
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年11月15日 下午1:23:52
 */
public class Constant {
	/**
	 * 菜单类型
	 * @author R & D
	 * @email 908350381@qq.com
	 * @date 2016年11月15日 下午1:24:29
	 */
	public enum MenuType {
		/**
		 * 目录
		 */
		CATALOG(0),
		/**
		 * 菜单
		 */
		MENU(1),
		/**
		 * 按钮
		 */
		BUTTON(2);

		private int value;

		private MenuType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/**
	 * 定时任务状态
	 * @author R & D
	 * @email 908350381@qq.com
	 * @date 2016年12月3日 上午12:07:22
	 */
	public enum ScheduleStatus {
		/**
		 * 正常
		 */
		NORMAL(0),
		/**
		 * 暂停
		 */
		PAUSE(1);

		private int value;

		private ScheduleStatus(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}

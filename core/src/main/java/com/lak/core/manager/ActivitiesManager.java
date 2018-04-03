package com.lak.core.manager;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.LinkedList;
import java.util.List;

public class ActivitiesManager {
	private List<Activity> mList = null;
	private Activity mRunningActivity = null;

	private ActivitiesManager() {
		mList = new LinkedList<Activity>();
	}

	private static class HolderManager {
		private static ActivitiesManager INSTANCE = new ActivitiesManager();
	}

	public static ActivitiesManager instance() {
		return HolderManager.INSTANCE;
	}
	
	// ---------------======================= fuction ====================--------------
	/**
	 * 添加一个Activity到队列中
	 * @param activity
	 * @return true-成功。false-失败
	 */
	public boolean addActivity(Activity activity) {
        return mList.add(activity);     
    }
	
	/**
	 * 从队列中移除一个Activity
	 * @param activity
	 * @return true-成功。false-失败
	 */
	public boolean removeActivity(Activity activity) {
		return mList.remove(activity);
	}

	/**
	 * 弹出clazzName上面的所有页面
	 * */
	public boolean popToActivity(@NonNull String clazzName) {
		if (mList != null) {
			while (mList.size() > 0) {
				Activity page = mList.get(mList.size() - 1);
				if (page != null) {
					boolean result = clazzName.equals(page.getClass().getSimpleName());
					if (result) { return true; }
					removeActivity(page);
					page.finish(); // 会导致页面的finish时调用removeActivity失败，但不影响结果
				}
			}
		}
		return false;
	}

	public boolean hasActivity(String clazzName) {
		boolean result = false;
		if (!TextUtils.isEmpty(clazzName)
				&& mList != null && mList.size() > 0) {
			for (int i = 0; i < mList.size(); i++) {
				Activity activity = mList.get(i);
				if (activity != null)
					result = clazzName.equals(activity.getClass().getSimpleName());
				if (result) { break; }
			}
		}
		return result;
	}

	/**
	 * 强制关闭堆栈顶部指定个数页面
	 * */
	public boolean finishSize(int size) {
		if (mList != null) {
			int minSize = size;
			if (mList.size() < size) { minSize = mList.size(); }
			for (int i = 0; i < minSize; i++) {
				Activity page = mList.get(mList.size() - 1);
				if (page != null) {
					removeActivity(page);
					page.finish(); // 会导致页面的finish时调用removeActivity失败，但不影响结果
				}
			}
		}
		return false;
	}

	/**
	 * 关闭所有Activity，并调用mList.clear(); 
	 */
	public void clear() {
		if (mList != null) {
			for (Activity activity : mList) {
				if (activity != null)
					activity.finish();
			}
			mList.clear();
		}
	}
	
	/**
	 * 返回当前正在运行的Ativity，有可能为null
	 * @return
	 */
	public @Nullable Activity getRunningActivity() {
		return mRunningActivity;
	}
	
	public void setRunningActivity(Activity mRunningActivity) {
		this.mRunningActivity = mRunningActivity;
	}
	
}

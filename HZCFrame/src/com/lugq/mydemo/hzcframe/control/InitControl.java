package com.lugq.mydemo.hzcframe.control;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Intent.ShortcutIconResource;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.widget.CheckBox;

import com.lugq.mydemo.hzcframe.R;
import com.lugq.mydemo.hzcframe.activity.StartUp;
import com.lugq.mydemo.hzcframe.task.CheckConnectionTask;
import com.lugq.mydemo.hzcframe.taskbasic.TaskPoolService;
import com.lugq.mydemo.hzcframe.util.ActionUtil;

/**
 * initialize operation--StartUp class.
 * @ClassName: InitControl
 * @author lugq
 * @date 2014年9月9日 下午3:42:29
 *
 */
public class InitControl {
	
	private Context mContext;
	private Handler mHandler;
	
	public InitControl(Context context, Handler handler) {
		this.mContext = context;
		this.mHandler = handler;
	}
	
	/**
	 * create shortcut.
	 */
	public void createShortCut() {
		SharedPreferences preferences = ActionUtil.getSharedPreferences(mContext);
		Editor databaseData = ActionUtil.getEditor(mContext);
		boolean isCreateShortCut = preferences.getBoolean("create_shortcut", true);
		if (isCreateShortCut) {
			boolean flag = isAddShortCut();
			if (flag == false) {
				addShortCut();
				databaseData.putBoolean("create_shortcut", false);
				databaseData.commit();
			}
		}
	}
	
	/**
	 * Check the connections.
	 */
	public void checkConnection() {
		TaskPoolService.getInstance().requestService(new CheckConnectionTask(mContext, mHandler));
	}
	
	/**
	 * Determine whether there is a shortcut to the desktop.
	 * @return
	 */
	private boolean isAddShortCut() {
        boolean isInstallShortcut = false;
        final ContentResolver cr = mContext.getContentResolver();
        // 2.2系统是”com.android.launcher2.settings”,网上见其他的为"com.android.launcher.settings"
        final String AUTHORITY = "com.android.launcher.settings";
        final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/favorites?notify=true");
        Cursor c =
            cr.query(CONTENT_URI, new String[] {"title", "iconResource"}, "title=?",
                     new String[] {mContext.getString(R.string.app_name)}, null);// XXX表示应用名称。
        if (c != null && c.getCount() > 0) {
            isInstallShortcut = true;
        }
        return isInstallShortcut;
	}
	
	/**
	 * Create a shortcut to perform specific.
	 */
	private void addShortCut() {
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        // 设置属性
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, mContext.getResources().getString(R.string.app_name));
        ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(mContext, R.drawable.ic_launcher);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);

        // 是否允许重复创建
        shortcut.putExtra("duplicate", false);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(mContext, StartUp.class);
        // 设置启动程序
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        mContext.sendBroadcast(shortcut);
	}
}































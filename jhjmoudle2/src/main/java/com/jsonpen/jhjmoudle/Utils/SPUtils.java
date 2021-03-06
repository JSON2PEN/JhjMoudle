package com.jsonpen.jhjmoudle.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * sp工具类
 *
 */
public class SPUtils {
	private static SPUtils instance = null;

	public static SPUtils getInstance() {
		if (instance == null) {
			synchronized (SPUtils.class) {
				if (instance == null) {
					instance = new SPUtils();
				}
			}
		}
		return instance;
	}

	private SPUtils() {
	}

	/**
	 * 本地k-v存储
	 */
	public SharedPreferences getPrefs(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context); // 本地k-v存储
	}

	public int getIntFromPrefs(Context context, String key, int def) {
		if (key == null) {
			return def;
		}
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(context);
		return pref.getInt(key, def);
	}

	public long getLongFromPrefs(Context context, String key, long def) {
		if (key == null) {
			return def;
		}
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(context);
		return pref.getLong(key, def);
	}

	public boolean getBooleanFromPrefs(Context context, String key, boolean def) {
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(context);
		return pref.getBoolean(key, def);
	}

	public String getStringFromPrefs(Context context, String key, String def) {
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(context);
		return pref.getString(key, def);
	}

	public void setIntToPrefs(Context context, String key, int value) {// K-V存储
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = pref.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public void setLongToPrefs(Context context, String key, long value) {// K-V存储
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = pref.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	public void setStringToPrefs(Context context, String key, String value) {// K-V存储
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = pref.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public void setBooleanToPrefs(Context context, String key, // K-V存储
			boolean value) {
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = pref.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

}

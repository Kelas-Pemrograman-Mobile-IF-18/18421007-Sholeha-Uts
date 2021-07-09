package com.sholeha.aplikasipenjualanhelm.session;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.sholeha.aplikasipenjualanhelm.MainActivity;
import com.sholeha.aplikasipenjualanhelm.admin.HomeAdminActivity;
import com.sholeha.aplikasipenjualanhelm.users.HomeUserActivity;

public class PrefSetting {

    public static String _id;
    public static String Username;
    public static String NoTelpon;
    public static String Alamat;
    public static String role;
    Activity activity;

    public PrefSetting(Activity activity){
        this.activity = activity;
    }

    public SharedPreferences getSharePreferances(){
        SharedPreferences preferences = activity.getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        return preferences;
    }

    public void isLogin(SessionManager session, SharedPreferences pref){
        session = new SessionManager(activity);
        if(session.isLoggedIn()){
            pref = getSharePreferances();
            _id  = pref.getString("_id", "");
            Username = pref.getString("Username", "");
            NoTelpon = pref.getString("NoTelpon", "");
            Alamat = pref.getString("Alamat", "");
            role = pref.getString("role", "");
        }else {
            session.setLogin(false);
            session.setSessid(0);
            Intent i = new Intent(activity, activity.getClass());
            activity.startActivity(i);
            activity.finish();
        }
    }

    public void checkLogin(SessionManager session, SharedPreferences pref){
        session = new SessionManager(activity);
        _id  = pref.getString("_id", "");
        Username = pref.getString("Username", "");
        NoTelpon = pref.getString("NoTelpon", "");
        Alamat = pref.getString("Alamat", "");
        role = pref.getString("role", "");
        if(session.isLoggedIn()){
            Log.e("role", String.valueOf(role));
            if (role.equals("1")){
                Intent i = new Intent(activity, HomeAdminActivity.class);
                activity.startActivity(i);
                activity.finish();
            }else {
                Intent i = new Intent(activity, HomeUserActivity.class);
                activity.startActivity(i);
                activity.finish();
            }
        }
    }


    public void storeRegIdSharedPreferences(Context context, String _id, String Username, String NoTelpon, String role, String Alamat, SharedPreferences prefs){

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("_id", _id);
        editor.putString("Username", Username);
        editor.putString("NoTelpon", NoTelpon);
        editor.putString("Alamat", Alamat);
        editor.putString("role", role);
        editor.commit();

    }
}
package com.moutamid.trackercarrentalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import androidx.appcompat.app.AlertDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utils {

    private static final String PACKAGE_NAME = "dev.moutamid.trackercarrentalproject";

    private SharedPreferences sharedPreferences;

    public void removeSharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }

    public String getStoredString(Context context, String name) {
        sharedPreferences = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(name, "Error");
    }

    public void storeString(Context context, String name, String object) {
        sharedPreferences = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(name, object).apply();
    }

    public void storeBoolean(Context context1, String name, boolean value) {
        sharedPreferences = context1.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(name, value).apply();
    }

    public boolean getStoredBoolean(Context context1, String name) {
        sharedPreferences = context1.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(name, false);
    }

    public void storeInteger(Context context1, String name, int value) {
        sharedPreferences = context1.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(name, value).apply();
    }

    public int getStoredInteger(Context context1, String name) {
        sharedPreferences = context1.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(name, 0);
    }

    public void storeFloat(Context context1, String name, float value) {
        sharedPreferences = context1.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putFloat(name, value).apply();
    }

    public float getStoredFloat(Context context1, String name) {
        sharedPreferences = context1.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(name, 0);
    }

    //    public void storeArrayList(Context context, String name, ArrayList<String> arrayList) {
//        sharedPreferences = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor edit = sharedPreferences.edit();
//        Set<String> set = new HashSet<>(arrayList);
//        edit.putStringSet(name, set);
//        edit.apply();
//    }
//
//    public ArrayList<String> getStoredArrayList(Context context, String name) {
//        sharedPreferences = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
//        Set<String> defaultSet = new HashSet<>();
//        defaultSet.add("Error");
//        Set<String> set = sharedPreferences.getStringSet(name, defaultSet);
//        return new ArrayList<>(set);
//    }

    public String getRandomNmbr(int length) {
        return String.valueOf(new Random().nextInt(length) + 1);
    }

//    public void showOfflineDialog(Context context, String title, String desc) {
//

    /*
		<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="300dp"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    android:background="@drawable/bg_dialog"
    android:orientation="vertical">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:visibility="invisible"
        android:layout_gravity="end"
        android:layout_marginEnd="10dp"
        android:layout_marginRight="10dp"
        android:layout_marginTop="7dp"
        android:text="x"
        android:textColor="@color/white"
        android:textSize="20sp"
        android:textStyle="bold" />

    <ImageView
        android:layout_width="80dp"
        android:layout_height="80dp"
        android:layout_gravity="center"
        android:src="@drawable/ic_info" />

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_marginEnd="12dp"
        android:layout_marginLeft="12dp"
        android:layout_marginRight="12dp"
        android:id="@+id/title_offline_dialog"
        android:layout_marginStart="12dp"
        android:gravity="center"
        android:maxLines="1"
        android:text="Awww... Snap!"
        android:textColor="@color/darkBlue"
        android:textSize="18sp"
        android:layout_marginTop="5dp"
        android:textStyle="bold" />

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_marginEnd="12dp"
        android:layout_marginLeft="12dp"
        android:layout_marginRight="12dp"
        android:layout_marginStart="12dp"
        android:layout_marginTop="10dp"
        android:gravity="center"
        android:text="You are not connected to Internet. Please make sure you have a working connection"
        android:textColor="@color/greyishblue"
        android:id="@+id/desc_offline_dialog"
        android:textSize="15sp" />

    <Button
        android:id="@+id/okay_btn_offline_dialog"
        android:layout_width="160dp"
        android:layout_height="45dp"
        android:layout_gravity="center"
        android:layout_marginBottom="15dp"
        android:layout_marginTop="20dp"
        android:background="@drawable/bg_dialog_offline_button"
        android:gravity="center"
        android:text="Okay"
        android:textColor="@color/white" />

</LinearLayout>
		*/

//        Button okayBtn;
//
//        final Dialog dialogOffline = new Dialog(context);
//        dialogOffline.setContentView(R.layout.dialog_offline);
//
//        okayBtn = dialogOffline.findViewById(R.id.okay_btn_offline_dialog);
//        TextView titleTv = dialogOffline.findViewById(R.id.title_offline_dialog);
//        TextView descTv = dialogOffline.findViewById(R.id.desc_offline_dialog);
//
//        if (!TextUtils.isEmpty(title))
//            titleTv.setText(title);
//
//        if (!TextUtils.isEmpty(desc))
//            descTv.setText(desc);
//
//        okayBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialogOffline.dismiss();
//            }
//        });
//
//        dialogOffline.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialogOffline.show();
//
//    }
//
//    public void showWorkDoneDialog(Context context, String title, String desc) {
//

    /*
    * <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="300dp"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    android:background="@drawable/bg_dialog"
    android:orientation="vertical">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="end"
        android:layout_marginEnd="10dp"
        android:layout_marginRight="10dp"
        android:visibility="invisible"
        android:layout_marginTop="7dp"
        android:text="x"
        android:textColor="@color/black"
        android:textSize="20sp"
        android:textStyle="bold" />

    <ImageView
        android:layout_width="80dp"
        android:layout_height="80dp"
        android:layout_gravity="center"
        android:src="@drawable/ic_done" />

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_marginEnd="12dp"
        android:layout_marginLeft="12dp"
        android:layout_marginRight="12dp"
        android:layout_marginStart="12dp"
        android:gravity="center"
        android:maxLines="1"
        android:text="Email sent"
        android:id="@+id/title_work_done_dialog"
        android:textColor="@color/darkBlue"
        android:textSize="18sp"
        android:layout_marginTop="5dp"
        android:textStyle="bold" />

    <TextView
        android:id="@+id/desc_work_done_dialog"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_marginEnd="12dp"
        android:layout_marginLeft="12dp"
        android:layout_marginRight="12dp"
        android:layout_marginStart="12dp"
        android:layout_marginTop="10dp"
        android:gravity="center"
        android:text="Open your email account and follow the instructions given through the link."
        android:textColor="@color/greyishblue"
        android:textSize="15sp" />

    <Button
        android:id="@+id/okay_btn_work_done_dialog"
        android:layout_width="160dp"
        android:layout_height="45dp"
        android:layout_gravity="center"
        android:layout_marginBottom="15dp"
        android:layout_marginTop="20dp"
        android:background="@drawable/bg_dialog_work_done_button"
        android:gravity="center"
        android:text="Okay"
        android:textColor="@color/white" />

</LinearLayout>
    * */

//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(R.layout.dialog_work_done);
//
//        Button okayBtn = dialog.findViewById(R.id.okay_btn_work_done_dialog);
//        TextView titleTv = dialog.findViewById(R.id.title_work_done_dialog);
//        TextView descTv = dialog.findViewById(R.id.desc_work_done_dialog);
//
//        if (!TextUtils.isEmpty(title))
//            titleTv.setText(title);
//
//        if (!TextUtils.isEmpty(desc))
//            descTv.setText(desc);
//
//        okayBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.show();
//
//    }

    public void showDialog(Context context, String title, String message, String positiveBtnName, String negativeBtnName, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener, boolean cancellable) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveBtnName, positiveListener)
                .setNegativeButton(negativeBtnName, negativeListener)
                .setCancelable(cancellable)
                .show();
    }

//    public void saveArrayList(ArrayList<String> list, String key){
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
    //      SharedPreferences.Editor editor = prefs.edit();
    //    Gson gson = new Gson();
    //  String json = gson.toJson(list);
    // editor.putString(key, json);
    //editor.apply();     // This line is IMPORTANT !!!
    // }

    //public ArrayList<String> getArrayList(String key){
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
    //      Gson gson = new Gson();
    //    String json = prefs.getString(key, null);
    //  Type type = new TypeToken<ArrayList<String>>() {}.getType();
    //return gson.fromJson(json, type);
    // }

    public String getDate() {

        try {

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            return sdf.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Error";

    }


//    public String getDate(Context context) {
//
//        try {
//
//            Date date = SecureTimer.with(context).getCurrentDate();
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//            return sdf.format(date);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "Error";
//
//    }
//
//    public String getNextDate(Context context) {
//
//        try {
//            Date date = SecureTimer.with(context).getCurrentDate();
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//
//            Calendar c = Calendar.getInstance();
//
//            c.setTime(sdf.parse(sdf.format(date)));
//            c.add(Calendar.DATE, 1);
//            return sdf.format(c.getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return "Error";
//    }
//
//    public String getPreviousDate(Context context) {
//
//        try {
//            Date date = SecureTimer.with(context).getCurrentDate();
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//
//            Calendar c = Calendar.getInstance();
//
//            c.setTime(sdf.parse(sdf.format(date)));
//            c.add(Calendar.DATE, -1);
//            return sdf.format(c.getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return "Error";
//    }

//    public void showSnackBar(View view, String msg) {
//        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
//    }
/*public void changeStatusBarColor(Activity activity, int id) {

    // Changing the color of status bar
    if (Build.VERSION.SDK_INT >= 21) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(activity.getResources().getColor(id));
    }

    // CHANGE STATUS BAR TO TRANSPARENT
    //window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
}

    // PUBLIC METHOD TO GET VIEW FROM ONE ACTIVITY OR FRAGMENT TO ANOTHER
    //-------------------------------------------------------------------
    //public Utils.NonSwipableViewPager getClassRoomViewPager() {
//
    //      // Class to set current item or change any view from any different activity
//
    //      if (null == classRoomViewPager) {
    //        classRoomViewPager = (Utils.NonSwipableViewPager) findViewById(R.id.class_room_viewPager);
    //  }
//
    //      return classRoomViewPager;
    //}*/
}

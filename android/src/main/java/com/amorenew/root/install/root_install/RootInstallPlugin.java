package com.amorenew.root.install.root_install;

import android.app.Activity;
import android.content.Context;
import android.os.PowerManager;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * RootInstallPlugin
 */
public class RootInstallPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {

    //private Context applicationContext;
    private Activity activity;
    private Result result;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        onAttachedToEngine(flutterPluginBinding.getApplicationContext(), flutterPluginBinding.getBinaryMessenger(), null);
    }

    public static void registerWith(Registrar registrar) {
        final RootInstallPlugin instance = new RootInstallPlugin();
        instance.onAttachedToEngine(registrar.context(), registrar.messenger(), registrar.activity());
    }

    private void onAttachedToEngine(Context applicationContext, BinaryMessenger messenger, Activity _activity) {
        //this.applicationContext = applicationContext;
        if (_activity != null)
            this.activity = _activity;
        final MethodChannel channel = new MethodChannel(messenger, "root_install");
        channel.setMethodCallHandler(new RootInstallPlugin());
    }


    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        this.result = result;
        final String method_installApk = "installApk";

        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else if (call.method.equals(method_installApk)) {
            String apkPath = call.argument("apkPath");
            install(apkPath);
        } else {
            result.notImplemented();
        }
    }

    void install(String apkPath) {
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"su", "-c", "pm install -r " + apkPath + "; reboot;"});
            process.waitFor();
            PowerManager pm = (PowerManager) activity.getSystemService(Context.POWER_SERVICE);
            pm.reboot(null);
            result.success(true);
        } catch (Exception e) {
            System.out.println(e.toString());
            result.success(false);
            //Toast.makeText(applicationContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    }

    @Override
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        activity = activityPluginBinding.getActivity();

    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {

    }

    @Override
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {

    }

    @Override
    public void onDetachedFromActivity() {

    }
}

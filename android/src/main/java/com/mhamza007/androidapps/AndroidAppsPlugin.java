package com.mhamza007.androidapps;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.util.ArrayList;
import java.util.List;

@CapacitorPlugin(name = "AndroidApps")
public class AndroidAppsPlugin extends Plugin {

    private AndroidApps implementation = new AndroidApps();
    JSObject apps = new JSObject();

    @PluginMethod
    public void echo(PluginCall call) {
        LoadApps loadApps = new LoadApps(call);
        loadApps.execute(PackageManager.GET_META_DATA);
    }

    class LoadApps extends AsyncTask<Integer, Integer, List<String>> {

        PluginCall pluginCall;

        public LoadApps(PluginCall pluginCall) {
            this.pluginCall = pluginCall;
        }

        @Override
        protected List<String> doInBackground(Integer... integers) {
            ArrayList<String> apps = new ArrayList<>();
            PackageManager packageManager = getActivity().getPackageManager();

            List<ApplicationInfo> infos = packageManager.getInstalledApplications(integers[0]);
            for (ApplicationInfo info : infos) {
                if ((info.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                    continue;
                }

//                AppInfo appInfo = new AppInfo();
//                appInfo.info = info;
//                appInfo.setLabel((String) info.loadLabel(packageManager));
                apps.add((String) info.loadLabel(packageManager));
                // apps.add((String) info.loadLabel(packageManager));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    apps.sort((o1, o2) -> o2.compareTo(o1));
                }
            }

            return apps;
        }

        @Override
        protected void onPostExecute(List<String> appInfos) {
            super.onPostExecute(appInfos);

            apps.put("value", implementation.echo(appInfos));
            pluginCall.resolve(apps);
        }
    }
}

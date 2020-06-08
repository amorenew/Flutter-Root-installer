# root_install
A Flutter plugin for installing any apy on a rooted Android device.

## Installation 
#### Link on Flutter plugins
https://pub.dev/packages/root_install
## Usage

##### Add Permissions

```
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />
```



##### Install local Android APk file

```bool isInstalled = await RootInstall.installApk('apkLocalPath');```
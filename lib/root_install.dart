import 'dart:async';

import 'package:flutter/services.dart';

class RootInstall {
  static const MethodChannel _channel = const MethodChannel('root_install');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<bool> installApk(String apkPath) async {
    try {
      final bool result =
          await _channel.invokeMethod('installApk', {'apkPath': apkPath});
      return result;
    } on PlatformException catch (e) {
      print(e.toString());
      return false;
    }
  }
}

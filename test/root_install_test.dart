import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:root_install/root_install.dart';

void main() {
  const MethodChannel channel = MethodChannel('root_install');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await RootInstall.platformVersion, '42');
  });
}

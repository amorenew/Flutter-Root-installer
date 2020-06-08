#import "RootInstallPlugin.h"
#if __has_include(<root_install/root_install-Swift.h>)
#import <root_install/root_install-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "root_install-Swift.h"
#endif

@implementation RootInstallPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftRootInstallPlugin registerWithRegistrar:registrar];
}
@end

#import <Cordova/CDVPlugin.h>
#import <Cordova/CDVPluginResult.h>
#import "KPDFViewer.h"
@import PDFViewerSDK;

@implementation KPDFViewer

- (void)open:(CDVInvokedUrlCommand*)command {
    __block CDVPluginResult* pluginResult = nil;

    NSDictionary *options = [command.arguments objectAtIndex:0];
    NSString *fileName = [options objectForKey:@"fileName"];
    NSString *bookmarkMessage = [options objectForKey:@"bookmarkMessage"];
    NSString *title = [options objectForKey:@"title"];
    NSInteger currentPage = [[options objectForKey:@"currentPage"] integerValue];

    // Create instance of PDFViewer
    PDFViewer *pdfViewer = [[PDFViewer alloc] init];
    // Set required values. If not set the default value is taken.
    pdfViewer.popUpTitle = @"My Own title";
    pdfViewer.pageBookMarkedMessage = bookmarkMessage;
    pdfViewer.topBarTitleText = title;
    pdfViewer.topBarBackgroundColor = [UIColor colorWithRed:55.0f/255.0f green:70.0f/255.0f blue:79.0f/255.0f alpha:1.0f];
    pdfViewer.topBarTextColor = UIColor.whiteColor;
    pdfViewer.currentPageIndex = currentPage;

    [pdfViewer loadLoacalPDFFromController:self.viewController fileName:fileName completionHandler:^(NSInteger bookMarkedIndex) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsInt:(int)bookMarkedIndex];
        [pluginResult setKeepCallbackAsBool:TRUE];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

@end

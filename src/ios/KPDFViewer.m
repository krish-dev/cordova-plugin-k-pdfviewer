#import <Cordova/CDVPlugin.h>
#import <Cordova/CDVPluginResult.h>
#import "KPDFViewer.h"
@import PDFViewerSDK;

@implementation KPDFViewer

- (void)open:(CDVInvokedUrlCommand*)command {
    NSDictionary *options = [command.arguments objectAtIndex:0];
    NSString *fileName = [options objectForKey:@"fileName"];
    NSLog(@"%@",fileName);

    PDFViewer *pdfViewer = [[PDFViewer alloc] init];
    [pdfViewer presentFromController:self.viewController pdfFilePath:@"pdf" currentPage:1 screenTitle:@"PDF VIEWER" popupMessage:@"Page book marked successfully" topbarColor:UIColor.cyanColor completionHandler:^(NSInteger pageNum) {
        NSLog(@"Page num in Target: %d", (int)pageNum);
    }];
}

@end

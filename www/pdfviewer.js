var exec = require('cordova/exec');
/**
 * @name KPDFViewer
 * @author Krishnendu Sekhar Das
 */
function KPDFViewer() { }

KPDFViewer.prototype.open = function (config, successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'KPDFViewer', 'open', [config]);
};

module.exports = new KPDFViewer();

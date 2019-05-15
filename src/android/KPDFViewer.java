package in.co.indusnet.cordova.plugins.pdfviewer;

import android.util.Log;
import com.github.firstplugin.PDFView;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class KPDFViewer extends CordovaPlugin implements PDFView.OnBookMarkListener {

    CallbackContext cordovaCallbackContext;

    @Override
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {

        if (action.equals("open")) {
            cordova.getActivity().runOnUiThread(() -> {
                cordovaCallbackContext = callbackContext;
                JSONObject options;
                try {
                    options = args.getJSONObject(0);
                    String pdfFileName = options.getString("fileName");
                    String title = options.getString("title");
                    int currentPage = options.getInt("currentPage");
                    openViewer(title, pdfFileName, currentPage);
                }catch (JSONException e){
                    cordovaCallbackContext.error(e.getMessage());
                }

            });

            return true;
        }

        return false;
    }

    private void openViewer(String title, String fileName, int currentPage) {
        PDFView.with(cordova.getActivity(), this)
                .fromFile(cordova.getContext(), fileName)
                .landingPage(currentPage)
                .toolBarColor("#37464F")
                .toolBarTextColor("#FFFFFF")
                .toolBarTitle(title)
                .start();
    }

    @Override
    public void onClick(int i) {
        PluginResult resultA = new PluginResult(PluginResult.Status.OK, i);
        resultA.setKeepCallback(true);
        cordovaCallbackContext.sendPluginResult(resultA);
    }
}

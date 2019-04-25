package in.co.indusnet.cordova.plugins.pdfviewer;

import android.util.Log;
import com.github.firstplugin.PDFView;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class KPDFViewer extends CordovaPlugin {

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
                    openViewer(pdfFileName);
                    Log.d("File Name:: ", pdfFileName);
                }catch (JSONException e){
                    cordovaCallbackContext.error(e.getMessage());
                }

            });

            return true;
        }

        return false;
    }

    private void openViewer(String fileName) {
        PDFView.with(cordova.getActivity())
                .fromFile(cordova.getContext(), fileName)
                .toolBarColor("#008577")
                .toolBarTextColor("#FFFFFF")
                .toolBarTitle("PDF View")
                .start();
    }
}

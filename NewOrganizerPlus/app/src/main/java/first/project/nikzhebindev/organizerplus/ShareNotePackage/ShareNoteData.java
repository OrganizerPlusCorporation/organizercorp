package first.project.nikzhebindev.organizerplus.ShareNotePackage;

import android.app.Activity;

public class ShareNoteData {
    public ShareNoteData(Activity activity, String message) {
        this.activity = activity;
        this.message = message;
    }


    private Activity activity;
    private String message;


    public Activity getActivity() {
        return activity;
    }

    public String getMessage() {
        return message;
    }
}
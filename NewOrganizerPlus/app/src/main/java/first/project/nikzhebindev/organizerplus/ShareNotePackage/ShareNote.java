package first.project.nikzhebindev.organizerplus.ShareNotePackage;

import android.content.Intent;

public abstract class ShareNote {
    public ShareNote(ShareNoteData data) {
        shareNoteData = data;
    }

    public abstract void share();
    public void onActivityResult(int requestCode, int resultCode, Intent data) {}

    protected ShareNoteData shareNoteData;
}
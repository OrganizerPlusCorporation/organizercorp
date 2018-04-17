package first.project.nikzhebindev.organizerplus.ShareNotePackage.ShareNoteTwitter;

import android.content.Intent;
import android.util.Log;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;

import first.project.nikzhebindev.organizerplus.ShareNotePackage.ShareNote;
import first.project.nikzhebindev.organizerplus.ShareNotePackage.ShareNoteData;
import first.project.nikzhebindev.organizerplus.ShareNotePackage.ShareNoteException;

public class ShareNoteTwitter extends ShareNote {
    public ShareNoteTwitter(ShareNoteData data) {
        super(data);

        Twitter.initialize(data.getActivity());
    }

    public void share() {
        if (TwitterCore.getInstance().getSessionManager().getActiveSession() == null) {
            client = new TwitterAuthClient();
            client.authorize(shareNoteData.getActivity(), new Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> twitterSessionResult) {
                    tweet();
                }

                @Override
                public void failure(TwitterException e) {
                    Log.w("Twitter", "Can't post tweet");
                }
            });
        }
        else {
            tweet();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        client.onActivityResult(requestCode, resultCode, data);
    }

    private void tweet() {
        final TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();

        if (session == null) {
            Log.e("Twitter", "Can't create session");
            return;
        }

        final Intent intent = new ComposerActivity.Builder(shareNoteData.getActivity())
                .session(session)
                .text(shareNoteData.getMessage())
                //.hashtags("#tag")
                .createIntent();

        shareNoteData.getActivity().startActivity(intent);
    }

    private TwitterAuthClient client;
}

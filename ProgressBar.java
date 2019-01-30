import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ProgressBar;

public class MaterialProgressBar {

    private ProgressDialog dialog;

    private ProgressBar proBar;

    public MaterialProgressBar(Context context) {

        if (dialog == null) {

            dialog = new ProgressDialog(context);

            dialog.setIndeterminate(true);

            dialog.setMessage("Please wait...");

            dialog.setCanceledOnTouchOutside(false);

            dialog.setCancelable(false);
        }
    }

    public void showProgress() {

        if (!dialog.isShowing())

            dialog.show();
    }

    public void hideProgress() {

        if (dialog != null && dialog.isShowing())

            dialog.dismiss();
    }
}

/*****

How to call ?

Step 1: public class MyLeaveHistoryDetailCustomDialog extends CommonDialog

Step 2: Within OnCreate method
Objects.requireNonNull(getWindow()).setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
getWindow().setBackgroundDrawableResource(R.color.primarySkyBlueColor);

****/

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;


public class CommonDialog extends Dialog
{
    private ProgressDialog progressDialog;

    public CommonDialog(Context context)
    {
        super(context);
    }

    public void showProgress(Context mContext)
    {
        if(progressDialog == null)
        {
            progressDialog = new ProgressDialog(mContext);

            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        }
        if(!progressDialog.isShowing())
        {
            progressDialog.show();
        }
    }

    public void hideProgress()
    {
        try
        {

            if(progressDialog != null)
            {
                if(progressDialog.isShowing())
                {
                    progressDialog.dismiss();
                }
            }

        }
        catch (IllegalArgumentException e)
        {

            e.printStackTrace();
        }
    }
}

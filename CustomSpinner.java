import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ListPopupWindow;
import android.text.Spannable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.span.ststimesheet.R;

import java.util.ArrayList;

/**
 * Created by sts-184 on 7/23/18.
 */


public class CustomSpinner extends LinearLayout {

    Context mContext;
    ArrayList<String> objectList;
    int selectedPosition = 0;
    onSpinnerItemClickListener onspinnerItemClickListener;
    ListPopupWindow popupWindow;
    private MaterialEditText spinnertext;

    public CustomSpinner(Context context) {

        super(context);

        mContext = context;

        initViews(context);
    }

    public CustomSpinner(Context context, AttributeSet attrs) {

        super(context, attrs);

        mContext = context;

        initViews(context);
    }

    public void setOnItemClickListener(onSpinnerItemClickListener onspinnerItemClickListener) {

        this.onspinnerItemClickListener = onspinnerItemClickListener;
    }

    private void initViews(Context context) {

        setOrientation(LinearLayout.VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.customlayout, this, true);

        //left text view
        spinnertext = (MaterialEditText) getChildAt(0);
    }

    public void setAdapter(ArrayList<String> list) {

        objectList = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (int i = 0; i < list.size(); i++) {

                if (UtilsClass.isValidString(list.get(i)))

                    objectList.add(list.get(i));
            }

            if (objectList.size() > 0) {

                selectedPosition = 0;

                spinnertext.setText(objectList.get(0));
            }

            popupWindow = new ListPopupWindow(mContext);

            popupWindow.setAdapter(new ArrayAdapter<String>(mContext, R.layout.custom_spinner_view, objectList));

            popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    performclickItem(position, view);
                }
            });

            popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_shadow_spinner_bg));

            spinnertext.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    UtilsClass.hideSoftKeyBoard(view, mContext);

                    try {

                        if (popupWindow != null) {

                            popupWindow.setWidth(view.getRight() - view.getLeft());

                            popupWindow.setHeight(LayoutParams.WRAP_CONTENT);

                            popupWindow.setAnchorView(view);

                            popupWindow.setVerticalOffset(0);

                            if (!popupWindow.isShowing()) {

                                popupWindow.show();

                            } else {

                                popupWindow.dismiss();

                                //popupWindow.show();
                            }

                        }
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void performclickItem(int position, View view) {

        if (objectList != null && objectList.size() > 0 && position >= 0 && view != null) {

            spinnertext.setText(objectList.get(position));

            selectedPosition = position;

            if (onspinnerItemClickListener != null) {

                onspinnerItemClickListener.onSpinnerItemClick(position, view);
            }

            popupWindow.dismiss();
        }
    }

    public void setError(String errortext) {
        spinnertext.setError(errortext);
    }

    public int getSelectedItemPosition() {
        return selectedPosition;
    }

    public String getSelectedItem() {
        return spinnertext.getText().toString().trim();
    }

    public void setSelection(int selectedPosition) {

        if (selectedPosition >= 0 && objectList != null && objectList.size() > selectedPosition) {

            this.selectedPosition = selectedPosition;

            spinnertext.setText(UtilsClass.validateString(objectList.get(selectedPosition)));
        }
    }

    public void setText(String text) {

        if (objectList != null && objectList.size() > 0 && UtilsClass.isValidString(text)) {

            selectedPosition = objectList.indexOf(text);

            spinnertext.setText(UtilsClass.validateString(text));
        }
    }

    public void setText(Spannable text) {

        try {

            if (objectList != null && objectList.size() > 0 && text != null) {

                selectedPosition = objectList.indexOf(text);

                spinnertext.setText(text);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }


    public void setClickable(boolean isclickable) {
        spinnertext.setClickable(isclickable);
    }

    public void setEnabled(boolean isenabled) {
        spinnertext.setEnabled(isenabled);
    }

    public void performItemClick(int position, View view) {
        performclickItem(position, view);
    }

    @Override
    public void onDetachedFromWindow() {

        super.onDetachedFromWindow();

        if (popupWindow != null && popupWindow.isShowing()) {

            popupWindow.dismiss();

            popupWindow = null;
        }
    }

    public interface onSpinnerItemClickListener {

        void onSpinnerItemClick(int position, View view);
    }
}

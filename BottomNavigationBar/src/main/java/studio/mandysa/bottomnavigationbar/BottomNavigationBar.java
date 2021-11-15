package studio.mandysa.bottomnavigationbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class BottomNavigationBar extends LinearLayout implements OnClickListener {

    private final ArrayList<BottomNavigationItem> mItemList = new ArrayList<>();
    private int mPosition;
    private OnItemViewSelectedListener mListener;

    public BottomNavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnItemViewSelectedListener(OnItemViewSelectedListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onClick(View p1) {
        BottomNavigationItem item = (BottomNavigationItem) p1;
        int position = getItemPosition(item);
        if (mPosition != position) {
            ((BottomNavigationItem) getChildAt(mPosition)).check();
            item.check();
            mPosition = position;
        } else return;
        if (mListener != null)
            mListener.onClick(position);
    }

    @Override
    public BottomNavigationItem getChildAt(int index) {
        return (BottomNavigationItem) super.getChildAt(index);
    }

    private int getItemPosition(BottomNavigationItem item) {
        for (int i = 0; i < getChildCount(); i++) {
            BottomNavigationItem view = getChildAt(i);
            if (item.equals(view)) return i;
        }
        return -1;
    }

    public void setSelectedPosition(int position) {
        onClick(getChildAt(position));
        if (mListener != null) mListener.onClick(position);
    }

    public int getSelectedPosition() {
        return mPosition;
    }

    public void addItem(@androidx.annotation.NonNull BottomNavigationItem item) {
        mItemList.add(item);
        item.setOnClickListener(this);
        addView(item);
        if (mItemList.size() == 1) mItemList.get(0).check();
    }

    public interface OnItemViewSelectedListener {
        void onClick(int position);
    }

}

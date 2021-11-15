package studio.mandysa.bottomnavigationbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;

@SuppressLint("ViewConstructor")
public class BottomNavigationItem extends LinearLayout {

    private TextView mTextView;
    private ImageView mImageView;
    @ColorInt
    private int mActiveColor;
    @ColorInt
    private int mInActiveColor;
    private boolean mState;

    public BottomNavigationItem(Context context, int imageRes, String text) {
        super(context);
        mActiveColor = getResources().getColor(R.color.default_checked_color);
        mInActiveColor = getResources().getColor(R.color.default_unchecked_color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            TypedArray ta = getContext().obtainStyledAttributes(new int[]{android.R.attr.selectableItemBackgroundBorderless});
            Drawable mDefaultFocusHighlightCache = ta.getDrawable(0);
            ta.recycle();
            setBackground(mDefaultFocusHighlightCache);
        }
        setGravity(Gravity.CENTER);
        setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 1f));
        setOrientation(VERTICAL);
        if (imageRes != 0) {
            mImageView = new ImageView(context);
            mImageView.setImageResource(imageRes);
            mImageView.setColorFilter(mInActiveColor);
            addView(mImageView);
        }
        if (text != null) {
            mTextView = new TextView(context);
            mTextView.setText(text);
            mTextView.setTextColor(mInActiveColor);
            mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            mTextView.setPadding(0, dp4(), 0, 0);
            mTextView.setGravity(Gravity.CENTER);
            addView(mTextView);
        }
    }

    public BottomNavigationItem setActiveColorResource(@ColorRes int colorRes) {
        return setActiveColor(getResources().getColor(colorRes));
    }

    public BottomNavigationItem setActiveColor(@ColorInt int color) {
        if (mImageView != null) {
            mActiveColor = color;
        }
        return this;
    }

    public BottomNavigationItem setInActiveColorResource(@ColorRes int colorRes) {
        return setInActiveColor(getResources().getColor(colorRes));
    }

    public BottomNavigationItem setInActiveColor(@ColorInt int color) {
        if (mImageView != null) {
            mInActiveColor = color;
        }
        return this;
    }

    public BottomNavigationItem setTextSize(int size) {
        if (mTextView != null)
            mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        return this;
    }

    void check() {
        mState = !mState;
        if (mTextView != null)
            mTextView.setTextColor(mState ? this.mActiveColor : this.mInActiveColor);
        if (mImageView != null)
            mImageView.setColorFilter(mState ? this.mActiveColor : this.mInActiveColor);
    }

    private int dp4() {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (float) 4, getResources().getDisplayMetrics());
    }

}

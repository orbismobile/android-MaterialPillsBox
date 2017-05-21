package pe.elcomercio.materialpillsbox;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 5/18/17.
 */

public class MaterialPillsBox extends ViewGroup implements View.OnClickListener {

//    public int uniqueTagId;
//    public int tagPosition;

    private List<PillEntity> pillEntityList = new ArrayList<>();


    private int maxPills;

    public MaterialPillsBox(Context context) {
        super(context);

    }

    public MaterialPillsBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0, 0);
    }

    public MaterialPillsBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MaterialPillsBox(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr, defStyleRes);
    }

    public void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MaterialPillsBox, defStyleAttr, defStyleRes);

        maxPills = a.getInt(
                R.styleable.MaterialPillsBox_max, 10);
//        setProgress(a.getFloat(
//                R.styleable.ImageProfilePercentage_progress, mProgress));
//        if (!a.hasValue(R.styleable.ImageProfilePercentage_backgroundSize)) {
//            if (a.hasValue(R.styleable.ImageProfilePercentage_circleSize)) {
//                setProgressRingSize(a.getDimension(
//                        R.styleable.ImageProfilePercentage_circleSize, mProgressRingSize));
//                setBackgroundRingSize(mProgressRingSize);
//            }
//        } else {
//            setBackgroundRingSize(a.getDimension(
//                    R.styleable.ImageProfilePercentage_backgroundSize, mBackgroundRingSize));
//            setProgressRingSize(a.getDimension(
//                    R.styleable.ImageProfilePercentage_circleSize, mProgressRingSize));
//        }
//        setProgressRingOutline(
//                a.getBoolean(R.styleable.ImageProfilePercentage_progressOutline, false));
//        setBackgroundRingColor(a.getColor(
//                R.styleable.ImageProfilePercentage_backgroundCircle, mBackgroundRingColor));
//        setProgressRingColor(a.getColor(
//                R.styleable.ImageProfilePercentage_progressColor, mProgressRingColor));
//        setProgressRingCap(a.getInt(
//                R.styleable.ImageProfilePercentage_animation, Paint.Cap.BUTT.ordinal()));

        a.recycle();
    }

    public void refreshPosition(List<PillEntity> pillEntityList) {
        for (int i = 0; i < pillEntityList.size(); i++) {
            getChildAt(i).setTag(i);
        }
    }

    public void addPill(PillEntity pillEntity) {
        if (pillEntityList.size() < maxPills) {
            pillEntityList.add(pillEntity);
            final LinearLayout linear = (LinearLayout) LayoutInflater.from(getContext())
                    .inflate(R.layout.pills_box_layout, this, false);
            TextView tv1 = (TextView) linear.findViewById(R.id.lblPill);
            tv1.setText(pillEntity.getName());
            linear.setTag(pillEntityList.size() - 1);
            addView(linear);
            linear.setOnClickListener(this);
        } else {
            Log.e("MATERIALPILLSBOX", "got max number of pills");
        }
    }

    @Override
    public void onClick(View v) {
        int viewPosition = (int) v.getTag();
        Toast.makeText(getContext(), "position: " + v.getTag() +
                        " tagName: " + pillEntityList.get(viewPosition).getName(),
                Toast.LENGTH_SHORT).show();
    }


    /**
     * This method is called each time when you add a new ChildView
     *
     * @param widthMeasureSpec  a value something like 1073742560
     * @param heightMeasureSpec a value something like 1073742560
     *                          For example if you set wrap_content to layout_height of this ViewGroup heightMeasureSpec is 0
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // Values of the Width and Height but in pixels, something like 640x480, 720x200
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        // Long number used for the setMeasuredDimension(,) for the viewGroup
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;

        int lineWidth = 0;
        int lineHeight = 0;

        int cCount = getChildCount();

        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);

            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();


            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (lineWidth + childWidth > sizeWidth - getPaddingLeft() - getPaddingRight()) {
                width = Math.max(width, lineWidth);
                lineWidth = childWidth;
                height += lineHeight;
                lineHeight = childHeight;
            } else {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            if (i == cCount - 1) {
                width = Math.max(lineWidth, width);
                height += lineHeight;
            }
        }

        /**
         * This method set a measured dimension for this ViewGroup
         * If you set setMeasuredDimension(100,100), you will get a square
         * with 2 pills inside it with vertical orientation.
         * MeasureSpec.EXACTLY return the MeasureSpec.getMode() but of all screen's width
         */
        setMeasuredDimension(
                modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingLeft() + getPaddingRight(),
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height + getPaddingTop() + getPaddingBottom()
        );
    }


    private List<List<View>> mAllViews = new ArrayList<List<View>>();
    private List<Integer> mLineHeight = new ArrayList<Integer>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        mLineHeight.clear();

        int width = getWidth();

        int lineWidth = 0;
        int lineHeight = 0;
        List<View> lineViews = new ArrayList<View>();

        int cCount = getChildCount();

        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();


            if (lineWidth + childWidth + lp.leftMargin + lp.rightMargin > width - getPaddingLeft() - getPaddingRight()) {
                mLineHeight.add(lineHeight);
                mAllViews.add(lineViews);

                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                lineViews = new ArrayList<View>();
            }

            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin + lp.bottomMargin);
            lineViews.add(child);


        }

        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);


        int left = getPaddingLeft();
        int top = getPaddingTop();

        int lineNum = mAllViews.size();

        for (int i = 0; i < lineNum; i++) {
            lineViews = mAllViews.get(i);
            lineHeight = mLineHeight.get(i);

            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                if (child.getVisibility() == View.GONE) {
                    continue;
                }

                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();

                child.layout(lc, tc, rc, bc);

                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            left = getPaddingLeft();
            top += lineHeight;
        }

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

}

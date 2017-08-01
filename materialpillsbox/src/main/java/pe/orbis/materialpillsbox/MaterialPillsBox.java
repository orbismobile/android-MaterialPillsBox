package pe.orbis.materialpillsbox;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 5/18/17.
 */

public class MaterialPillsBox extends ViewGroup implements View.OnClickListener {

    private static final String TAG = MaterialPillsBox.class.getPackage().getName();
    private List<Object> objectList = new ArrayList<>();

    private int maxPills;
    private int backgroundPill;
    private int backgroundPillSelected;
    private boolean hideCloseIcon;
    private int pillTextColor;
    private int pillCloseIcon;
    private int pillMarginTop;
    private int pillMarginBottom;
    private int pillMarginLeft;
    private int pillMarginRight;
    private int selectionMode;

    private int pillPaddingTop;
    private int pillPaddingBottom;
    private int pillPaddingLeft;
    private int pillPaddingRight;

    private int closeIconMarginLeft;

    private static final int DEFAULT_MODE_MULTI_SELECTION = 1;
    private static final int DEFAULT_MAX_PILLS = 20;
    private OnPillClickListener onPillClickListener;

    public void setOnPillClickListener(OnPillClickListener onPillClickListener) {
        this.onPillClickListener = onPillClickListener;
    }

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

    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MaterialPillsBox, defStyleAttr, defStyleRes);

        maxPills = a.getInt(
                R.styleable.MaterialPillsBox_maxPills, DEFAULT_MAX_PILLS);

        backgroundPill = a.getResourceId(R.styleable.MaterialPillsBox_pillBackground, R.drawable.shape_button_pill);
        backgroundPillSelected = a.getResourceId(R.styleable.MaterialPillsBox_pillSelectedBackground, R.drawable.shape_button_selected_pill);

        pillCloseIcon = a.getResourceId(R.styleable.MaterialPillsBox_pillCloseIcon, R.drawable.ic_close_white_18dp);

        pillMarginTop = a.getDimensionPixelSize(
                R.styleable.MaterialPillsBox_pillMarginTop, getResources().getDimensionPixelOffset(R.dimen.default_pill_margin));
        pillMarginBottom = a.getDimensionPixelSize(
                R.styleable.MaterialPillsBox_pillMarginBottom, getResources().getDimensionPixelOffset(R.dimen.default_pill_margin));
        pillMarginLeft = a.getDimensionPixelSize(
                R.styleable.MaterialPillsBox_pillMarginLeft, getResources().getDimensionPixelOffset(R.dimen.default_pill_margin));
        pillMarginRight = a.getDimensionPixelSize(
                R.styleable.MaterialPillsBox_pillMarginRight, getResources().getDimensionPixelOffset(R.dimen.default_pill_margin));
        hideCloseIcon =
                a.getBoolean(R.styleable.MaterialPillsBox_showCloseIcon, false);
        pillTextColor = a.getColor(
                R.styleable.MaterialPillsBox_pillTextColor, ContextCompat.getColor(this.getContext(), R.color.md_white_1000));
        pillPaddingTop = a.getDimensionPixelSize(
                R.styleable.MaterialPillsBox_pillPaddingTop, getResources().getDimensionPixelOffset(R.dimen.default_pill_padding_top));
        pillPaddingBottom = a.getDimensionPixelSize(
                R.styleable.MaterialPillsBox_pillPaddingBottom, getResources().getDimensionPixelOffset(R.dimen.default_pill_padding_bottom));
        pillPaddingLeft = a.getDimensionPixelSize(
                R.styleable.MaterialPillsBox_pillPaddingLeft, getResources().getDimensionPixelOffset(R.dimen.default_pill_padding_left));
        pillPaddingRight = a.getDimensionPixelSize(
                R.styleable.MaterialPillsBox_pillPaddingRight, getResources().getDimensionPixelOffset(R.dimen.default_pill_padding_right));
        closeIconMarginLeft = a.getDimensionPixelSize(
                R.styleable.MaterialPillsBox_pillCloseIconMarginLeft, getResources().getDimensionPixelOffset(R.dimen.default_close_icon_margin_left));

        selectionMode = a.getInteger(R.styleable.MaterialPillsBox_pillSelectionMode, DEFAULT_MODE_MULTI_SELECTION);

        a.recycle();
    }


    public void setPillTextColor(int pillTextColor) {
        this.pillTextColor = pillTextColor;
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        removeAllViews();
        for (int i = 0; i < objectList.size(); i++) {
            if (i < maxPills) {
                LinearLayout linear = setupPillChildView(((PillEntity) objectList.get(i)).getMessage(), ((PillEntity) objectList.get(i)).isPressed());
                addView(linear);
                linear.setTag(i);
            }
        }
    }

    public void initFirstSetup(List<Object> objects) {
        this.objectList = objects;
        notifyDataSetChanged();
    }

    private LinearLayout setupPillChildView(String pillMessage, boolean isPressed) {
        final LinearLayout linear = (LinearLayout) LayoutInflater.from(getContext())
                .inflate(R.layout.pills_box_layout, this, false);
        linear.setBackgroundResource(backgroundPill);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(pillMarginLeft, pillMarginTop, pillMarginRight, pillMarginBottom);
        linear.setLayoutParams(layoutParams);
        linear.setPadding(pillPaddingLeft, pillPaddingTop, pillPaddingRight, pillPaddingBottom);
        if (isPressed) {
            linear.setBackgroundResource(backgroundPillSelected);
        } else {
            linear.setBackgroundResource(backgroundPill);
        }
        TextView lblMessage = (TextView) linear.findViewById(R.id.lblMessage);
        lblMessage.setText(pillMessage);
        lblMessage.setTextColor(pillTextColor);
        ImageView imgClose = (ImageView) linear.findViewById(R.id.imgClose);
        imgClose.setBackgroundResource(pillCloseIcon);
        if (hideCloseIcon) {
            imgClose.setVisibility(View.VISIBLE);
        } else {
            imgClose.setVisibility(View.GONE);
        }
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) imgClose.getLayoutParams();
        marginLayoutParams.leftMargin = closeIconMarginLeft;
        imgClose.setLayoutParams(marginLayoutParams);
        imgClose.setOnClickListener(this);
        linear.setOnClickListener(this);
        return linear;
    }

    @Override
    public void onClick(View v) {
        if (v instanceof ImageView) {//onClick CloseIcon
            //v.getParent()).getTag() returns the linearLayoutPosition
            int pillPosition = (int) ((LinearLayout) v.getParent()).getTag();

            if (onPillClickListener != null) {
                onPillClickListener.onCloseIconClick(this, pillPosition);
            }

        } else {
            //onClick for lnlContainer
            int linearLayoutPosition = (int) v.getTag();
            PillEntity pillEntity = (PillEntity) (objectList.get(linearLayoutPosition));
            if (selectionMode == DEFAULT_MODE_MULTI_SELECTION) {
                if (pillEntity.isPressed()) {
                    pillEntity.setPressed(false);
                    v.setBackgroundResource(backgroundPill);
                } else {
                    pillEntity.setPressed(true);
                    v.setBackgroundResource(backgroundPillSelected);
                }
            }
            if (onPillClickListener != null) {
                onPillClickListener.onPillClick(this, linearLayoutPosition);
            }
        }
    }

    /**
     * (2)
     * This method needs override the super constructor strongly
     * This method is called after constructor is executed
     * This method is executed before onLayout method
     * This method is called each time when you add a new ChildView
     *
     * @param widthMeasureSpec  a value of the ViewGroup something like 1073742560
     * @param heightMeasureSpec a value of the ViewGroup something like 1073742560
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

        /*
         * This method set a measured dimension for this ViewGroup.
         * <p>
         * If you set setMeasuredDimension(100,100), you will get a square
         * with 2 pills inside it with vertical orientation.
         * MeasureSpec.EXACTLY return the MeasureSpec.getMode() but of all screen's width
         */
        setMeasuredDimension(
                modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingLeft() + getPaddingRight(),
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height + getPaddingTop() + getPaddingBottom()
        );
    }

    private final List<List<View>> mAllViews = new ArrayList<List<View>>();
    private final List<Integer> mLineHeight = new ArrayList<Integer>();


    /**
     * (3)
     * This method is called after onMeasure method
     * This method is called each time when you add a new ChildView
     * When extends from ViewGroup this method is required
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        mAllViews.clear();
        mLineHeight.clear();

        //getWidth() returns twice the dp parent's value in pixels, if you set 200dp to layout_width then we'll get 400
        int width = getWidth();

        int lineWidth = 0;
        int lineHeight = 0;
        List<View> lineViews = new ArrayList<>();

        int cCount = getChildCount();

        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            //this conditional is executed when a childView reach the end of a row
            if (lineWidth + childWidth + lp.leftMargin + lp.rightMargin > width - getPaddingLeft() - getPaddingRight()) {
                //we add a lineHeight to the list only when we reach the width of parent
                mLineHeight.add(lineHeight);
                //we add the list of child views to the list(mAllViews) only when we reach the width of parent
                mAllViews.add(lineViews);

                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                lineViews = new ArrayList<>();
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

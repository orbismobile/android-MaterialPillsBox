package pe.elcomercio.materialpillsbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 5/18/17.
 *
 */

public class MaterialPillsBox extends ViewGroup{

    public MaterialPillsBox(Context context) {
        super(context);
        init(context, null);
    }

    public MaterialPillsBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MaterialPillsBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialPillsBox);
        final LayoutInflater inflater = LayoutInflater.from(context);


    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {




    }
}

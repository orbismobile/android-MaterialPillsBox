package pe.orbis.materialpillsbox;


import android.view.View;

/**
 * Created by Carlos Vargas on 29/05/17.
 */

public interface OnPillClickListener {
    void onPillClick(View view, int position);
    void onCloseIconClick(View view, int position);
}

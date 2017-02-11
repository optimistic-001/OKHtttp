package specialeffects.junjie.com.okhtttp.Sideslip;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by JIE on 2016/9/6.
 */
public class ViewGragOne extends LinearLayout {
    private ViewDragHelper mDragHelper;

    public ViewGragOne(Context context) {
        super(context);
    }

    public ViewGragOne(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGragOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragHelper = ViewDragHelper.create(this,1.0f,new DragHelperCallback());
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }
    class DragHelperCallback extends ViewDragHelper.Callback{
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return false;
        }
    }
}

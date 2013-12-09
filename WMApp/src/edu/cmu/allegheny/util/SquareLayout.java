/**
 * 
 */
package edu.cmu.allegheny.util;

/**
 * A customized LinerLayout
 * 
 * @author yinxu
 *
 */
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class SquareLayout extends LinearLayout{

	public SquareLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}

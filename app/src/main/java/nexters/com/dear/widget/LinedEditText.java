package nexters.com.dear.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatEditText;
import nexters.com.dear.R;


public class LinedEditText extends AppCompatEditText {
    private Paint mPaint = new Paint();
    private Bitmap mBitmap;
    private Rect source, bitmapRect;
    public LinedEditText(Context context) {
        super(context);
        initPaint();
    }

    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public LinedEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint(){
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0x80000000);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_letter_line);
        setLineSpacing(40, 1);
    }

    @Override protected void onDraw(Canvas canvas){
        int left = getLeft();
        int right = getRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int height = getHeight();
        int lineHeight = getLineHeight();
        int count = (height-paddingTop-paddingBottom) / lineHeight;
        left = 0;
        mBitmap = Bitmap.createScaledBitmap(mBitmap, getWidth() - (paddingRight + paddingLeft), mBitmap.getHeight(), true);
        int absLeft = left + paddingLeft;
        for (int i = 0; i < count; i++) {
            int baseline = lineHeight * (i + 1) + paddingTop - (int)getTextSize() + ((int)getLineSpacingExtra()/2);
//            canvas.drawLine(left+paddingLeft, baseline, right-paddingRight, baseline, mPaint);
//            source = new Rect(absLeft, baseline, parentWidth, mBitmap.getHeight());
//            bitmapRect = new Rect(absLeft ,baseline , parentWidth, mBitmap.getHeight());
//            canvas.drawBitmap(mBitmap, source, bitmapRect, new Paint());
            canvas.drawBitmap(mBitmap, absLeft, baseline, null);
        }

        super.onDraw(canvas);
    }
}

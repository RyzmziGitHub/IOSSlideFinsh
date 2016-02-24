
package silde.ry.org.iosslidefinsh;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

public class SlidingActivity extends AppCompatActivity implements SlidingLayout.SlidingListener {
    private View mPreview;

    private float mInitOffset;
    private String mBitmapId;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.slide_layout);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        mInitOffset = -(1.f / 3) * metrics.widthPixels;

        mPreview = findViewById(R.id.iv_preview);
        FrameLayout contentView = (FrameLayout) findViewById(R.id.content_view);

        contentView.addView(LayoutInflater.from(this).inflate(layoutResID, null), new FrameLayout.LayoutParams(MATCH_PARENT,
                MATCH_PARENT, Gravity.BOTTOM));

        final SlidingLayout slideLayout = (SlidingLayout) findViewById(R.id.slide_layout);
        slideLayout.setSlidingListener(this);
        slideLayout.setEdgeSize((int) (metrics.density * 20));

        mBitmapId = getIntent().getExtras().getString("bitmap_id");
        Bitmap bitmap = IntentUtils.getInstance().getBitmap(mBitmapId);
        if (null != bitmap) {
            if (Build.VERSION.SDK_INT >= 16) {
                mPreview.setBackground(new BitmapDrawable(bitmap));
            } else {
                mPreview.setBackgroundDrawable(new BitmapDrawable(bitmap));
            }

            IntentUtils.getInstance().setIsDisplayed(mBitmapId, true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        IntentUtils.getInstance().setIsDisplayed(mBitmapId, false);
    }


    @SuppressLint("NewApi")
    @Override
    public void onPanelSlide(View panel, float slideOffset) {
        if (slideOffset <= 0) {
        } else if (slideOffset < 1) {
            mPreview.setTranslationX(mInitOffset * (1 - slideOffset));
        } else {
            mPreview.setTranslationX(0);
            finish();
            overridePendingTransition(0, 0);
        }
    }

}

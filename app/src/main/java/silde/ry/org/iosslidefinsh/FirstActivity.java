package silde.ry.org.iosslidefinsh;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by renyang on 16/2/22.
 */
public class FirstActivity extends SlidingActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new TestAdapter(this));
    }

    class TestAdapter extends PagerAdapter{

        Context context;

        public TestAdapter(Context context){
            this.context = context;
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return 2;
        }

        /**
         * Determines whether a page View is associated with a specific key object
         * as returned by {@link #instantiateItem(ViewGroup, int)}. This method is
         * required for a PagerAdapter to function properly.
         *
         * @param view   Page View to check for association with <code>object</code>
         * @param object Object to check for association with <code>view</code>
         * @return true if <code>view</code> is associated with the key object <code>object</code>
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView tv = new TextView(context);
            tv.setText("xx"+position);
            tv.setTextColor(Color.RED);
            container.addView(tv);
            return tv;
        }
    }

}

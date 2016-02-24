package silde.ry.org.iosslidefinsh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FirstActivity.class);
                IntentUtils.getInstance().startActivity(MainActivity.this, intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        IntentUtils.getInstance().clear();
    }
}

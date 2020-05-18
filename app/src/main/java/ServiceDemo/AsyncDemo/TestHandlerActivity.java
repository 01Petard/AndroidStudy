package ServiceDemo.AsyncDemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hzxcompany.androidstudy.R;

public class TestHandlerActivity extends AppCompatActivity {

    private static final int UPDATE_TEXT =1;
    private TextView textView;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler);
        textView = findViewById(R.id.textView);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.what = UPDATE_TEXT;
                handler.sendMessage(message);
            }
        });
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    textView.setText("我是修改后的一段文字！");
                    break;
                default:
                    break;
            }
        }
    };
}

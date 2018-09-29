package com.example.romasha.calcxe;

import android.icu.text.DecimalFormat;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
   // константы – это будут ID пунктов меню.
final int MENU_EXIT_ID=1;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    // создание меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,MENU_EXIT_ID,0,"EXIT");
        return super.onCreateOptionsMenu(menu);
    }
    // обработка нажатий на пункты меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case MENU_EXIT_ID:
                // выход из приложения
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelloTextView = (TextView)findViewById(R.id.textView);
        mUglevodiEditText=(EditText)findViewById(R.id.uglevodi);
        mMassaEditText=(EditText)findViewById(R.id.inputmass) ;
        mBtnXe=(Button)findViewById(R.id.buttonXe) ;

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    private TextView mHelloTextView;
    private EditText mUglevodiEditText;
    private EditText mMassaEditText;
     Button mBtnXe;

    public void onClick(View view) {
          //очищаем поле с результатом
        mHelloTextView.setText("");
        //проверка полей на пустату
        if(TextUtils.isEmpty(mUglevodiEditText.getText().toString())||TextUtils.isEmpty(mMassaEditText.getText().toString()))
        {
return;
        }
          //вычесляем ХЕ
        double u, m, c,xe;
        String s1 = mUglevodiEditText.getText().toString();
        String s2 = mMassaEditText.getText().toString();
        u = Double.parseDouble(s1);
        m = Double.parseDouble(s2);
        c = (u * m)/100;
        xe=c/12;



          //обрезаем результат до двух знаков после запятой
        mHelloTextView.setText(mHelloTextView.getText()+String.format(Locale.US, "%.2f",xe)+" XE");
        //очищаем поля
        mUglevodiEditText.setText("");
        mMassaEditText.setText("");

    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

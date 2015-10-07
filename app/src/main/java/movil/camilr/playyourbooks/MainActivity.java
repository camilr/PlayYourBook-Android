package movil.camilr.playyourbooks;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import movil.camilr.playyourbooks.adapters.PagerFrAdapter;
import movil.camilr.playyourbooks.fragments.LocalFragment;
import movil.camilr.playyourbooks.fragments.RecientesFragment;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    List<PagerTitle> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);
        data = new ArrayList<>();

        LocalFragment fragmentLocal = new LocalFragment();
        RecientesFragment fragmentRecientes = new RecientesFragment();

        data.add(fragmentRecientes);
        data.add(fragmentLocal);

        PagerFrAdapter adapter = new PagerFrAdapter(getSupportFragmentManager(),data);

        pager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menu_addBook:
                Intent intent = new Intent(this, ExploradorActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_buscar:

                break;
            case R.id.menu_config:

                break;

        }


        return super.onOptionsItemSelected(item);
    }
}

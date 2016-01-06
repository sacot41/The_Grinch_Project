package com.sacot41.grinch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by Samuel on 2015-12-11.
 */
public class GrinchBaseActiviy extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int theId = item.getItemId();
        if (theId == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.swipeback_stack_to_front, R.anim.swipeback_stack_right_out);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.swipeback_stack_right_in, R.anim.swipeback_stack_to_back);
    }

}

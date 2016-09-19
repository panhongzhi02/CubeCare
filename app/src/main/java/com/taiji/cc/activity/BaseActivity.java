package com.taiji.cc.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.taiji.cc.helper.ToolBarHelper;


/**
 * Created by panho on 2016/8/31.
 */
public abstract class BaseActivity extends AppCompatActivity{

    private ToolBarHelper mToolBarHelper;

    public Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mToolBarHelper = new ToolBarHelper(this,layoutResID);
        mToolbar = mToolBarHelper.getToolbar();
        setContentView(mToolBarHelper.getContentView());
        setSupportActionBar(mToolbar);
        onCreateCustomToolBar(mToolbar);
        mToolBarHelper.setOnTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTitleClick();
            }
        });
    }

    public void onCreateCustomToolBar(Toolbar toolbar){
        toolbar.setContentInsetsRelative(0,0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setTitle(String title){
        mToolBarHelper.setTitle(title);
    }

    public void onTitleClick(){

    }
}

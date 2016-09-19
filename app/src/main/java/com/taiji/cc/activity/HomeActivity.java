package com.taiji.cc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.taiji.cc.R;
import com.taiji.cc.adapter.PatientListAdapter;
import com.taiji.cc.factorys.CommonFactory;
import com.taiji.cc.fragment.ApkPlugFragment;
import com.taiji.cc.fragment.EducationFragment;
import com.taiji.cc.fragment.OrderFragment;
import com.taiji.cc.fragment.PatientFragment;
import com.taiji.cc.fragment.RecordFragment;
import com.taiji.cc.fragment.ReportFragment;
import com.taiji.cc.fragment.SignFragment;
import com.taiji.cc.greendao.bean.Patient;
import com.taiji.cc.greendao.manager.PatientDBManager;
import com.taiji.cc.scan.bean.ScanResponse;
import com.taiji.cc.util.T;

import java.util.List;

/**
 * Created by panho on 2016/8/31.
 */
public class HomeActivity extends ScanBaseActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener{

    private Button order_btn;
    private Button sign_btn;
    private Button record_btn;
    private Button education_btn;
    private Button report_btn;

    private Fragment order_fragment;
    private Fragment sign_fragment;
    private Fragment record_fragment;
    private Fragment education_fragment;
    private Fragment report_fragment;

    private Fragment patient_fragment;
    private Fragment apkplug_fragment;

    private FragmentManager mFragmentManager;

    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private FragmentTransaction transaction;

    private TextView user_name_tv;
    private TextView department_tv;

    private RecyclerView patients_rv;
    private PatientListAdapter adapter;
    private List<Patient> patients;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_acy);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

        order_btn = (Button) findViewById(R.id.order_btn);
        sign_btn = (Button) findViewById(R.id.sign_btn);
        record_btn = (Button) findViewById(R.id.record_btn);
        education_btn = (Button) findViewById(R.id.education_btn);
        report_btn = (Button) findViewById(R.id.report_btn);
        order_btn.setOnClickListener(this);
        sign_btn.setOnClickListener(this);
        record_btn.setOnClickListener(this);
        education_btn.setOnClickListener(this);
        report_btn.setOnClickListener(this);

        mFragmentManager = getSupportFragmentManager();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView =  navigationView.getHeaderView(0);
        user_name_tv = (TextView) headerView.findViewById(R.id.user_name_tv);
        department_tv = (TextView) headerView.findViewById(R.id.department_tv);
        user_name_tv.setText("潘宏智");
        department_tv.setText("心血管外科");
        initButton(R.id.nav_camera);

        patients_rv = (RecyclerView) findViewById(R.id.patients_rv);
        patients_rv.setLayoutManager(new LinearLayoutManager(this));
        final PatientDBManager manager = PatientDBManager.getInstance(this);
        patients = manager.queryPatientList();
        adapter = new PatientListAdapter(patients);
        patients_rv.setAdapter(adapter);
        patients_rv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                CommonFactory.getFactory(HomeActivity.this).changePatient(patients.get(i).getP_id(),patients.get(i).getPp_visitid());
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
    }

    @Override
    public void onClick(View v) {
        initButton(v.getId());
    }

    private void initButton(int id){
        transaction = mFragmentManager.beginTransaction();

        order_btn.setBackgroundResource(R.drawable.iv_order_normal);
        order_btn.setClickable(true);
        sign_btn.setBackgroundResource(R.drawable.iv_sign_normal);
        sign_btn.setClickable(true);
        record_btn.setBackgroundResource(R.drawable.iv_record_normal);
        record_btn.setClickable(true);
        education_btn.setBackgroundResource(R.drawable.iv_education_normal);
        education_btn.setClickable(true);
        report_btn.setBackgroundResource(R.drawable.iv_report_normal);
        report_btn.setClickable(true);

        if(order_fragment!=null){
            transaction.hide(order_fragment);
        }
        if(sign_fragment!=null){
            transaction.hide(sign_fragment);
        }
        if(record_fragment!=null){
            transaction.hide(record_fragment);
        }
        if(education_fragment!=null){
            transaction.hide(education_fragment);
        }
        if(report_fragment!=null){
            transaction.hide(report_fragment);
        }
        if(patient_fragment!=null){
            transaction.hide(patient_fragment);
        }
        if(apkplug_fragment!=null){
            transaction.hide(apkplug_fragment);
        }

        switch (id){
            case R.id.order_btn:
                order_btn.setBackgroundResource(R.drawable.iv_order_press);
                order_btn.setClickable(false);
                if(order_fragment==null){
                    order_fragment = new OrderFragment();
                    transaction.add(R.id.id_content,order_fragment);
                }else{
                    transaction.show(order_fragment);
                }
                break;
            case R.id.sign_btn:
                sign_btn.setBackgroundResource(R.drawable.iv_sign_press);
                sign_btn.setClickable(false);
                if(sign_fragment==null){
                    sign_fragment = new SignFragment();
                    transaction.add(R.id.id_content,sign_fragment);
                }else{
                    transaction.show(sign_fragment);
                }
                break;
            case R.id.record_btn:
                record_btn.setBackgroundResource(R.drawable.iv_record_press);
                record_btn.setClickable(false);
                if(record_fragment==null){
                    record_fragment = new RecordFragment();
                    transaction.add(R.id.id_content,record_fragment);
                }else{
                    transaction.show(record_fragment);
                }
                break;
            case R.id.education_btn:
                education_btn.setBackgroundResource(R.drawable.iv_education_press);
                education_btn.setClickable(false);
                if(education_fragment==null){
                    education_fragment = new EducationFragment();
                    transaction.add(R.id.id_content,education_fragment);
                }else{
                    transaction.show(education_fragment);
                }
                break;
            case R.id.report_btn:
                report_btn.setBackgroundResource(R.drawable.iv_report_press);
                report_btn.setClickable(false);
                if(report_fragment==null){
                    report_fragment = new ReportFragment();
                    transaction.add(R.id.id_content,report_fragment);
                }else{
                    transaction.show(report_fragment);
                }
                break;
            case R.id.nav_camera:
                if(patient_fragment==null){
                    patient_fragment = new PatientFragment();
                    transaction.add(R.id.id_content,patient_fragment);
                }else{
                    transaction.show(patient_fragment);
                }
                break;
            case R.id.nav_share:
                if(apkplug_fragment==null){
                    apkplug_fragment = new ApkPlugFragment();
                    transaction.add(R.id.id_content,apkplug_fragment);
                }else{
                    transaction.show(apkplug_fragment);
                }
                break;
        }
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            initButton(id);
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_manage) {
            Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share) {
            initButton(R.id.nav_share);
        } else if (id == R.id.nav_send) {
            finish();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onTitleClick() {
        super.onTitleClick();
        T.showShort(this,"点击了标题");
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {

        }
    }

    @Override
    public void onScanSuccess(ScanResponse response) {
        T.showShort(this,response.getResult());
    }

    @Override
    public void onMatchSuccess(List<ScanResponse> responses) {
        super.onMatchSuccess(responses);
        T.showShort(this,responses.get(0).getResult()+"/n"
                +responses.get(1).getResult());
    }
}

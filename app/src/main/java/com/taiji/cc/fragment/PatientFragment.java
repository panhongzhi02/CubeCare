package com.taiji.cc.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.taiji.cc.R;
import com.taiji.cc.activity.PatientInfoActivity;
import com.taiji.cc.adapter.PatientAdapter;
import com.taiji.cc.factorys.CommonFactory;
import com.taiji.cc.greendao.bean.Patient;
import com.taiji.cc.greendao.manager.PatientDBManager;
import com.taiji.cc.http.methods.HttpMethod;
import com.taiji.cc.http.service.PatientService;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by panho on 2016/9/2.
 */
public class PatientFragment extends BaseFragment{

    private SwipeRefreshLayout patient_sr;

    private RecyclerView patient_rv;

    private PatientAdapter adapter;

    private List<Patient> mPatients = new ArrayList<>();

    private PatientDBManager manager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.patient_fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        manager = PatientDBManager.getInstance(mActivity);

        patient_sr = (SwipeRefreshLayout) view.findViewById(R.id.patient_sr);
        patient_sr.setColorSchemeResources(R.color.colorPrimary);
        patient_sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadPatients();
            }
        });

        patient_rv = (RecyclerView) view.findViewById(R.id.patient_rv);
        patient_rv.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new PatientAdapter(mPatients);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        adapter.setEmptyView(LayoutInflater.from(mActivity).inflate(R.layout.empty_view, (ViewGroup) patient_rv.getParent(),false));

        patient_rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
            @Override
            public void itemClick(Patient patient) {
                CommonFactory.getFactory(mActivity).changePatient(patient.getP_id(),patient.getPp_visitid());
                Intent intent = new Intent(mActivity,PatientInfoActivity.class);
                startActivity(intent);
            }
        });
        loadPatients();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(!hidden){
            Log.d("pan","进入患者列表");
        }
        super.onHiddenChanged(hidden);
    }

    private void loadPatients(){
        PatientService service = HttpMethod.getInstance().getRetrofit().create(PatientService.class);
        service.getPatients("112")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<List<Patient>, List<Patient>>() {
                    @Override
                    public List<Patient> call(List<Patient> patients) {
                        manager.deleteAll();
                        manager.insertList(patients);
                        return manager.queryPatientList();
                    }
                })
                .subscribe(new Subscriber<List<Patient>>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        patient_sr.setRefreshing(true);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mPatients = manager.queryPatientList();
                        adapter.setNewData(mPatients);
                        patient_sr.setRefreshing(false);
                    }

                    @Override
                    public void onNext(List<Patient> patients) {
                        if(patients.size()>0) {
                            CommonFactory.getFactory(mActivity).changePatient(patients.get(0).getP_id(), patients.get(0).getPp_visitid());
                        }
                        adapter.setNewData(patients);
                        patient_sr.setRefreshing(false);
                    }
                });
    }

}

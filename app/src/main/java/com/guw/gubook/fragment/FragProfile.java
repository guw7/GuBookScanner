package com.guw.gubook.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.guw.gubook.Action.PrefManager;
import com.guw.gubook.R;
import com.guw.gubook.views.activity.PageLogin;

public class FragProfile extends Fragment {


    Button btnLogout;
    PrefManager manager;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView tvNama, tvAlamat, tvEmail, tvJK, tvAsal, tvNoHp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

//      Init
        manager = new PrefManager(getActivity());
        sharedPreferences = getActivity().getSharedPreferences(manager.PREF_NAME, 0);
        editor = sharedPreferences.edit();
        btnLogout = view.findViewById(R.id.btnLogout);

        tvNama = view.findViewById(R.id.tvNama);
        tvAlamat = view.findViewById(R.id.tvAlamat);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvJK = view.findViewById(R.id.tvJK);
        tvAsal = view.findViewById(R.id.tvAsal);
        tvNoHp = view.findViewById(R.id.tvNoHp);

        tvNama.setText(sharedPreferences.getString("nama", " "));
        tvAlamat.setText(sharedPreferences.getString("alamat", " "));
        tvJK.setText(sharedPreferences.getString("jk", " "));
        tvAsal.setText(sharedPreferences.getString("asal", " "));
        tvEmail.setText(sharedPreferences.getString("email", " "));
        tvNoHp.setText(sharedPreferences.getString("no_hp", " "));

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.setSudahLogin(false);
                editor.clear();
                editor.commit();

                Intent intent = new Intent(getActivity(), PageLogin.class);
                startActivity(intent);
            }
        });

        return view;
    }
}

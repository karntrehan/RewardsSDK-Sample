package com.mpaani.rsample;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mpaani.rewards.RedemptionResponseListener;
import com.mpaani.rewards.Rewards;
import com.mpaani.rewards.entities.TGiftFailed;
import com.mpaani.rewards.entities.TGiftSuccess;

public class RSampleActivity extends AppCompatActivity implements RedemptionResponseListener {

    TextInputEditText edtPhoneNumber;
    TextInputLayout tilPhoneNumber;
    Button btnDone;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsample);
        context = this;

        edtPhoneNumber = (TextInputEditText) findViewById(R.id.edt_phone_number);
        tilPhoneNumber = (TextInputLayout) findViewById(R.id.til_phone_number);
        btnDone = (Button) findViewById(R.id.btnDone);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = edtPhoneNumber.getText().toString().trim();

                if (TextUtils.isEmpty(phoneNumber))
                    tilPhoneNumber.setError("Cannot be empty");
                else performRedemption(phoneNumber);
            }
        });

    }

    private void performRedemption(String phoneNumber) {
        Rewards.getInstance().build(context, phoneNumber, this);
    }

    @Override
    public void redemptionSuccess(TGiftSuccess tGiftSuccess) {
        Toast.makeText(this, "Success! " + tGiftSuccess.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void redemptionFailed(TGiftFailed tGiftFailed) {
        Toast.makeText(this, "Failed! " + tGiftFailed.toString(), Toast.LENGTH_SHORT).show();
    }
}

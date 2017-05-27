package khoattv.retrofithw.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import khoattv.retrofithw.MainActivity;
import khoattv.retrofithw.R;
import khoattv.retrofithw.networks.NetworkService;
import khoattv.retrofithw.networks.Request;
import khoattv.retrofithw.networks.Response;
import khoattv.retrofithw.networks.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

  private EditText etUsername;
  private EditText etPassword;
  private Button btnSignUp;
  private TextView tvSignIn;
  private TextInputLayout ilUsername;
  private TextInputLayout ilPassword;


  public RegisterFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_register, container, false);
    etUsername = (EditText) view.findViewById(R.id.et_username_reg);
    etPassword = (EditText) view.findViewById(R.id.et_password_reg);
    btnSignUp = (Button) view.findViewById(R.id.btn_sign_up);
    tvSignIn = (TextView) view.findViewById(R.id.tv_sign_in);
    ilPassword = (TextInputLayout) view.findViewById(R.id.il_password_reg);
    ilUsername = (TextInputLayout) view.findViewById(R.id.il_username_reg);
    etUsername.addTextChangedListener(new MyTextWatcher(etUsername));
    etPassword.addTextChangedListener(new MyTextWatcher(etPassword));
    btnSignUp.setOnClickListener(this);
    tvSignIn.setOnClickListener(this);

    btnSignUp.setTextColor(Color.parseColor("#c5c5c5"));
    return view;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_sign_up:
        if (LoginFragment.checkTextInput(etPassword) && LoginFragment.checkTextInput(etUsername)) {
          NetworkService networkService = RetrofitFactory.getInstance().createService(NetworkService.class);
          networkService.register(new Request(etUsername.getText().toString(), etPassword.getText().toString()))
                  .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                      if (response.code() == 200) {
                        Toast.makeText(getActivity(), "Sign up success!", Toast.LENGTH_SHORT).show();
                      } else {
                        ilPassword.setError("User already exists!");
                      }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                      Toast.makeText(getActivity(), "No Internet!", Toast.LENGTH_SHORT).show();
                    }
                  });
        }
        break;
      case R.id.tv_sign_in:
        ((MainActivity) getActivity()).changeScreen(new LoginFragment(), false);
    }
  }



  private class MyTextWatcher implements TextWatcher {

    private View view;

    public MyTextWatcher(View view) {
      this.view = view;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
      switch (view.getId()) {
        case R.id.et_password_reg:
          if (!LoginFragment.checkTextInput(etPassword)) {
            ilPassword.setError("Password can not contain special characters and is longer than 3 characters!");
          } else {
            ilPassword.setErrorEnabled(false);
          }
          break;
        case R.id.et_username_reg:
          if (!LoginFragment.checkTextInput(etUsername)) {
            ilUsername.setError("Username can not contain special characters and is longer than 3 characters!");
          } else {
            ilUsername.setErrorEnabled(false);
          }
          break;
      }
      if (LoginFragment.checkTextInput(etUsername) && LoginFragment.checkTextInput(etPassword)) {
        btnSignUp.setTextColor(Color.parseColor("#3F51B5"));
      } else {
        btnSignUp.setTextColor(Color.parseColor("#c5c5c5"));
      }
    }
  }
}

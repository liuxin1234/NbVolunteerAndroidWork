package com.example.renhao.wevolunteer.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.core.listener.AccessTokenListener;
import com.example.core.local.LocalDate;
import com.example.model.AccessTokenBO;
import com.example.model.ActionCallbackListener;
import com.example.model.user.UserListDto;
import com.example.renhao.wevolunteer.IndexActivity;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {
    EditText et_username;
    EditText et_password;
    Button btn_login;
    Button btn_register;
    ImageView img_back;
    ImageView img_HiddenOrShow;
    TextView tv_forgot_password;
    LinearLayout ll;

    private String username;
    private String password;

    private boolean isHidden = true;

    private SharedPreferences sp = null;
    private static final String FILE_NAME = "SaveUsernameAndPassword";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initViewListener();
        setSharePreferences();
    }


    private void initView() {
        et_username = (EditText) findViewById(R.id.edit_login_username);
        et_password = (EditText) findViewById(R.id.edit_login_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
//        img_back = (ImageView) findViewById(R.id.imageView_btn_back);
        img_HiddenOrShow = (ImageView) findViewById(R.id.img_HiddenOrShow);
        tv_forgot_password = (TextView) findViewById(R.id.tv_forgot_password);
        ll = (LinearLayout) findViewById(R.id.login_layout);
    }

    private void initViewListener() {
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
//        img_back.setOnClickListener(this);
        img_HiddenOrShow.setOnClickListener(this);
        tv_forgot_password.setOnClickListener(this);
        et_username.setOnFocusChangeListener(this);
        et_password.setOnFocusChangeListener(this);
        ll.setOnTouchListener(onTouchListener);
    }



    private void setSharePreferences() {
        username = LocalDate.getInstance(this).getLocalDate("username", "");
        password = LocalDate.getInstance(this).getLocalDate("password", "");
        if (!TextUtils.isEmpty(username)) {
            et_username.setText(username);
        }
        if (!TextUtils.isEmpty(password)) {
            et_password.setText(password);
        }

    }

    private void onSaveContent() {
        super.onStop();
        username = et_username.getText().toString();
        password = et_password.getText().toString();
        LocalDate.getInstance(this).setLocalDate("username", username);
        LocalDate.getInstance(this).setLocalDate("password", password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_register:
                createAlertDialog();
                break;
/*            case R.id.imageView_btn_back:
                Intent intent = new Intent();
//                intent.putExtra("fragment_id", 0);
                LocalDate.getInstance(getApplicationContext()).setLocalDate("BACKHOME", true);
                intent.setClass(this, IndexActivity.class);
                startActivity(intent);
                getAccessToken();
                this.finish();
                break;*/
            case R.id.img_HiddenOrShow:
                if (isHidden) {
                    //设置EditText文本为可见的
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //设置EditText文本为隐藏的
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                isHidden = !isHidden;
                et_password.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = et_password.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }

                break;
            case R.id.tv_forgot_password:
                Intent intent_fgPassword = new Intent(this, ForgotPasswordActivity.class);
                startActivity(intent_fgPassword);
                break;
            default:
                break;
        }
    }
    /**
     * 给布局加触摸监听，当点击EditText之外的地方时
     * EditText失去焦点
     */
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated method stub
            ll.setFocusable(true);
            ll.setFocusableInTouchMode(true);
            ll.requestFocus();//布局获得焦点
            return false;
        }
    };

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.edit_login_username:
                setHintEt(et_username,hasFocus);
                break;
            case R.id.edit_login_password:
                setHintEt(et_password,hasFocus);
                break;
            default:
                break;
        }
    }

    private void setHintEt(EditText et,boolean hasFocus){
        String hint;
        if(hasFocus){
            hint = et.getHint().toString();
            et.setTag(hint);
            et.setHint("");
        }else{
            hint = et.getTag().toString();
            et.setHint(hint);
        }
    }

    private void login() {
        showNormalDialog("正在登陆…");
        username = et_username.getText().toString();
        password = et_password.getText().toString();
        if (TextUtils.isEmpty(username)) {
            showToast("账号不能为空");
            dissMissNormalDialog();
            return;
        } else if (TextUtils.isEmpty(password)) {
            showToast("密码不能为空");
            dissMissNormalDialog();
            return;
        } else {
            onSaveContent();
            AppActionImpl.getInstance(getApplicationContext()).getAccessToken(username, password, new AccessTokenListener() {
                @Override
                public void success(AccessTokenBO accessTokenBO) {
                    if (accessTokenBO == null){
                        return;
                    }
                    String userId = accessTokenBO.getUserId();
                    AppActionImpl.getInstance(getApplicationContext()).userIdLogin(userId, new ActionCallbackListener<UserListDto>() {
                        @Override
                        public void onSuccess(UserListDto data) {
                            if (data == null){
                                showToast("该用户不存在");
                            }else {
                                //注册登录时需写的
                                LocalDate.getInstance(getApplicationContext()).setLocalDate("volunteerId", data.getId());
                                LocalDate.getInstance(getApplicationContext()).setLocalDate("isLogin", true);
                                LocalDate.getInstance(getApplicationContext()).setLocalDate("username", username);
                                LocalDate.getInstance(getApplicationContext()).setLocalDate("password", password);
                                showToast("登录成功");
                                //测试用的跳转
                                startActivity(new Intent(LoginActivity.this, IndexActivity.class));
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {
                            dissMissNormalDialog();
                            showToast(message);
                            LocalDate.getInstance(getApplicationContext()).setLocalDate("volunteerId", "");
                            LocalDate.getInstance(getApplicationContext()).setLocalDate("isLogin", false);
                        }
                    });
                }

                @Override
                public void fail() {
                    ConnectivityManager con = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
                    boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
                    boolean internet = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
                    dissMissNormalDialog();
                    if (wifi || internet) {

                        //执行相关操作
                        showToast("用户名和密码错误");
                    } else {
                        showToast("网络未连接");
                    }


                }
            });

        }
    }

    private void createAlertDialog() {
        final AlertDialog builder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.register_dialog, null);
        Button btn_dialog_Volunteer = (Button) view.findViewById(R.id.btn_dialog_Volunteer);
        Button btn_dialog_majorVolunteer = (Button) view.findViewById(R.id.btn_dialog_majorVolunteer);
        if (btn_dialog_Volunteer != null) {
            btn_dialog_Volunteer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    builder.dismiss();
                }
            });
        }
        if (btn_dialog_majorVolunteer != null) {
            btn_dialog_majorVolunteer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LoginActivity.this, RegisterProBonoActivity.class));
                    builder.dismiss();
                }
            });
        }

        builder.setView(view);
        builder.show();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
//        intent.putExtra("fragment_id", 0);
        LocalDate.getInstance(getApplicationContext()).setLocalDate("BACKHOME", true);
        intent.setClass(this, IndexActivity.class);
        startActivity(intent);
        getAccessToken();
        this.finish();
    }


}

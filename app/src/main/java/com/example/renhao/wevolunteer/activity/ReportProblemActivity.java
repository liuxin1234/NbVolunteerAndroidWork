package com.example.renhao.wevolunteer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.mail.MailSenderInfo;
import com.example.renhao.wevolunteer.mail.SimpleMailSender;

/*问题反馈界面*/

public class ReportProblemActivity extends BaseActivity {
    private static final String TAG = "ReportProblemActivity";
    EditText edit_problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_problem);

        TextView tv_submit = (TextView) findViewById(R.id.tv_report_submit);
        edit_problem = (EditText) findViewById(R.id.edit_report_problem);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_problem.getText() == null)
                    showToast("反馈内容为空");
                else {
                    final String problem = edit_problem.getText().toString();
                    Thread newThread;         //声明一个子线程
                    new Thread() {
                        @Override
                        public void run() {
                            //这个类主要是设置邮件
                            MailSenderInfo mailInfo = new MailSenderInfo();
                            mailInfo.setMailServerHost("smtp.mxhichina.com");
                            mailInfo.setMailServerPort("25");
                            mailInfo.setValidate(true);
                            mailInfo.setUserName("postmaster@nbzyz.org");
                            mailInfo.setPassword("Wezynb35");//您的邮箱密码
                            mailInfo.setFromAddress("postmaster@nbzyz.org");
                            mailInfo.setToAddress("postmaster@nbzyz.org");
                            mailInfo.setSubject("App问题反馈");
                            mailInfo.setContent(problem);
                            //这个类主要来发送邮件
                            SimpleMailSender sms = new SimpleMailSender();
                            sms.sendTextMail(mailInfo);//发送文体格式
//                            sms.sendHtmlMail(mailInfo);//发送html格式
                        }
                    }.start();
                    showToast("您的反馈已发送");
                    ReportProblemActivity.this.finish();
                }


            }
        });

        //回退按钮
        ImageView btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportProblemActivity.this.finish();
            }
        });

    }
}

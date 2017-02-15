package com.example.renhao.wevolunteer.handler;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;

import org.xml.sax.XMLReader;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/23 20:06
 * 修改备注：
 */
public class MxgsaTagHandler implements Html.TagHandler {
    private int sIndex = 0;
    private  int eIndex=0;
    private final Context mContext;

    public MxgsaTagHandler(Context context){
        mContext=context;
    }

    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        // TODO Auto-generated method stub
        if (tag.toLowerCase().equals("mxgsa")) {
            if (opening) {
                sIndex=output.length();
            }else {
                eIndex=output.length();
                output.setSpan(new MxgsaSpan(), sIndex, eIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }
    private class MxgsaSpan extends ClickableSpan implements View.OnClickListener {
        @Override
        public void onClick(View widget) {
            // TODO Auto-generated method stub
            //具体代码，可以是跳转页面，可以是弹出对话框，下面是跳转页面
            //mContext.startActivity(new Intent(mContext,MainActivity.class));
        }
    }
}

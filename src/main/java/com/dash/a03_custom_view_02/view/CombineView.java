package com.dash.a03_custom_view_02.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dash.a03_custom_view_02.R;

/**
 * Created by Dash on 2017/12/28.
 */
public class CombineView extends FrameLayout implements View.OnClickListener {

    private TextView textView;
    private CheckBox checkBox;
    private String text;
    private boolean checked;

    public CombineView(@NonNull Context context) {
        super(context);

        init();
    }

    public CombineView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //获取xml里面初始的属性值
        text = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "text");

        //第一个参数表示命名空间,,,第二个参数属性的名称,,,第三个参数是默认的布尔值
        checked = attrs.getAttributeBooleanValue("http://schemas.android.com/apk/res-auto", "checked", false);

        init();
    }

    public CombineView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    /**
     * 初始化的方法....加载出布局,并且添加到当前自定义view上
     */
    private void init() {

        //第三个参数:是否有挂载的父控件...现在有了,当前的自定义view...相当于addView()
        View view = View.inflate(getContext(), R.layout.combine_layout, this);

        textView = view.findViewById(R.id.combine_text);
        checkBox = view.findViewById(R.id.combine_check);

        //点击事件
        this.setOnClickListener(this);

        //设置初始的数据
        textView.setText(text);
        checkBox.setChecked(checked);

    }

    /**
     * 对外提供设置文本的方法
     */
    public void setText(String text){
        textView.setText(text);
    }

    /**
     * 对外提供设置是否选中的方法
     */
    public void setChecked(boolean flag){
        checkBox.setChecked(flag);
    }

    /**
     * 对外提供是否选中的方法
     */
    public boolean getChecked(){
        return checkBox.isChecked();
    }


    @Override
    public void onClick(View view) {
        //改变checkBox状态
        checkBox.setChecked(! checkBox.isChecked());
    }
}

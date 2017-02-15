package com.example.renhao.wevolunteer.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.renhao.wevolunteer.R;
import com.unnamed.b.atv.model.TreeNode;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/24 17:14
 * 修改备注：
 */
public class TreeViewHolder extends TreeNode.BaseNodeViewHolder<TreeViewHolder.IconTreeItem> {

    public TreeViewHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(TreeNode node, IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_profile_node, null, false);
        TextView tvValue = (TextView) view.findViewById(R.id.tv_treeview_name);
        TextView icon = (TextView) view.findViewById(R.id.tv_treeview_icon);
        tvValue.setText(value.text);
        if (node.isSelected()){
            icon.setText("-");
        } else{
            icon.setText("+");}
        return view;
    }

    public static class IconTreeItem {
        public String text;
        public String id;
        public String code;
        public boolean isShow = false;
        public boolean isOpen = false;

        public IconTreeItem() {
        }

        public IconTreeItem(String code, String id, String text) {
            this.code = code;
            this.id = id;
            this.text = text;
        }

        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean show) {
            isShow = show;
        }
    }
}

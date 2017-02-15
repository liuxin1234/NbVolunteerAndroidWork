package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.area.AreaListDto;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.holder.TreeViewHolder;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 区域选择界面
 */
public class AreaSelectionActivity extends BaseActivity implements TreeNode.TreeNodeClickListener {
    private static final String TAG = "AreaSelectionActivity";
    @Bind(R.id.imageView_areaselect_back)
    ImageView mBack;
    @Bind(R.id.textView_areaselect_title)
    TextView mTitle;
    @Bind(R.id.tv_areaselect_submit)
    TextView mtSubmit;
    @Bind(R.id.relativeLayout)
    RelativeLayout mRelativeLayout;
    @Bind(R.id.listView_area_selection)
    ViewGroup mSelection;
    @Bind(R.id.tv_area_chose)
    TextView mTvChose;
    @Bind(R.id.ll_area)
    LinearLayout mLlArea;

    private AndroidTreeView tView;

    private TreeNode selectNode;
    private VolunteerViewDto mVolunteerViewDto;
    private String parentId = "ac689592-5a3e-4015-8609-cdeed42df6ab";

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_selection);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("type", -1);
        mVolunteerViewDto = (VolunteerViewDto) getIntent().getSerializableExtra("personal_data");

        initTreeNode();
    }

    private void initTreeNode() {
        TreeNode root = TreeNode.root();
        TreeViewHolder.IconTreeItem iconTreeItem = new TreeViewHolder.IconTreeItem("", parentId, "全国");
        TreeNode s1 = new TreeNode(iconTreeItem).setViewHolder(new TreeViewHolder(this));
        root.addChildren(s1);
        tView = new AndroidTreeView(this, root);
        tView.setDefaultAnimation(true);
        tView.setUse2dScroll(true);
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
        tView.setDefaultNodeClickListener(this);
           /* tView.setDefaultViewHolder(ArrowExpandSelectableHeaderHolder.class);*/
        mSelection.addView(tView.getView());
        tView.setUseAutoToggle(false);

        tView.expandAll();
    }

    private void queryArea(final TreeNode parent, TreeViewHolder.IconTreeItem item) {
        AppActionImpl.getInstance(this).AreaQuery(item.id, new ActionCallbackListener<List<AreaListDto>>() {
            @Override
            public void onSuccess(List<AreaListDto> data) {
                if (data == null || data.size() < 1) {
                    parent.setSelectable(false);
                    return;
                }
                List<TreeViewHolder.IconTreeItem> childs = new ArrayList<TreeViewHolder.IconTreeItem>();
                for (int i = 0; i < data.size(); i++) {
                    AreaListDto dto = data.get(i);
                    childs.add(new TreeViewHolder.IconTreeItem(dto.getCode(), dto.getId(), dto.getName()));
                }
                addChildTreeView(parent, childs);
                tView.expandNode(parent);
            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });
    }

    @OnClick({R.id.imageView_areaselect_back, R.id.tv_areaselect_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_areaselect_back:
                finish();
                break;
            case R.id.tv_areaselect_submit:
                submit();
                break;
        }
    }

    private void submit() {
        if (selectNode == null) {
            showToast("请选择");
            return;
        }
        TreeViewHolder.IconTreeItem item = (TreeViewHolder.IconTreeItem) selectNode.getValue();
        if (type > -1) {
            Intent intent = new Intent();
            intent.putExtra("areaName", item.text);
            intent.putExtra("areaId", item.id);
            intent.putExtra("areaCode", item.code);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            mVolunteerViewDto.setAreaCode(item.code);
            mVolunteerViewDto.setAreaId(item.id);
            mVolunteerViewDto.setAreaName(item.text);
            List<VolunteerViewDto> list = new ArrayList<>();
            list.add(mVolunteerViewDto);
            AppActionImpl.getInstance(this).volunteerUpdate(list, new ActionCallbackListener<String>() {
                @Override
                public void onSuccess(String data) {
                    /*Intent intent = new Intent();
                    intent.putExtra("", mVolunteerViewDto);
                    setResult(RESULT_OK, intent);*/
                    finish();
                }

                @Override
                public void onFailure(String errorEvent, String message) {
                    showToast("连接错误，请稍后再试");
                }
            });
        }
    }

    @Override
    public void onClick(TreeNode node, Object value) {
        TreeViewHolder.IconTreeItem item = (TreeViewHolder.IconTreeItem) value;
        mTvChose.setText(item.text);
        selectNode = node;
        if (node.isSelected()) {
            if (node.isExpanded()) {
                node.setExpanded(false);
                tView.collapseNode(node);
            } else {
                node.setExpanded(true);
                tView.expandNode(node);
            }
        } else {
            node.setSelected(true);
            if (node.isSelectable())
                queryArea(node, item);
        }

    }

    private void addChildTreeView(TreeNode parent, List<TreeViewHolder.IconTreeItem> childs) {
        for (int i = 0; i < childs.size(); i++) {
                /*parent.addChildren(new TreeNode(childs.get(i)).setViewHolder(new TreeViewHolder(this)));*/
            tView.addNode(parent, new TreeNode(childs.get(i)).setViewHolder(new TreeViewHolder(this)));
        }
            /*tView.expandAll();*/
    }

    private void addChildTreeView(TreeNode parent, TreeViewHolder.IconTreeItem child) {
           /* parent.addChildren(new TreeNode(child).setViewHolder(new TreeViewHolder(this)));*/
        tView.addNode(parent, new TreeNode(child).setViewHolder(new TreeViewHolder(this)));
    }
}

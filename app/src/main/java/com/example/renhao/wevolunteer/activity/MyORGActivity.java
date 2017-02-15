package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.organization.OrganizationListDto;
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
 * 所属机构界面
 */
public class MyORGActivity extends BaseActivity implements TreeNode.TreeNodeClickListener {
    private static final String TAG = "MyORGActivity";
    @Bind(R.id.imageView_myorg_back)
    ImageView mBack;
    @Bind(R.id.tv_myorg_title)
    TextView mTitle;
    @Bind(R.id.tv_myorg_submit)
    TextView mSubmit;
    @Bind(R.id.relativeLayout)
    RelativeLayout mRelativeLayout;
    @Bind(R.id.tv_myorg_chose)
    TextView mTvChose;
    @Bind(R.id.ll_myorg)
    LinearLayout mLlMyorg;
    @Bind(R.id.listView_myorg_selection)
    RelativeLayout mSelection;

    private AndroidTreeView tView;

    private TreeNode selectNode;
    private VolunteerViewDto mVolunteerViewDto;
    private String parentId = "00000000-0000-0000-0000-000000000000";

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorg);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("type", -1);
        mVolunteerViewDto = (VolunteerViewDto) getIntent().getSerializableExtra("personal_data");

        initTreeNode();
    }

    private void initTreeNode() {
        TreeNode root = TreeNode.root();
        TreeViewHolder.IconTreeItem iconTreeItem = new TreeViewHolder.IconTreeItem("", parentId, "全部");
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

    private void queryOrg(final TreeNode parent, TreeViewHolder.IconTreeItem item) {
        AppActionImpl.getInstance(this).organizationQueryChild(item.id, new ActionCallbackListener<List<OrganizationListDto>>() {
            @Override
            public void onSuccess(List<OrganizationListDto> data) {
                if (data == null || data.size() < 1) {
                    return;
                }
                List<TreeViewHolder.IconTreeItem> childs = new ArrayList<TreeViewHolder.IconTreeItem>();
                for (int i = 0; i < data.size(); i++) {
                    OrganizationListDto dto = data.get(i);
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

    @OnClick({R.id.imageView_myorg_back, R.id.tv_myorg_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_myorg_back:
                finish();
                break;
            case R.id.tv_myorg_submit:
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
            intent.putExtra("orgName", item.text);
            intent.putExtra("orgId", item.id);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            mVolunteerViewDto.setOrganizationId(item.id);
            mVolunteerViewDto.setOrganizationName(item.text);
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
            queryOrg(node, item);
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

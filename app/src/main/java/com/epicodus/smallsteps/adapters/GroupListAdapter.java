package com.epicodus.smallsteps.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.smallsteps.R;
import com.epicodus.smallsteps.models.Group;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.GroupViewHolder> {
    private ArrayList<Group> groupList = new ArrayList<>();
    private Context context;

    public GroupListAdapter(Context context, ArrayList<Group> groupList) {
        this.context = context;
        this.groupList = groupList;
    }

    @Override
    public GroupListAdapter.GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_list_item, parent, false);
        GroupViewHolder groupViewHolder = new GroupViewHolder(view);
        return groupViewHolder;
    }

    public void onBindViewHolder(GroupListAdapter.GroupViewHolder holder, int position) {
        holder.bindGroup(groupList.get(position));
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.groupNameTextView) TextView groupNameTextView;
        @Bind(R.id.groupCategoryTextView) TextView groupCategoryTextView;
        @Bind(R.id.groupLocationTextView) TextView groupLocationTextView;
        @Bind(R.id.groupMemberCountTextView) TextView groupMemberCountTextView;
        @Bind(R.id.groupImageView) ImageView groupImageView;

        private Context context;

        public GroupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        public void bindGroup(Group group) {
            groupNameTextView.setText(group.getShortName());
            groupCategoryTextView.setText(group.getCategory());
            groupLocationTextView.setText(group.getCity());
            groupMemberCountTextView.setText(String.valueOf(group.getMemberCount()));

            if(group.getImageUrl() != "") {
                Picasso.with(context).load(group.getImageUrl()).into(groupImageView);
            }
        }
    }

}

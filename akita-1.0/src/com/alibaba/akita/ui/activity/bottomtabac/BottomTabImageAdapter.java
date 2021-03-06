/*
 * Copyright 1999-2101 Alibaba Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.akita.ui.activity.bottomtabac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.alibaba.akita.R;

/**
 * Created with IntelliJ IDEA.
 * User: justin
 * Date: 12-4-3
 * Time: 下午7:46
 *
 * @author Justin Yang
 */
public class BottomTabImageAdapter extends BaseAdapter {
    private Context context;
    private int width;
    private int height;
    private int selectedColor;
    private int unSelectedColor;
    private Integer[] imgeIDs;
    private String[] tabLables;
    private LayoutInflater mInflater;
    private int mSelected = 0;

    public BottomTabImageAdapter(Context context, Integer[] imageIDs, String[] tabLables,
                                 int width, int height, int unSelectedColor, int selectedColor) {
        this.context = context;
        this.imgeIDs = imageIDs;
        this.tabLables = tabLables;
        this.width = width;
        this.height = height;
        this.selectedColor = selectedColor;
        this.unSelectedColor = unSelectedColor;

        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return imgeIDs.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /**
     * 点击设置
     * @param selectedID
     */
    public void setFocus(int selectedID) {
        mSelected = selectedID;
        this.notifyDataSetInvalidated();
    }

    /**
     * 图片设置
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.griditem_tab, null);
            holder = new Holder();
            holder.rl_griditem = (RelativeLayout) convertView.findViewById(R.id.ll_griditem);
            holder.v_indicator = convertView.findViewById(R.id.v_indicator);
            holder.iv_tab = (ImageView) convertView.findViewById(R.id.iv_tab);
            holder.tv_tab = (TextView) convertView.findViewById(R.id.tv_tab);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        if (mSelected == position) {
            holder.v_indicator.setBackgroundColor(this.selectedColor);
        } else {
            holder.v_indicator.setBackgroundColor(this.unSelectedColor);
        }
        if (imgeIDs[position] == null) {
            holder.iv_tab.setVisibility(View.GONE);
        } else {
            holder.iv_tab.setImageResource(imgeIDs[position]);
        }
        holder.iv_tab.setBackgroundColor(android.R.drawable.picture_frame);
        holder.tv_tab.setText(tabLables[position]);
        holder.rl_griditem.setLayoutParams(new GridView.LayoutParams(width, height));

        return convertView;
    }

    static class Holder {
        public RelativeLayout rl_griditem;
        public View v_indicator;
        public ImageView iv_tab;
        public TextView tv_tab;
    }

}


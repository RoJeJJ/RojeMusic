package com.roje.rojemusic.fragment.dialog;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.roje.rojemusic.R;
import com.roje.rojemusic.entities.DialogItem;
import com.roje.rojemusic.widget.view.DialogScrollLayout;
import com.roje.rojemusic.utils.DisplayUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreFragment extends AttachDialogFragment {
    public static String TAG = "MoreFragment";
    public interface DialogItemClickListener{
        void itemClick(int position);
    }
    private String title;
    private ArrayList<DialogItem> items;
    private DialogItemClickListener listener;

    @BindView(R.id.bottom_dialog_rv)
    RecyclerView rv;
    @BindView(R.id.dialog_layout)
    DialogScrollLayout dialogLayout;
    public static MoreFragment newInstance(String title, ArrayList<DialogItem> items){
        MoreFragment fragment = new MoreFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        args.putSerializable("items",items);
        fragment.setArguments(args);
        return fragment;
    }
    public MoreFragment(){}

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            title = getArguments().getString("title");
            items = (ArrayList<DialogItem>) getArguments().getSerializable("items");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(activity,R.style.bottomDialog);
        @SuppressLint("InflateParams")
        View root = LayoutInflater.from(activity).inflate(R.layout.bottom_dialog_layout,null);
        ButterKnife.bind(this,root);
        dialog.setContentView(root);
        Window window = dialog.getWindow();
        if (window != null){
            window.setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = DisplayUtil.screenWith(activity);
            window.setAttributes(params);
        }
        initViews();
        return dialog;
    }

    public void setDialogItemClickListener(DialogItemClickListener listener){
        this.listener = listener;
    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.bottom_dialog_layout,container,false);
//        ButterKnife.bind(this,view);
//        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getDialog().setCanceledOnTouchOutside(true);
//        Window window = getDialog().getWindow();
//        if (window != null){
//            WindowManager.LayoutParams params = window.getAttributes();
//            params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
//            params.width = DisplayUtil.screenWith(activity);
//            view.measure(0,0);
//            params.height = view.getMeasuredHeight();
//            window.setAttributes(params);
//        }
//        initViews();
//        return view;
//    }

    private void initViews() {
        rv.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        rv.addItemDecoration(new ItemDecoration());
        DialogMoreAdapter adapter = new DialogMoreAdapter();
        rv.setAdapter(adapter);
        rv.setNestedScrollingEnabled(true);
        dialogLayout.setDisMissListener(new DialogScrollLayout.DisMissListener() {
            @Override
            public void disMiss() {
                Dialog dialog = getDialog();
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
    }

    class DialogMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            switch (viewType){
                case 0:
                    view = LayoutInflater.from(activity).inflate(R.layout.bottom_dialog_title,parent,false);
                    return new TitleHolder(view);
                default:
                    view = LayoutInflater.from(activity).inflate(R.layout.bottom_dialog_item,parent,false);
                    return new ItemHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position) == 0){
                TitleHolder titleHolder = (TitleHolder) holder;
                titleHolder.title.setText(title);
            }else {
                final ItemHolder itemHolder = (ItemHolder) holder;
                DialogItem item = items.get(position - 1);
                itemHolder.icn.setImageResource(item.icn);
                itemHolder.text.setText(item.title);
                itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (listener != null)
                            listener.itemClick(itemHolder.getAdapterPosition() - 1);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return items.size()+1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0){
                return 0;
            }else return 1;
        }

        class TitleHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.bottom_dialog_title)
            TextView title;
            TitleHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
        class ItemHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.bottom_item_icn)
            ImageView icn;
            @BindView(R.id.bottom_item_title)
            TextView text;
            ItemHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }

    }
    private class ItemDecoration extends RecyclerView.ItemDecoration{
        private Drawable drawer;
        ItemDecoration(){
            drawer = ContextCompat.getDrawable(activity,R.drawable.divider);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int right = parent.getWidth() - parent.getPaddingRight();
            for (int i=0;i<parent.getChildCount();i++){
                View child = parent.getChildAt(i);
                if (parent.getChildAdapterPosition(child) != 0){
                    TextView tv = ButterKnife.findById(child,R.id.bottom_item_title);
                    RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                    int left = tv.getLeft();
                    int top = child.getBottom()+params.bottomMargin;
                    int bottom = top+drawer.getIntrinsicHeight();
                    drawer.setBounds(left,top,right,bottom);
                    drawer.draw(c);
                }
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0,0,0,drawer.getIntrinsicHeight());
        }
    }
}

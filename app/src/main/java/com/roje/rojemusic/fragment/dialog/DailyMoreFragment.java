package com.roje.rojemusic.fragment.dialog;


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
import com.roje.rojemusic.bean.comment.CommentResp;
import com.roje.rojemusic.bean.daily_song.RecDailySongRespBean;
import com.roje.rojemusic.present.Presenter;
import com.roje.rojemusic.present.impl.PresenterImpl;
import com.roje.rojemusic.utils.DisplayUtil;
import com.roje.rojemusic.widget.view.DialogScrollLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DailyMoreFragment extends AttachDialogFragment {
    @BindView(R.id.bottom_dialog_rv)
    RecyclerView recycle;
    @BindView(R.id.dialog_layout)
    DialogScrollLayout dialogLayout;
    private DailyMoreAdapter adapter;
    private RecDailySongRespBean.RecommendBean bean;
    private int comment = -1;
    private Presenter presenter;
    private Observer<CommentResp> observer;
    private Disposable disposable;
    private Drawable divider;
    public DailyMoreFragment(){}
    public static DailyMoreFragment newInstance(RecDailySongRespBean.RecommendBean bean){
        DailyMoreFragment fragment = new DailyMoreFragment();
        Bundle arg = new Bundle();
        arg.putSerializable("daily_song",bean);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.BOTTOM;
            params.width = DisplayUtil.screenWith(activity);
            window.setAttributes(params);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        divider = ContextCompat.getDrawable(activity,R.drawable.divider);
        rxInit();
        if (getArguments() != null)
            bean = (RecDailySongRespBean.RecommendBean) getArguments().getSerializable("daily_song");
        if (bean != null) {
            presenter.comment(bean.getCommentThreadId(), 0, 0, observer);
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        dispose();
    }
    private void dispose(){
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    private void rxInit() {
        presenter = new PresenterImpl();
        observer = new Observer<CommentResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(CommentResp commentResp) {
                comment = commentResp.getTotal();
                if (adapter != null )
                    adapter.notifyItemChanged(4);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                dispose();
            }

            @Override
            public void onComplete() {
                dispose();
            }
        };
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(activity, R.style.bottomDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = LayoutInflater.from(activity).inflate(R.layout.bottom_dialog_layout,container,false);
        ButterKnife.bind(this,root);
        initViews();
        return root;
    }

    private void initViews() {
        recycle.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        recycle.setAdapter(adapter = new DailyMoreAdapter());
        recycle.setNestedScrollingEnabled(true);
        recycle.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                if (divider != null){
                    for (int i=0;i<parent.getChildCount();i++){
                        View view = parent.getChildAt(i);
                        RecyclerView.ViewHolder holder = parent.getChildViewHolder(view);
                        if (holder instanceof DailyMoreAdapter.ItemHolder){
                            DailyMoreAdapter.ItemHolder h = (DailyMoreAdapter.ItemHolder) holder;
                            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
                            int left = h.title.getLeft();
                            int top = view.getBottom() + params.bottomMargin;
                            int right = parent.getWidth() - parent.getPaddingRight();
                            int bottom = view.getBottom() + divider.getIntrinsicHeight();
                            divider.setBounds(left, top, right, bottom);
                            divider.draw(c);
                        }
                    }
                }
                super.onDraw(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (divider != null){
                    RecyclerView.ViewHolder holder = parent.getChildViewHolder(view);
                    if (holder instanceof DailyMoreAdapter.ItemHolder)
                        outRect.bottom = divider.getIntrinsicHeight();
                }
                super.getItemOffsets(outRect, view, parent, state);
            }
        });
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
    class DailyMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 0){
                View view = LayoutInflater.from(activity).inflate(R.layout.daily_song_more_head,parent,false);
                return new HeadHolder(view);
            }else {
                View view = LayoutInflater.from(activity).inflate(R.layout.bottom_dialog_item,parent,false);
                return new ItemHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof HeadHolder){
                HeadHolder h = (HeadHolder) holder;
                h.title.setText(activity.getString(R.string.dialog_song_name,bean.getName()));
                if (bean.getCopyright() == 0){
                    h.tip.setVisibility(View.INVISIBLE);
                    h.btn.setVisibility(View.INVISIBLE);
                }else {
                    h.tip.setVisibility(View.VISIBLE);
                    h.btn.setVisibility(View.VISIBLE);
                    h.btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
            }else {
                ItemHolder h = (ItemHolder) holder;
                switch (position){
                    case 1:
                        h.icn.setImageResource(R.drawable.yx);
                        h.title.setText(R.string.daily_more_next_play);
                        break;
                    case 2:
                        h.icn.setImageResource(R.drawable.yr);
                        h.title.setText(R.string.daily_more_collect);
                        break;
                    case 3:
                        h.icn.setImageResource(R.drawable.yo);
                        h.title.setText(R.string.daily_more_dld);
                        break;
                    case 4:
                        h.icn.setImageResource(R.drawable.yl);
                        String str = getString(R.string.daily_more_comment);
                        if (comment > 0)
                            str += "("+comment+")";
                        h.title.setText(str);
                        break;
                    case 5:
                        h.icn.setImageResource(R.drawable.zc);
                        h.title.setText(R.string.daily_more_share);
                        break;
                    case 6:
                        h.icn.setImageResource(R.drawable.yx);
                        h.title.setText(R.string.daily_more_next_play);
                        break;
                    case 7:
                        h.icn.setImageResource(R.drawable.yx);
                        StringBuilder sb = new StringBuilder();
                        for (int i= 0;i<bean.getArtists().size();i++){
                            sb.append(bean.getArtists().get(i).getName());
                            if (i != bean.getArtists().size() - 1)
                                sb.append("/");
                        }
                        h.title.setText(getString(R.string.daily_more_artist,sb.toString()));
                        break;
                    case 8:
                        h.icn.setImageResource(R.drawable.yg);
                        h.title.setText(getString(R.string.daily_more_album,bean.getAlbum().getName()));
                        break;
                    case 9:
                        h.icn.setImageResource(R.drawable.zi);
                        h.title.setText(R.string.daily_more_video);
                        break;
                    case 10:
                        h.icn.setImageResource(R.drawable.yy);
                        h.title.setText(R.string.daily_more_boring);
                        break;
                }
            }
        }

        @Override
        public int getItemCount() {
            return 11;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0)
                return 0;
            return 1;
        }
        class HeadHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.title)
            TextView title;
            @BindView(R.id.tip)
            TextView tip;
            @BindView(R.id.btn)
            TextView btn;
            HeadHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
        class ItemHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.bottom_item_icn)
            ImageView icn;
            @BindView(R.id.bottom_item_title)
            TextView title;
            ItemHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
}

package com.itemanimation;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tiv on 02.08.2016.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    private static final String TAG = Adapter.class.getSimpleName();
    List<String> strings;
    private OnViewClickedCallback callback;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onViewClicked(view);
            }
        });
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bindData(strings.get(position), position);
    }

    @Override
    public int getItemCount() {
        return (strings != null) ? strings.size() : 0;
    }

    public void addItems(List<String> strings) {
        this.strings = strings;
        notifyItemRangeChanged(0, strings.size());
        Log.d(TAG, "addItems: " + this.strings);
//        notifyDataSetChanged();
    }

    public void deleteOrAddData(int deletePos, int addPos) {
        strings.remove(deletePos);
        notifyItemRemoved(deletePos);
        strings.add(addPos, "ANIMATION");
//       notifyItemRemoved(addPos);
        notifyItemInserted(addPos);

    }

    public void setCallback(OnViewClickedCallback callback) {
        this.callback = callback;
    }
    public void deleteAtPosition(int pos){
        strings.remove(pos);
        notifyItemRemoved(pos);
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView textView;
        private int inListPosition;

        public Holder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
        }

        public void bindData(String data, int position) {
            Log.d(TAG, "bindData() called with: " + "data = [" + data + "]");
            textView.setText(data);
            inListPosition = position;
        }
    }
}

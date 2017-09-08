package app.androidrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter  extends
        RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<String> mtList ;
    public Context mcontext;
    ViewHolder viewHolder;
    int lastPosition = -1;
    int fastPosition = +1;

    public Adapter(List<String> list, Context context) {

        mtList = list;
        mcontext = context;
    }

    // Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        // create a layout
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);

        viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Called by RecyclerView to display the data at the specified position.
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position ) {

        viewHolder.textView.setText(mtList.get(position));
        Picasso.with(mcontext).load(R.drawable.image).into(viewHolder.imageView);
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        "OnClick :" + mtList.get(position),
                        Toast.LENGTH_SHORT).show();

            }
        });

        if(position >lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(mcontext, R.anim.up_from_bottom);
            viewHolder.itemView.startAnimation(animation);
            //lastPosition = position;
        }

        if(position <fastPosition) {

            Animation animation = AnimationUtils.loadAnimation(mcontext, R.anim.down_from_top);
            viewHolder.itemView.startAnimation(animation);
            //fastPosition = position;
        }


    }

    //Returns the total number of items in the data set hold by the adapter.
    @Override
    public int getItemCount() {
        return mtList.size();
    }

    // initializes some private fields to be used by RecyclerView.
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.text);
            imageView = (ImageView) view.findViewById(R.id.image);
        }
    }

}
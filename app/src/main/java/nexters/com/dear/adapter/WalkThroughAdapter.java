package nexters.com.dear.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import nexters.com.dear.R;
import nexters.com.dear.model.WalkThroughInfo;


public class WalkThroughAdapter extends PagerAdapter {
    Context context;
    ArrayList<WalkThroughInfo> infoArrayList = new ArrayList<>();

    public WalkThroughAdapter(Context context, ArrayList<WalkThroughInfo> infoArrayList) {
        this.context = context;
        this.infoArrayList = infoArrayList;
    }

    @Override
    public int getCount() {
        return infoArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = null;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.template_walk_through, container, false);
        ImageView imageView = view.findViewById(R.id.template_walk_thru_image);
        imageView.setImageResource(infoArrayList.get(position).getResid());


        TextView txtPageNum = view.findViewById(R.id.template_walk_thru_page_number);
        TextView txtDes = view.findViewById(R.id.template_walk_thru_description);

        txtDes.setText(infoArrayList.get(position).getDescription());
        txtPageNum.setText(infoArrayList.get(position).getPage());


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        (container).removeView((View) object);

    }
}

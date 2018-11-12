package in.trentweet.recyclerwithfilter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private List<CountryModel> modelList;
    private ArrayList<String> photos = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context _context;
    MainActivity _mainActivity;

    public MyAdapter(Context context, List<CountryModel> modelList, MainActivity mainActivity) {
        this.modelList = modelList;
        _context = context;
        _mainActivity = mainActivity;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        photos.add("http://icons.iconarchive.com/icons/danieledesantis/playstation-flat/256/playstation-circle-icon.png");
        photos.add("https://cdn1.iconfinder.com/data/icons/ui-5/502/speech-512.png");
        photos.add("https://sawmill-creek.com/wp-content/uploads/2018/03/Circle-icons-profle.svg.png");
        photos.add("https://marketplace.canva.com/MAB5ncsL3dA/1/thumbnail/canva-arrow-pointing-right-inside-circle-icon-MAB5ncsL3dA.png");
        photos.add("http://www.smnext.in/wp-content/uploads/2014/10/youtube-circle-icon-150x150.png");
        photos.add("http://www.learnex.in/wp-content/uploads/2015/12/flat-faces-icons-circle-6.png");

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView img_flag;
        TextView txtCountryName, txtNativeName, txtCountryCapital;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            img_flag = itemView.findViewById(R.id.img_flag);
            txtCountryName = itemView.findViewById(R.id.txtCountryName);
            txtNativeName = itemView.findViewById(R.id.txtNativeName);
            txtCountryCapital = itemView.findViewById(R.id.txtCountryCapital);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(layoutInflater
                .inflate(R.layout.layout_recyclerview, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        CardView cardView = viewHolder.cardView;
        ImageView img_flag = viewHolder.img_flag;
        TextView txtCountryName = viewHolder.txtCountryName,
                txtNativeName = viewHolder.txtNativeName,
                txtCountryCapital = viewHolder.txtCountryCapital;


        Glide.with(_context)
                .load(photos.get(new Random().nextInt(photos.size())))
                .into(img_flag);

        txtCountryName.setText(modelList.get(position).getCountryName());
        txtNativeName.setText(modelList.get(position).getCountryNativeName());
        txtCountryCapital.setText(modelList.get(position).getCountryCapitalName());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelList.remove(position);
                _mainActivity.setText("Left: " + modelList.size());
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void updateList(List<CountryModel> list) {
        modelList = list;
        notifyDataSetChanged();
    }

}

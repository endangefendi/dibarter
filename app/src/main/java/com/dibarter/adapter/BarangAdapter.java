package com.dibarter.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dibarter.R;
import com.dibarter.model.BarangModel;
import com.google.android.gms.ads.AdView;

import java.util.List;

import static com.dibarter.fragment.HomeFragment.IndexIklan;

public class BarangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "AgendaAdapter";
    private Context context;
    private List<Object> list;
    private OnItemClickListener listener;
    int AD_TYPE = 0;
    // A menu item view type.
    private static final int MENU_ITEM_VIEW_TYPE = 0;

    // The banner ad view type.
    private static final int BANNER_AD_VIEW_TYPE = 1;

    public BarangAdapter(Context context, OnItemClickListener listener, List<Object> list) {
        this.context = context;
        this.listener = listener;
        this.list = list;
    }

    @Override
    public int getItemCount() {
//        Log.e("getItemCount", "getItemCount "+list.size());
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
//            if (!(itemView instanceof AdView)) {
                mTextView =  v.findViewById(R.id.text);
//            }
        }
    }


    public void addList(List<Object> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClicked(int position, BarangModel item);
    }


    /**
     * The {@link AdViewHolder} class.
     */
    public static class AdViewHolder extends RecyclerView.ViewHolder {

        AdViewHolder(View view) {
            super(view);
        }
    }


    /**
     * Determines the view type for the given position.
     */
    @Override
    public int getItemViewType(int position) {
        return (position % IndexIklan == 0) ? BANNER_AD_VIEW_TYPE
                : MENU_ITEM_VIEW_TYPE;
    }

    /**
     * Creates a new view for a menu item view or a banner ad view
     * based on the viewType. This method is invoked by the layout manager.
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case MENU_ITEM_VIEW_TYPE:
                View menuItemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.item_barang, viewGroup, false);
                return new MenuItemViewHolder(menuItemLayoutView);
            case BANNER_AD_VIEW_TYPE:
                // fall through
            default:
                View bannerLayoutView = LayoutInflater.from(
                        viewGroup.getContext()).inflate(R.layout.banner_ad_container,
                        viewGroup, false);
                return new AdViewHolder(bannerLayoutView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case MENU_ITEM_VIEW_TYPE:
                MenuItemViewHolder ItemHolder = (MenuItemViewHolder) holder;
                BarangModel item = (BarangModel) list.get(position);

                // Add the menu item details to the menu item view.
                Glide.with(context).load(item.getItem_gambar_utama())
                        .placeholder(R.drawable.ic_account_circle)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(ItemHolder.foto);
                ItemHolder.tv_judul.setText(item.getItem_title());
                ItemHolder.tv_deskripsi.setText(item.getItem_desc());
                ItemHolder.tv_tgl.setText(item.getItem_tanggal());
                ItemHolder.tv_lokasi.setText(item.getItem_wilayah());

                break;
            case BANNER_AD_VIEW_TYPE:
                // fall through
            default:
                AdViewHolder bannerHolder = (AdViewHolder) holder;
                AdView adView = (AdView) list.get(position);
                ViewGroup adCardView = (ViewGroup) bannerHolder.itemView;
                // The AdViewHolder recycled by the RecyclerView may be a different
                // instance than the one used previously for this position. Clear the
                // AdViewHolder of any subviews in case it has a different
                // AdView associated with it, and make sure the AdView for this position doesn't
                // already have a parent of a different recycled AdViewHolder.
                if (adCardView.getChildCount() > 0) {
                    adCardView.removeAllViews();
                }
                if (adView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }

                // Add the banner ad to the ad view.
                adCardView.addView(adView);

                if (position==0){
                    adView.setVisibility(View.GONE);
                }
        }

        BarangModel item = (BarangModel) list.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) listener.onItemClicked(position, item);
            }
        });
    }

    public static class MenuItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_judul;
        TextView tv_deskripsi;
        TextView tv_lokasi;
        TextView tv_tgl;
        ImageView foto;

        MenuItemViewHolder(View view) {
            super(view);
            tv_judul = view.findViewById(R.id.judul);
            tv_deskripsi = view.findViewById(R.id.deskripsi);
            tv_lokasi = view.findViewById(R.id.lokasi);
            tv_tgl = view.findViewById(R.id.tgl);
            foto = view.findViewById(R.id.foto);
        }
    }


}
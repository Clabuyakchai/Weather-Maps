package com.example.clabuyakchai.weather.ui.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.clabuyakchai.weather.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

public class ClusterRenderer extends DefaultClusterRenderer<OfficeItem> {
    private final int markerWidth;
    private final int markerHeight;
    private Context context;

    public ClusterRenderer(Context context, GoogleMap map, ClusterManager<OfficeItem> clusterManager) {
        super(context, map, clusterManager);

        this.context = context;
        markerWidth = (int) context.getResources().getDimension(R.dimen.custom_marker_width);
        markerHeight = (int) context.getResources().getDimension(R.dimen.custom_marker_height);
    }

    @Override
    protected void onBeforeClusterItemRendered(OfficeItem item, MarkerOptions markerOptions) {
        IconGenerator iconGenerator = new IconGenerator(context);
        Drawable marker = context.getResources().getDrawable(R.drawable.bg_marker);
        iconGenerator.setBackground(marker);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_marker, null, false);

        TextView textMarker = view.findViewById(R.id.custom_marker_text);
        textMarker.setText(item.getTitle());

        iconGenerator.setContentView(view);

        Bitmap icon = Bitmap.createScaledBitmap(iconGenerator.makeIcon(), markerWidth, markerHeight, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
    }
}

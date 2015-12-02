package com.bench.tlacouque.benchosmdroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.osmdroid.api.IMapController;


import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.bonuspack.overlays.Marker;




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapView map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        map.setUseDataConnection(true);
        IMapController mapController = map.getController();
        mapController.setZoom(15);
        GeoPoint startPoint = new GeoPoint(48.553609 , 3.01594);
        mapController.setCenter(startPoint);
        Marker marker = new Marker(map);
        marker.setPosition(startPoint);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setIcon(getResources().getDrawable(android.R.drawable.star_on));
        marker.setTitle("Start point");
        map.getOverlays().add(marker);
        map.invalidate();
        CacheManagerChanged cacheManager = new CacheManagerChanged(map);
        Log.d("Test",""+cacheManager.cacheCapacity());
        Log.d("Test",""+cacheManager.currentCacheUsage());
        double north = 48.560591;
        double south = 48.548348;
        double est = 3.02536;
        double west = 3.000212;
        BoundingBoxE6 boundingBoxE6 = new BoundingBoxE6(north,est,south,west);
        cacheManager.downloadAreaAsync(this, boundingBoxE6, 15,15);
        Log.d("Test", "" + cacheManager.currentCacheUsage());
        Log.d("Test", "" + cacheManager.possibleTilesInArea(boundingBoxE6, 15, 15));
        map.invalidate();


    }
}

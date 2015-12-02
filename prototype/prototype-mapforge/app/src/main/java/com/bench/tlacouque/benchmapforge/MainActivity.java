package com.bench.tlacouque.benchmapforge;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.util.AndroidUtil;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.datastore.MapDataStore;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.overlay.Marker;
import org.mapsforge.map.layer.renderer.TileRendererLayer;
import org.mapsforge.map.reader.MapFile;
import org.mapsforge.map.rendertheme.InternalRenderTheme;

import java.io.File;


public class MainActivity extends Activity {
    MapView mapViewLayout;
    private static final String MAPFILE = "germany.map";
    TileCache tileCache;
    TileRendererLayer tileRendererLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //AndroidGraphicFactory.createInstance(this.getApplication());
        mapViewLayout = (MapView) findViewById(R.id.mapView);
        mapViewLayout.setClickable(true);
        mapViewLayout.getMapScaleBar().setVisible(true);
        mapViewLayout.setBuiltInZoomControls(true);
        mapViewLayout.getMapZoomControls().setZoomLevelMin((byte) 10);
        mapViewLayout.getMapZoomControls().setZoomLevelMax((byte) 20);
        tileCache = AndroidUtil.createTileCache(this, "mapcache", mapViewLayout.getModel().displayModel.getTileSize(),
                1f, mapViewLayout.getModel().frameBufferModel.getOverdrawFactor());


    }

    @Override
    protected void onStart() {
        super.onStart();
        mapViewLayout.getModel().mapViewPosition.setCenter(new LatLong(52.492069, 13.284844));
        mapViewLayout.getModel().mapViewPosition.setZoomLevel((byte) 12);

        // tile renderer layer using internal render theme
        MapDataStore mapDataStore = new MapFile(getMapFile());
        tileRendererLayer = new TileRendererLayer(tileCache, mapDataStore,
                mapViewLayout.getModel().mapViewPosition, false, true, AndroidGraphicFactory.INSTANCE);
        tileRendererLayer.setXmlRenderTheme(InternalRenderTheme.OSMARENDER);

        // only once a layer is associated with a mapView the rendering starts
        mapViewLayout.getLayerManager().getLayers().add(tileRendererLayer);
        createPositionMarker((double)52.492069,(double)13.284844);


    }

    private void createPositionMarker(double paramDouble1, double paramDouble2) {
    final LatLong localLatLong = new LatLong(paramDouble1, paramDouble2);
        StarMarker positionmarker = new StarMarker(android.R.drawable.btn_star_big_on, localLatLong);
        mapViewLayout.getLayerManager().getLayers().add(positionmarker);
}


private class StarMarker extends Marker {
    public StarMarker(int icon, LatLong localLatLong) {
        super(localLatLong,AndroidGraphicFactory.convertToBitmap(MainActivity.this.getApplicationContext().getResources().getDrawable(icon)),
                1*(AndroidGraphicFactory.convertToBitmap(MainActivity.this.getApplicationContext().getResources().getDrawable(icon)).getWidth())/2,
                -1*(AndroidGraphicFactory.convertToBitmap(MainActivity.this.getApplicationContext().getResources().getDrawable(icon)).getHeight())/2);
    }
}

    private File getMapFile() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),MAPFILE);
        return file;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapViewLayout.destroyAll();
    }
}
